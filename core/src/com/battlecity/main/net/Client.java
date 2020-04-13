package com.battlecity.main.net;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.*;
import com.battlecity.main.*;
import com.battlecity.main.assets.AssetsGame;
import com.battlecity.main.entity.DynamicEntity.State;
import com.battlecity.main.entity.Entity;
import com.battlecity.main.entity.Entity.Facing;
import com.battlecity.main.entity.tank.PlayerTank;
import com.battlecity.main.entity.tank.Tank.Action;
import com.battlecity.main.entity.tiles.Eagle;
import com.battlecity.main.entity.tiles.Tile;
import com.battlecity.main.entity.tiles.TileRegion;
import com.battlecity.main.events.PowerShovelEvent;
import com.battlecity.main.events.TimerManager.Task;
import com.battlecity.main.global.Parameters;
import com.battlecity.main.global.enums.*;
import com.battlecity.main.levels.BaseLevel;
import com.battlecity.main.net.packet.*;
import static com.battlecity.main.net.packet.Packet.TYPE.UPDATE;
import com.battlecity.main.ui.*;
import com.battlecity.main.world.*;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.io.IOException;
import java.util.ArrayList;



/**
 *
 * @author Pablis
 */
public class Client extends com.esotericsoftware.kryonet.Client
{

    public ResponseCode error = ResponseCode.NOREASON;

    private static Client instance;

    private ArrayList<Entity> entities = new ArrayList<>(225);
    private ArrayList<Entity> bufferArray = new ArrayList<>(225);

    private PlayerTank pTank;
    private ClientWorld world;

    public Array<PlayerTank> local = new Array();

    private String chatBuffer = "";

    Client() {
    }

    public static Client instance() {
        if(Client.instance == null) {
            Client.instance = new Client();
            PacketRegistry.registerPackets(Client.instance.getKryo());
        }
        return Client.instance;
    }

    public void initialize(String inet4Address, String name) throws IOException {
        this.addListener(clientListener);
        this.world = new ClientWorld();
        this.pTank = new PlayerTank(0, 0);
        local.add(pTank);
        this.setName(name);
        this.error = ResponseCode.NOREASON;
        this.start();
        this.connect(5000, inet4Address, Server.TCP, Server.UDP);
    }

    @Override
    public void setName(String name) {
        super.setName(name);

        if(pTank != null) {
            pTank.setName(name);
        }
    }

    @Deprecated
    @Override
    public void stop() {
        super.stop();
    }

    public void shutdown() {
        super.stop();
        super.close();
        this.world.dispose();
        local.clear();
        System.out.println("Client shutdown.");
    }

    public ClientWorld getWorld() {
        return this.world;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public String getChatText() {
        return this.chatBuffer;
    }

    public void emtpyChatBuffer() {
        this.chatBuffer = "";
    }

    public int getPlayerLives() {
        return pTank.getPlayerLives();
    }

    public PlayerTank getPlayerTank() {
        return pTank;
    }

    public State getPlayerState() {
        return pTank.getState();
    }

    public void update() {
        if(!world.isWorldInitiated()) {
            return;
        }

        if(bufferArray != null && !bufferArray.isEmpty()) {
            entities.clear();
            ArrayList<Entity> temp = entities;
            entities = bufferArray;
            bufferArray = temp;
        }

        getWorld().setEntities(entities);
        getWorld().update();
    }

    public void sendEntityUpdate(Action action) {
        this.sendEntityUpdate(pTank.getState(), pTank.getFacing(), action);
    }

    public void sendEntityUpdate(State state) {
        this.sendEntityUpdate(state, pTank.getFacing(), pTank.getAction());
    }

    public void sendEntityUpdate(State state, Facing facing) {
        this.sendEntityUpdate(state, facing, pTank.getAction());
    }

    public void sendEntityUpdate(State state, Facing facing, Action action) {
        if(!world.isWorldInitiated() || world.isGameOver()) {
            return;
        }

        if(!pTank.isAlive() || pTank.getState() == State.RESPAWNING || pTank.
           getState() ==
                                                                       State.EXPLODING || pTank.getState() ==
                                                                                          State.EXPLODED) {
            return;
        }

        if(state != pTank.getState()) {
            pTank.setState(state);
        }

        if(facing != pTank.getFacing()) {
            pTank.setFacing(facing);
        }

        if(action != pTank.getAction()) {
            pTank.setAction(action);
        }

        sendUpdatePacket();
    }

    public void sendChat(String text) {
        PacketChatText pChat = (PacketChatText)Packet.createPacket(
                PacketChatText.class, Packet.TYPE.UPDATE, getID());
        pChat.name = pTank.getName();
        pChat.text = text;
        sendTCP(pChat);
    }

    public void sendEvent(Event event) {
        PacketEvent pEvent = (PacketEvent)Packet.
                createPacket(PacketEvent.class, UPDATE, getID());
        pEvent.event = event;
        sendTCP(pEvent);
    }

    public void sendUpdatePacket() {
        if(pTank.isFrozen()) {
            return;
        }

        PacketEntity p = (PacketEntity)Packet.createPacket(
                PacketEntity.class, Packet.TYPE.UPDATE, getID());

        p.name = pTank.getName();
        p.facing = pTank.getFacing();
        p.state = pTank.getState();
        p.action = pTank.getAction();

        sendUDP(p);
    }

    private final Listener clientListener = new Listener()
    {
        @Override
        public void connected(Connection connection) {
            System.out.println("connected to server!");
        }

        @Override
        public void disconnected(Connection connection) {
            System.out.println("disconnected from server!");

            if(error == ResponseCode.NOREASON) {
                error = ResponseCode.SERVER_DISCONNECTED;

                Gdx.app.postRunnable(new Runnable()
                {
                    @Override
                    public void run() {
                        GameScreen screen = new ScreenMainMenu();
                        CoreBC.changeScreen(screen);
                    }
                });
            }
        }

        @Override

        public void received(Connection connection, Object object) {
            if(object instanceof PacketPlayerID) {
                PacketPlayerID pEntity = (PacketPlayerID)object;
                switch(pEntity.getType()) {
                    case CONNECTED: {
                        Client.instance().getPlayerTank().setPlayerID(pEntity.playerID);
                        System.out.println("Recieved playerID: " + pEntity.playerID);
                        break;
                    }
                    case DISCONNECTED: {
                        break;
                    }
                    case UPDATE: {

                        break;
                    }
                }
            }
            else if(object instanceof PacketServerUpdateEntities) {
                PacketServerUpdateEntities pEntity = (PacketServerUpdateEntities)object;

                switch(pEntity.getType()) {
                    case CONNECTED: {
                        break;
                    }
                    case DISCONNECTED: {
                        break;
                    }
                    case UPDATE: {
                        bufferArray = pEntity.entityList;
                        break;
                    }
                }
            }
            else if(object instanceof PacketTileData) {
                PacketTileData pTileData = (PacketTileData)object;

                switch(pTileData.getType()) {
                    case CONNECTED: {
                        final Task task = new Task()
                        {

                            @Override
                            public void run() {
                                if(!getWorld().isWorldInitiated()) {
                                    return;
                                }

                                for(Tile tile
                                    : getWorld().getCurrentLevel().tileList) {

                                    if(tile.tileID == pTileData.tileID) {

                                        for(TileRegion tr : tile.getRegions()) {
                                            if(tr.regionValue() ==
                                               pTileData.tileRegion.
                                                       regionValue()) {

                                                if(pTileData.tileRegion.
                                                        isDamaged()) {
                                                    getWorld().getCurrentLevel().damagedTileList.
                                                            add(tile);

                                                    if(pTileData.tileRegion.
                                                            isDestroyed()) {
                                                        tr.destroy();
                                                    }
                                                    else if(pTileData.tileRegion.
                                                            isHitTop()) {
                                                        tr.damage(Facing.DOWN);
                                                    }
                                                    else if(pTileData.tileRegion.
                                                            isHitBottom()) {
                                                        tr.damage(Facing.UP);
                                                    }
                                                    else if(pTileData.tileRegion.
                                                            isHitLeft()) {
                                                        tr.damage(Facing.RIGHT);
                                                    }
                                                    else if(pTileData.tileRegion.
                                                            isHitRight()) {
                                                        tr.damage(Facing.LEFT);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                                this.cancel();
                            }
                        };

                        World.getTimerManager().scheduleTask(task, 100, 100);
                        break;
                    }

                    case DISCONNECTED: {
                        break;
                    }
                    case UPDATE: {

                        break;
                    }
                }
            }
            else if(object instanceof PacketChatText) {
                PacketChatText pChat = (PacketChatText)object;

                if(pChat.text != null) {
                    chatBuffer = pChat.toString();
                }
            }
            else if(object instanceof PacketScore) {
                PacketScore pScore = (PacketScore)object;

                Tier tier = pScore.tier == 0 ? Tier.FIRST : pScore.tier == 1 ?
                                                            Tier.SECOND : pScore.tier == 2 ? Tier.THIRD : pScore.tier == 3 ? Tier.FOUR : Tier.PLAYER_TANK;

                Parameters.playerScore += tier.score;

                if(tier != Tier.PLAYER_TANK)
                    Parameters.enemiesKilledByPlayer[pScore.tier] += 1;
            }
            else if(object instanceof PacketEvent) {
                PacketEvent pEvent = (PacketEvent)object;

                final float volume = pEvent.getData() == getID() ?
                                     Parameters.volume : (Parameters.volume * 0.5f);

                switch(pEvent.event) {
                    case ON_TANK_SHOOT: {
                        AssetsGame.manager().soundManager.
                                getSound("bulletShoot").play(false, volume);
                        break;
                    }
                    case ON_WORLD_GAMEOVER: {
                        Eagle eagle = (Eagle)getWorld().getCurrentLevel().
                                getEagle();

                        if(eagle != null)
                            eagle.kill();
                        break;
                    }
                    case ON_PLAYER_GAMEOVER: {
                        pTank.setState(State.DEAD);
                        break;
                    }
                    case ON_POWERUP_PICKUP: {
                        AssetsGame.manager().soundManager.getSound("powerup").
                                play(false, volume);
                        break;
                    }
                    case ON_LIFE_PICKUP: {
                        AssetsGame.manager().soundManager.getSound("playerLife").
                                play(false, volume);
                        break;
                    }
                    case ON_POWERUP_SHOW: {
                        AssetsGame.manager().soundManager.getSound("powerShow").
                                play();
                        break;
                    }
                    case ON_POWERUP_SHOVEL_PICKUP: {
                        Task powerShovelEvent = new PowerShovelEvent();
                        World.getTimerManager().
                                scheduleTask(powerShovelEvent, 1,
                                             1);
                        break;
                    }
                    case ON_POWERUP_TANK_PICKUP: {
                        pTank.addPlayerLife();
                        break;
                    }
                    case ON_PLAYER_LOST_LIFE: {
                        pTank.removePlayerLife();

                        if(Parameters.mode == BaseLevel.Mode.DEATHMATCH)
                            Parameters.deaths += 1;
                        break;
                    }
                    case ON_ENEMY_HIT: {
                        AssetsGame.manager().soundManager.getSound("enemyHit").
                                play(false, volume);
                        break;
                    }
                    case ON_BULLET_DEAD: {
                        AssetsGame.manager().soundManager.getSound("bulletDead").
                                play(false, volume);
                        break;
                    }
                    case ON_ENEMY_SPAWNED: {
                        int amount = Parameters.totalEnemiesCount - 1;
                        Parameters.totalEnemiesCount = amount < 0 ? 0 : amount;
                        break;
                    }
                    case ON_NEXT_LEVEL: {
                        AssetsGame.manager().soundManager.getSound("tankIdle").
                                stop();
                        AssetsGame.manager().soundManager.getSound("tankMoving").
                                stop();

                        if(Server.isServerRunning()) {
                            Gdx.app.postRunnable(new Runnable()
                            {
                                @Override
                                public void run() {
                                    BaseLevel level = Server.getInstance().getWorld().
                                            getCurrentLevel();
                                    getWorld().changeLevel(level);
                                }
                            });

                            return;
                        }

                        final int levelID = pEvent.getData();
                        final BaseLevel next = BaseLevel.getLevel(levelID);

                        if(pTank.getPlayerLives() == 0 && getWorld().getCurrentLevel().getMode() != BaseLevel.Mode.DEATHMATCH) {
                            Gdx.app.postRunnable(new Runnable()
                            {
                                @Override
                                public void run() {
                                    Client.instance().error = ResponseCode.GAME_OVER;
                                    ScreenScore screen = new ScreenScore();
                                    CoreBC.changeScreen(screen);
                                }
                            });

                            return;
                        }

                        Gdx.app.postRunnable(new Runnable()
                        {
                            @Override
                            public void run() {
                                if(next == null) {
                                    Client.instance().error = ResponseCode.INVALID_LEVEL;
                                    ScreenMainMenu screen = new ScreenMainMenu();
                                    CoreBC.changeScreen(screen);
                                    return;
                                }

                                getWorld().changeLevel(next);
                            }
                        });

                        break;
                    }
                    case ON_WORLD_INIT: {
                        ((ClientWorld)world).initWorld();
                        pTank.setState(State.DEFAULT);

                        int levelID = getWorld().getCurrentLevel().
                                getLevelID();

                        Parameters.lastLevel = levelID == 0 ? Parameters.lastLevel : levelID;

                        break;
                    }
                    case ON_CONNECTION_REFUSED: {
                        error = ResponseCode.SERVER_FULL;
                        shutdown();

                        Gdx.app.postRunnable(new Runnable()
                        {
                            @Override
                            public void run() {
                                ScreenMainMenu screen = new ScreenMainMenu();
                                CoreBC.changeScreen(screen);
                            }
                        });
                        break;
                    }
                }
            }
            else if(object instanceof PacketServerStatus) {
                PacketServerStatus pServer = (PacketServerStatus)object;
                switch(pServer.getType()) {
                    case CONNECTED: {
                        pTank.setPlayerLives(pServer.playerLives);
                        Parameters.startingLevel = pServer.currentLevelID;
                        Parameters.totalEnemiesCount = pServer.currentEnemies;

                        final Runnable r = new Runnable()
                        {
                            @Override
                            public void run() {
                                if(!getWorld().isWorldInitiated()) {
                                    final BaseLevel lvl;

                                    if(Server.isServerRunning()) {
                                        lvl = Server.getInstance().getWorld().
                                                getCurrentLevel();
                                    }
                                    else {
                                        lvl = BaseLevel.getLevel(
                                                Parameters.startingLevel);
                                    }
                                    getWorld().changeLevel(lvl);

                                    if(pServer.worldInitiated) {
                                        getWorld().initWorld();
                                    }

                                    System.out.println("Level recieved: " + lvl.
                                                       getLevelID());
                                }
                            }
                        };

                        Gdx.app.postRunnable(r);
                    }

                    break;
                }
            }
        }
    };

}
