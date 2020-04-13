package com.battlecity.main.world;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;
import com.battlecity.main.entity.*;
import com.battlecity.main.entity.DynamicEntity.State;
import com.battlecity.main.entity.tank.*;
import com.battlecity.main.events.powers.PowerUP;
import com.battlecity.main.entity.tiles.Tile;
import com.battlecity.main.events.TimerManager.Task;
import com.battlecity.main.events.powers.*;
import com.battlecity.main.global.*;
import static com.battlecity.main.global.Presets.DEATHMATCH_POWERUP_DELAY;
import com.battlecity.main.global.enums.Event;
import com.battlecity.main.global.enums.Power;
import com.battlecity.main.global.enums.Tier;
import com.battlecity.main.levels.*;
import com.battlecity.main.net.Server;
import com.battlecity.main.ui.*;
import java.util.*;



/**
 *
 * @author Pablis
 */
public class ServerWorld extends World
{

    private final ServerSpawner spawner;
    private final HashMap<Integer, Entity> playerList;
    private ArrayList<Entity> entities;
    private ArrayList<Entity> bufferArray;
    private boolean enemiesFrozen;
    private Task changeLevelTimer;
    private Task spawnPowerUPDeathmatch;

    public ServerWorld() {
        this.enemiesFrozen = false;
        this.isWorldInitiated = false;
        this.changeLevelTimer = null;
        this.bufferArray = new ArrayList<>(30);
        this.entities = new ArrayList<>(30);
        this.playerList = new HashMap<>(2);
        this.spawner = new ServerSpawner();
    }

    @Override
    public void changeLevel(BaseLevel l) {
        super.changeLevel(l);

        enemiesFrozen = false;
        isWorldInitiated = (l.getLevelID() == LevelEnd.LEVEL_END_ID);
        changeLevelTimer = null;
        entities.clear();
        bufferArray.clear();
        bufferArray.addAll(playerList.values());

        final Task onNextLevel = new Task()
        {
            @Override
            public void run() {
                if(getCurrentLevel().getMode() == BaseLevel.Mode.ARCADE)
                    spawner.start();
                else {
                    spawnPowerUPDeathmatch = new Task()
                    {
                        @Override
                        public void run() {
                            if(getCurrentLevel().getMode() != BaseLevel.Mode.DEATHMATCH)
                                this.cancel();

                            ((ServerWorld)Server.getInstance().getWorld()).spawnPowerUp();
                        }
                    };

                    World.getTimerManager().scheduleTask(spawnPowerUPDeathmatch, DEATHMATCH_POWERUP_DELAY, DEATHMATCH_POWERUP_DELAY);
                }

                for(Entity e : playerList.values()) {
                    PlayerTank pTank = (PlayerTank)e;

                    if(pTank.getPlayerLives() > 0 && l.getLevelID() != LevelEnd.LEVEL_END_ID) {
                        pTank.onSpawn();
                    }
                    else {
                        pTank.setState(State.DEAD);
                    }
                }

                isWorldInitiated = true;
                Server.getInstance().sendEventToAll(Event.ON_WORLD_INIT, -1);
                Server.getInstance().sendStateToAll();
            }
        };

        World.getTimerManager().scheduleTask(onNextLevel, 4000);
        Server.getInstance().sendEventToAll(Event.ON_NEXT_LEVEL, l.getLevelID());
    }

    /**
     * Adds a new player to the world.
     *
     * @param playerID
     *
     * @return - The entity added.
     */
    public PlayerTank addPlayer(int playerID) {
        PlayerTank pTank = new PlayerTank(0, 0, playerID);
        pTank.setPlayerLives(Parameters.playerLives);
        addEntity(pTank);
        playerList.put(playerID, pTank);
        return pTank;
    }

    @Override
    public void addEntity(Entity e) {
        bufferArray.add(e);

        if(isWorldInitiated()) {
            e.onSpawn();
        }
    }

    @Override
    public ArrayList<Entity> getEntities() {
        return new ArrayList<>(entities);
    }

    public Entity getPlayer(int id) {
        return this.playerList.get(id);
    }

    public Collection<Entity> getPlayers() {
        return this.playerList.values();
    }

    public void removePlayer(int playerID) {
        this.playerList.remove(playerID);
    }

    @Override
    public boolean isGameOver() {
        return getCurrentLevel().getMode() == BaseLevel.Mode.ARCADE && !getCurrentLevel().getEagle().isAlive();
    }

    public void spawnPowerUp() {
        this.spawner.createPowerUPInstance();
    }

    public boolean enemiesFrozen() {
        return this.enemiesFrozen;
    }

    /**
     *
     * @param freeze
     */
    public void freezeEnemies(boolean freeze, Entity pickedBy) {
        for(Entity e : Server.getInstance().getWorld().getEntities()) {
            if(!(e instanceof Tank) || e == pickedBy)
                continue;

            if(Server.getInstance().getWorld().getCurrentLevel().getMode() == BaseLevel.Mode.DEATHMATCH || e instanceof EnemyTank) {
                ((Tank)e).setFrozen(freeze);
            }
        }
        this.enemiesFrozen = freeze;
    }

    /**
     * The number of enemies not spawned already.
     *
     * @return
     */
    public int totalEnemies() {
        return Parameters.totalEnemiesCount - spawner.enemiesSpawnedTotal;
    }

    /**
     * Total number of enemies that will be created by the spawner.
     *
     * @return the spawn list length.
     */
    public int enemySpawnListSize() {
        return spawner.getTotalEnemies();
    }

    @Override
    public void update() {
        BaseLevel.Mode mode = getCurrentLevel().getMode();

        switch(mode) {
            case ARCADE:
                if(spawner.allEnemiesKilled()) {
                    if(changeLevelTimer == null) {
                        changeLevelTimer = new ChangeLevel();
                        int delay = 4000;
                        World.getTimerManager().scheduleTask(changeLevelTimer, delay);
                    }
                }
                break;
            case DEATHMATCH:
                if(changeLevelTimer != null)
                    break;

                int alive = 0;

                for(Entity e : playerList.values()) {
                    PlayerTank pTank = (PlayerTank)e;

                    if(pTank.getState() != State.DEAD)
                        alive++;
                }

                if(alive == 1 && playerList.size() > 1) {
                    changeLevelTimer = new ChangeLevel();
                    World.getTimerManager().scheduleTask(changeLevelTimer, 4000);
                }

                break;
        }

        for(Entity entity : this.entities) {
            boolean alive = entity instanceof Bullet ? ((Bullet)entity).getState() != State.DEAD : entity.isAlive();

            if(alive) {

                bufferArray.add(entity);

                if(entity instanceof DynamicEntity) {
                    DynamicEntity dEntity = (DynamicEntity)entity;

                    if(dEntity.getState() == State.MOVING && !dEntity.
                       isFrozen()) {

                        boolean canMove = true;

                        float oldX = dEntity.getX();
                        float oldY = dEntity.getY();

                        dEntity.moveEntity();

                        Rectangle dEntityBounds = dEntity.getBounds();

                        if(this.worldBounds.contains(dEntityBounds)) {

                            for(Tile t : getCurrentLevel().tileList) {
                                if(!t.isAlive()) {
                                    continue;
                                }

                                if(dEntityBounds.overlaps(t.getBounds())) {
                                    boolean collided = t.onCollision(dEntity);

                                    if(collided) {
                                        canMove = false;
                                    }

                                    if(!canMove) {
                                        if(dEntity instanceof Bullet) {
                                            t.onDammageBy(entity);
                                            getCurrentLevel().damagedTileList.
                                                    add(t);

                                            ((Bullet)dEntity).onCollision(t);

                                            /**
                                             * This lines fixes a visual bug in
                                             * the client when the bullet
                                             * destroys a Tile so it will render
                                             * properly in the center rather
                                             * than outside the bounds.
                                             * Temporary.
                                             */
//                                                    dEntity.moveEntity();
                                        }
                                        else {
                                            // This is important, else a tank can move between tileList.
                                            if(entity instanceof PlayerTank) {
                                                Vector2 push = entity.getBounds().getPosition(Vector2.X).sub(t.getBounds().getPosition(Vector2.Y));
                                                float nX = push.x * Presets.PUSH_FORCE;
                                                float nY = push.y * Presets.PUSH_FORCE;
                                                oldX += nX;
                                                oldY += nY;
                                            }
                                            break;
                                        }
                                    }
                                }

                                t.update();
                            }

                            if(canMove) {
                                for(Entity oEntity : this.entities) {

                                    if(dEntity == oEntity) {
                                        continue;
                                    }

                                    if(!oEntity.isAlive()) {
                                        continue;
                                    }

                                    if(dEntityBounds.
                                            overlaps(oEntity.
                                                    getBounds())) {
                                        canMove = !(dEntity.onCollision(oEntity));

                                        if(!canMove) {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        else {

                            if(entity instanceof Bullet) {
                                if(((Bullet)entity).getOwner() instanceof PlayerTank) {
                                    Server.getInstance().sendEventToAll(
                                            Event.ON_BULLET_DEAD, -1);
                                }
                            }
                            canMove = false;
                        }

                        if(!canMove) {
                            if(dEntity instanceof Bullet) {
                                ((Bullet)dEntity).onDammageBy(null);
                            }
                            else {
                                dEntity.setX(oldX);
                                dEntity.setY(oldY);

                                if(dEntity instanceof EnemyTank) {
                                    ((EnemyTank)dEntity).pickRandomDirection();
                                }
                            }
                        }
                    }
                }
            }
            else if(entity instanceof EnemyTank) {
                spawner.onSpawnRemoved(((EnemyTank)entity).getTier());
            }

            entity.update();
        }
        /**
         * Swap the arrays.
         */
        entities.clear();
        ArrayList<Entity> temp = entities;
        entities = bufferArray;
        bufferArray = temp;
    }

    private class ServerSpawner
    {

        private static final int MAX_CONCURRENT_SPAWNS = 4;

        private int[] spawnList = null;
        private Task spawnTimer = null;

        private PowerUP powerUPEntity = null;

        private int enemiesSpawnedTotal = 0;
        private int enemiesKilled = 0;
        private int concurrentEnemies = 0;
        private int spawnPos = 0;

        ServerSpawner() {

        }

        /**
         * Starts spawning enemies. No action is taken if the spawner is already
         * running.
         */
        public void start() {
            this.powerUPEntity = null;
            this.enemiesSpawnedTotal = 0;
            this.concurrentEnemies = 0;
            this.enemiesKilled = 0;
            this.spawnPos = 0;
            this.spawnList = getCurrentLevel().getSpawnList();

            if(this.spawnTimer != null) {
                stop();
            }

            spawnTimer = new Task()
            {

                @Override
                public void run() {
                    spawnNext();
                }
            };

            World.getTimerManager().scheduleTask(spawnTimer, 3000, 3000);

            if(Presets.debugOn) {
                System.out.println("The world spawner has been started.");
            }
        }

        public void spawnNext() {
            if(ScreenGame.paused)
                return;

            if(enemiesSpawnedTotal >= this.spawnList.length)
                return;

            if(concurrentEnemies >= MAX_CONCURRENT_SPAWNS)
                return;

            final Tile t = getCurrentLevel().enemySpawns.get(spawnPos);
            float spawnX = t.getX();
            float spawnY = t.getY();

            if(spawnPos < (getCurrentLevel().enemySpawns.size - 1)) {
                spawnPos++;
            }
            else {
                spawnPos = 0;
            }

            EnemyTank enemy;
            int j = this.spawnList[enemiesSpawnedTotal];
            boolean isPowerUpEnemy = enemiesSpawnedTotal == 4 ||
                                     enemiesSpawnedTotal == 11 || enemiesSpawnedTotal == 18;

            switch(j) {
                default: {
                    enemy = new EnemyTank(spawnX, spawnY, Tier.FIRST,
                                          isPowerUpEnemy);
                    break;
                }
                case 2: {
                    enemy = new EnemyTank(spawnX, spawnY, Tier.SECOND,
                                          isPowerUpEnemy);
                    break;
                }
                case 3: {
                    enemy = new EnemyTank(spawnX, spawnY, Tier.THIRD,
                                          isPowerUpEnemy);
                    break;
                }
                case 4: {
                    enemy = new EnemyTank(spawnX, spawnY, Tier.FOUR,
                                          isPowerUpEnemy);
                    break;
                }
            }

            if(enemiesFrozen()) {
                enemy.setFrozen(true);
            }

            addEntity(enemy);
            enemiesSpawnedTotal++;
            concurrentEnemies++;
            Server.getInstance().sendEventToAll(Event.ON_ENEMY_SPAWNED, -1);

            if(Presets.debugOn) {
                System.out.println("Enemy spawned [Total: " +
                                   enemiesSpawnedTotal + "/" + this.spawnList.length +
                                   ". On screen: " + concurrentEnemies + "/" +
                                   MAX_CONCURRENT_SPAWNS);
            }
        }

        /**
         * Stops spawning enemies. Duh!
         */
        public void stop() {
            spawnTimer.cancel();
        }

        public void onSpawnRemoved(Tier tier) {
            concurrentEnemies--;
            enemiesKilled++;
        }

        /**
         * Creates a new power-up. If a previous one is present it will be
         * destroyed first.
         *
         * @param power
         */
        public void createPowerUPInstance() {

            if(this.powerUPEntity != null && this.powerUPEntity.isAlive()) {
                this.powerUPEntity.destroy();
            }

            Array<Power> pList = new Array(Power.values());
            Iterator<Power> it = pList.iterator();

            while(it.hasNext()) {
                Power p = it.next();

                switch(p) {
                    case BOUNCE:
                    case HEALTH:
                    case CLOAK: {
                        if(getCurrentLevel().getMode() == BaseLevel.Mode.ARCADE)
                            it.remove();
                        break;
                    }
                    case SHOVEL: {
                        if(getCurrentLevel().getMode() == BaseLevel.Mode.DEATHMATCH)
                            it.remove();
                        break;
                    }
                    case TANK: {
                        if(Server.localCoop)
                            it.remove();
                        break;
                    }
                }
            }

//            Power[] pList = Power.values();
//            int j = new Random().nextInt(pList.length);
//            Power p = pList[j];
            Power p = pList.random();

            float posX = 0;
            float posY = 0;

            int tsize = getCurrentLevel().emtpyTileList.size;
            int random = new Random().nextInt(tsize);

            for(int i = 0; i < tsize; i++) {
                if(i == random) {
                    Tile t = getCurrentLevel().emtpyTileList.get(i);
                    posX = t.getX();
                    posY = t.getY();
                }
            }

            //this.powerUPEntity = new PowerBounce(posX, posY);
            
            switch(p) {
                case GRENADE: {
                    this.powerUPEntity = new PowerGrenade(posX, posY);
                    break;
                }
                case STAR: {
                    this.powerUPEntity = new PowerStar(posX, posY);
                    break;
                }
                case TANK: {
                    this.powerUPEntity = new PowerTank(posX, posY);
                    break;
                }
                case CLOCK: {
                    this.powerUPEntity = new PowerClock(posX, posY);
                    break;
                }
                case SHOVEL: {
                    this.powerUPEntity = new PowerShovel(posX, posY);
                    break;
                }
                case CLOAK: {
                    this.powerUPEntity = new PowerCloak(posX, posY);
                    break;
                }
                case HEALTH: {
                    this.powerUPEntity = new PowerHealth(posX, posY);
                    break;
                }
                case BOUNCE: {
                    this.powerUPEntity = new PowerBounce(posX, posY);
                    break;
                }
                default: {
                    this.powerUPEntity = new PowerHelmet(posX, posY);
                    break;
                }
            }

            addEntity(powerUPEntity);
        }

        public int getTotalEnemies() {
            return this.spawnList.length;
        }

        public boolean allEnemiesKilled() {
            return this.spawnList == null || enemiesKilled >= this.spawnList.length;
        }

    }

    private final class ChangeLevel extends Task
    {
        @Override
        public void run() {

            if(isGameOver()) {
                this.cancel();
                return;
            }

            if(spawnPowerUPDeathmatch != null)
                spawnPowerUPDeathmatch.cancel();

            int levelID = getCurrentLevel().getLevelID();

            switch(levelID) {
                case LevelEnd.LEVEL_END_ID:
                    Server.getInstance().sendEventToAll(Event.ON_WORLD_GAMEOVER, levelID);
                    return;
                case 35:
                    levelID = LevelEnd.LEVEL_END_ID;
                    break;
                default:
                    levelID++;
                    break;
            }

            if(getCurrentLevel().getMode() == BaseLevel.Mode.DEATHMATCH) {
                for(Entity e : playerList.values()) {
                    ((PlayerTank)e).setPlayerLives(Parameters.playerLives);
                }
            }

            BaseLevel next = BaseLevel.getLevel(levelID);

            if(next == null) {
                next = BaseLevel.getLevel(LevelEnd.LEVEL_END_ID);
                Parameters.friendlyFire = false;
            }

            ScreenGame.paused = false;
            ServerWorld.this.changeLevel(next);
            System.out.println("Level changed to: " + next.
                               getLevelID());
        }

    };

}
