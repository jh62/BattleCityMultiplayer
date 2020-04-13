package com.battlecity.main.entity.tank;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;
import com.battlecity.main.assets.AssetsGame;
import com.battlecity.main.entity.*;
import com.battlecity.main.entity.tiles.Tile;
import com.battlecity.main.events.TimerManager.Task;
import com.battlecity.main.events.powers.*;
import com.battlecity.main.global.Parameters;
import com.battlecity.main.global.Presets;
import com.battlecity.main.global.enums.Event;
import com.battlecity.main.global.enums.Power;
import com.battlecity.main.global.enums.Tier;
import com.battlecity.main.levels.*;
import com.battlecity.main.net.*;
import com.battlecity.main.world.*;
import java.util.*;



/**
 *
 * @author Pablis
 */
public final class PlayerTank extends Tank
{

    private String name = "Tank" + MathUtils.random(100);
    private int playerID = 1;
    private Power powerUP = Power.NONE;
    private int powerUpDuration = 0;
    private int lives;
    private float health = 0;
    public boolean bouncing_field = false; // <-- this is what i call lazy hack
    public int bouncing_field_hitpoints = Presets.BOUNCE_FIELD_HITPOINTS;

    private boolean showHealth = false;
    transient private int score = 0;
    transient public Vector2 direction = new Vector2();

    PlayerTank() {
    }

    public PlayerTank(float x, float y) {
        super(x, y, SIDE.PLAYER);
    }

    public PlayerTank(float x, float y, int playerID) {
        this(x, y);
        this.playerID = playerID;
    }

    @Override
    public void setAction(Action action) {
        if(action == Action.SHOOTING) {
            long lastShot = System.currentTimeMillis() - lastShotTime;

            if(lastShot < Presets.PLAYER_ATTACK_DELAY_MS) {
                action = Action.NORMAL;
            }
            else {
                lastShotTime = System.currentTimeMillis();
            }
        }

        super.setAction(action);
    }

    public int getPlayerID() {
        return this.playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public float getHealth() {
        return health;
    }

    public void addPlayerLife() {
        int amount = this.lives + 1;
        setPlayerLives(amount);
    }

    @Override
    public void onShooting() {
        super.onShooting();

        if(getPowerUp() == Power.CLOAK) {
            setPowerUp(Power.NONE);
        }
    }

    public void removePlayerLife() {
        setPlayerLives(this.lives - 1);
    }

    public void setPlayerLives(int amount) {
        this.lives = amount < 0 ? 0 : amount > 10 ? 10 : amount;
    }

    public int getPlayerLives() {
        return this.lives;
    }

    public void setPowerUp(Power power) {
        this.powerUP = power;
        this.powerUpDuration = power.duration;

        if(power == Power.STAR) {
            this.tier = this.getTier().nextTIER();
        }
        else if(power == Power.HEALTH) {
            this.health = 1.0f;
        }
        else if(power == Power.BOUNCE) {
            bouncing_field_hitpoints = Presets.BOUNCE_FIELD_HITPOINTS;
            System.out.println("added hitpoints: " + this.bouncing_field_hitpoints);
        }

        if(this.powerUpDuration > 0) {

            final Task task_PowerUpConsume = new Task()
            {

                @Override
                public void run() {
                    if(powerUpDuration > 0) {
                        powerUpDuration -= 1;
                    }
                    else {
                        powerUP = Power.NONE;
                        this.cancel();
                    }
                }
            };

            World.getTimerManager().scheduleTask(task_PowerUpConsume, 1, 1);
        }
    }

    public Power getPowerUp() {
        return this.powerUP;
    }

    public float getPowerUpTimeLeft() {
        return this.powerUpDuration;
    }

    public boolean getShowHealth() {
        return showHealth;
    }

    transient private long lastShotTime = 0;

    @Override
    public boolean isShooting() {
        if(bulletInstances >= 2) {
            return true;
        }
        else if(bulletInstances >= 1 && this.getTier() != Tier.THIRD && this.
                getTier() == Tier.FOUR) {
            return true;
        }

        return super.isShooting();
    }

    public void onEnemyKilled(Tier tier) {
        this.score += tier.score;

        if(this.score >= Presets.NEW_LIFE_SCORE * (Client.instance().local.size)) {
            this.score = 0;
            this.addPlayerLife();

            for(PlayerTank pt : Client.instance().local) {
                pt.addPlayerLife();
            }

            Server.getInstance().sendEventToAll(Event.ON_LIFE_PICKUP, playerID);
        }

        Server.getInstance().sendPlayerScoreUpdate(Client.instance().local.size > 1 ? 1 : this.playerID,
                                                   tier.ordinal());
    }

    @Override
    public void setState(State state) {
        super.setState(state);

        switch(state) {
            case DEFAULT: {
                this.setFacing(Facing.UP);
                this.setState(State.STOPPED);
                this.setAction(Action.NORMAL);
                this.setPowerUp(Power.FORCE_FIELD);
                this.bullet = null;
                this.bulletInstances = 0;
                this.health = Client.instance().getWorld().getCurrentLevel().getMode() == BaseLevel.Mode.DEATHMATCH ? 1.0f : 0f;
                this.bouncing_field = false;
                this.bouncing_field_hitpoints = Presets.BOUNCE_FIELD_HITPOINTS;
                break;
            }
            case RESPAWNING: {
                if(getPlayerLives() == 0) {
                    setState(State.DEAD);

                    if(Server.isServerRunning()) {
                        Server.getInstance().
                                sendEventToPlayer(
                                        Event.ON_PLAYER_GAMEOVER, playerID,
                                        playerID);
                    }
                    return;
                }

                if(Server.isServerRunning() && Server.getInstance().getWorld().
                   getCurrentLevel() != null) {

                    Tile t;

                    BaseLevel.Mode mode = Server.getInstance().getWorld().getCurrentLevel().getMode();

                    switch(mode) {
                        case DEATHMATCH: {
                            t = Server.getInstance().getWorld().getCurrentLevel().getRandomEmptyTile(mode);
                            break;
                        }
                        default:
                            if(playerID == 1)
                                t = Server.getInstance().getWorld().getCurrentLevel().playerSpawns.get(0);
                            else if(playerID == 2)
                                t = Server.getInstance().getWorld().getCurrentLevel().playerSpawns.get(1);
                            else {
                                t = Server.getInstance().getWorld().getCurrentLevel().getRandomEmptyTile(mode);
                            }
                            break;
                    }

                    this.x = t.getX();
                    this.y = t.getY();
                }

                float delay = AssetsGame.manager().animations.get("respawn").
                        getAnimationDuration() * 1000;

                final Task task_RespawnEvent = new Task()
                {

                    @Override
                    public void run() {
                        setState(State.DEFAULT);
                        bulletInstances = 0;
                    }
                };

                World.getTimerManager().scheduleTask(task_RespawnEvent,
                                                     (int)delay);
                break;
            }
            case MOVING: {
                if(this.playerID == Client.instance().getID()) {
                    AssetsGame.manager().soundManager.getSound("tankIdle").
                            stop();
                    AssetsGame.manager().soundManager.getSound("tankMoving").
                            play(true, 0.5f);
                }
                break;
            }
            case STOPPED: {
                if(this.playerID == Client.instance().getID()) {
                    AssetsGame.manager().soundManager.getSound("tankMoving").
                            stop();
                    AssetsGame.manager().soundManager.getSound("tankIdle").
                            play(true);
                }
                break;
            }
            case EXPLODING: {
                if(this.playerID == Client.instance().getID()) {
                    AssetsGame.manager().soundManager.getSound("tankIdle").
                            stop();
                    AssetsGame.manager().soundManager.getSound("tankMoving").
                            stop();
                }

                final Task task_onKilled = new Task()
                {
                    @Override
                    public void run() {
                        setTier(Tier.FIRST);
                        setState(State.RESPAWNING);
                    }
                };

                float delay = AssetsGame.manager().animations.get("explosion").
                        getAnimationDuration() * 1000;
                World.getTimerManager().scheduleTask(task_onKilled, (int)delay);

                break;
            }
            case DEAD: {
                if(this.playerID == Client.instance().getID()) {
                    AssetsGame.manager().soundManager.getSound("tankIdle").
                            stop();
                    AssetsGame.manager().soundManager.getSound("tankMoving").
                            stop();
                }
                break;
            }
        }
    }

    @Override
    public void onSpawn() {
        super.onSpawn();
        setState(State.RESPAWNING);
    }

    @Override
    public boolean onCollision(Entity attacker) {
        if(attacker instanceof PowerUP) {
            return attacker.onCollision(this);
        }
//        else if(attacker instanceof PlayerTank) {
//            return (((PlayerTank)attacker).getPowerUp() != Power.FORCE_FIELD) && Parameters.friendlyFire;
//        }

        return super.onCollision(attacker);
    }

    @Override
    public void onDammageBy(Entity attacker) {
        if(this.getPowerUp() == Power.HELMET || this.getPowerUp() ==
                                                Power.FORCE_FIELD) {
            return;
        }

        if(((DynamicEntity)attacker).getSide() != this.getSide() ||
           Parameters.friendlyFire || Server.getInstance().getWorld().getCurrentLevel().getMode() == BaseLevel.Mode.DEATHMATCH && Server.getInstance().getWorld().getCurrentLevel().getLevelID() != LevelEnd.LEVEL_END_ID) {

            if(Server.getInstance().getWorld().getCurrentLevel().getMode() == BaseLevel.Mode.DEATHMATCH) {
                
                health -= Presets.PLAYER_ATTACK_DAMAGE;
                showHealth = true;

                if(getPowerUp() == Power.CLOAK)
                    setPowerUp(Power.NONE);

                World.getTimerManager().scheduleTask(new Task()
                {
                    @Override
                    public void run() {
                        showHealth = false;
                        this.cancel();
                    }
                }, Presets.DEATHMATCH_SHOW_HEALTH_TIME);

                if(health > 0)
                    return;

                if(attacker instanceof Bullet) {
                    int attackerID = ((PlayerTank)((Bullet)attacker).getOwner()).playerID;

                    if(attackerID != playerID) {
                        Server.getInstance().sendPlayerScoreUpdate(attackerID, Tier.PLAYER_TANK.ordinal()); // Add to attacker score
                    }
                }
            }

            this.setState(State.EXPLODING);
            removePlayerLife();
            Server.getInstance().sendEventToPlayer(Event.ON_PLAYER_LOST_LIFE,
                                                   playerID,
                                                   playerID);
            return;
        }

        if(!isFrozen()) {
            final Task task_Freeze = new Task()
            {

                private int elapsed = 0;
                private int blickTimer = 0;

                @Override
                public void run() {
                    if(elapsed > Presets.FRIENDLY_FIRE_FREEZE_TIME) {
                        setFrozen(false);
                        setVisible(true);
                        this.cancel();
                        return;
                    }

                    if(blickTimer >= 500) {
                        setVisible(!isVisible());
                        blickTimer = 0;
                    }

                    blickTimer++;
                    elapsed++;
                }
            };

            setFrozen(true);
            World.getTimerManager().scheduleTask(task_Freeze, 1, 1);
        }
    }

    @Override
    public void update() {
        super.update();
    }

}
