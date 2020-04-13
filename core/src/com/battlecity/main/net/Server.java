package com.battlecity.main.net;

import com.battlecity.main.entity.*;
import com.battlecity.main.entity.DynamicEntity.State;
import com.battlecity.main.entity.tank.PlayerTank;
import com.battlecity.main.entity.tank.Tank;
import com.battlecity.main.entity.tiles.Tile;
import com.battlecity.main.entity.tiles.TileRegion;
import com.battlecity.main.global.*;
import com.battlecity.main.net.packet.Packet;
import static com.battlecity.main.net.packet.Packet.TYPE.CONNECTED;
import static com.battlecity.main.net.packet.Packet.TYPE.DISCONNECTED;
import static com.battlecity.main.net.packet.Packet.TYPE.UPDATE;
import com.battlecity.main.net.packet.PacketEntity;
import com.battlecity.main.net.packet.PacketRegistry;
import com.battlecity.main.net.packet.PacketServerUpdateEntities;
import com.battlecity.main.net.packet.PacketTileData;
import com.battlecity.main.global.enums.Event;
import com.battlecity.main.levels.*;
import com.battlecity.main.net.packet.*;
import com.battlecity.main.world.*;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.io.IOException;
import java.util.regex.*;



/**
 *
 * @author Pablis
 */
public class Server extends com.esotericsoftware.kryonet.Server
{

    public static final int TCP = 2596;
    public static final int UDP = 2597;

    public static boolean localCoop = false;

    private static Server instance;

    private ServerWorld world;

    private static boolean isRunning = false;

    private Server() {
    }

    public static Server getInstance() {
        if(Server.instance == null) {
            Server.instance = new Server();
        }

        return Server.instance;
    }

    public void initialize() throws IOException {
        initialize(false);
    }

    public void initialize(boolean twoPlayers) throws IOException {
        PacketRegistry.registerPackets(this.getKryo());
        this.addListener(serverListener);
        this.start();
        this.bind(Server.TCP, Server.UDP);
        this.world = new ServerWorld();
        Server.isRunning = true;
        localCoop = twoPlayers;
    }

    public ServerWorld getWorld() {
        return this.world;
    }

    public static boolean isServerRunning() {
        return Server.instance != null && Server.isRunning;
    }

    /**
     * Properly shuts down the world.
     */
    public void shutdownServer() {
        super.stop();
        super.close();

        Server.isRunning = false;
        Server.instance = null;

        this.world.dispose();

        System.out.println("Server is offline.");
    }

    public void update() {
        if(!this.world.isWorldInitiated()) {
            return;
        }

        this.world.update();
        sendEntityUpdates();
    }

    private void sendEntityUpdates() {
        if(world.getEntities().isEmpty()) {
            return;
        }

        PacketServerUpdateEntities pEntity = (PacketServerUpdateEntities)Packet.
                createPacket(PacketServerUpdateEntities.class,
                             Packet.TYPE.UPDATE, -1);
        pEntity.entityList = world.getEntities();

        for(Connection c : Server.this.getConnections()) {
            c.sendUDP(pEntity);
        }
    }

    private void sendTileUpdateToPlayer(int playerID) {
        PacketTileData pTileData = (PacketTileData)Packet.createPacket(
                PacketTileData.class, CONNECTED, -1);

        for(Tile tile : getWorld().getCurrentLevel().damagedTileList) {

            pTileData.tileID = tile.tileID;

            for(TileRegion region : tile.getRegions()) {
                pTileData.tileRegion = region;
                sendToTCP(playerID, pTileData);
            }
        }
    }

    public void sendEventToAll(Event event, int from) {
        PacketEvent pEvent = (PacketEvent)Packet.
                createPacket(PacketEvent.class, UPDATE, from);
        pEvent.event = event;
        sendToAllTCP(pEvent);
    }

    public void sendEventToPlayer(Event event, int from, int toPlayer) {
        PacketEvent pEvent = (PacketEvent)Packet.
                createPacket(PacketEvent.class, UPDATE, from);
        pEvent.event = event;
        sendToTCP(toPlayer, pEvent);
    }

    public void sendStateToPlayer(int connection) {
        PacketServerStatus pOptions = (PacketServerStatus)Packet.createPacket(
                PacketServerStatus.class, CONNECTED, -1);
        pOptions.currentLevelID = getWorld().getCurrentLevel().getLevelID();
        pOptions.playerLives = ((PlayerTank)getWorld().getPlayer(connection)).
                getPlayerLives();
        pOptions.currentEnemies = getWorld().totalEnemies();
        pOptions.worldInitiated = getWorld().isWorldInitiated();
        pOptions.arcade = getWorld().getCurrentLevel().getMode() == BaseLevel.Mode.ARCADE;
        sendToTCP(connection, pOptions);
    }

    public void sendStateToAll() {
        PacketServerStatus pOptions = (PacketServerStatus)Packet.createPacket(
                PacketServerStatus.class, CONNECTED, -1);

        pOptions.currentLevelID = getWorld().getCurrentLevel().getLevelID();
        pOptions.currentEnemies = getWorld().totalEnemies();
        pOptions.worldInitiated = getWorld().isWorldInitiated();

        for(Connection c : getConnections()) {
            int id = c.getID();
            pOptions.playerLives = ((PlayerTank)getWorld().getPlayer(id)).
                    getPlayerLives();
            sendToTCP(id, pOptions);
        }
    }

    public void sendPlayerScoreUpdate(int playerID, int tier) {
        PacketScore pScore = (PacketScore)Packet.createPacket(
                PacketScore.class, Packet.TYPE.UPDATE, -1);
        pScore.tier = tier;
        sendToTCP(playerID, pScore);
    }

    Pattern cmd_pattern = Pattern.compile("(?s:(?<cmd>[/].*?)\\s(?<trg>.*?)\\s)", Pattern.CASE_INSENSITIVE);

    private Entity getCMDTarget(int targetid) {
        for(Connection c : getConnections()) {
            if(targetid == c.getID()) {
                return world.getPlayer(targetid);
            }
        }

        return null;
    }

    private Entity getCMDTarget(String name) {
        for(Entity p : world.getPlayers()) {
            PlayerTank pTank = (PlayerTank)p;

            if(pTank.getName().equalsIgnoreCase(name)) {
                return pTank;
            }
        }

        return null;
    }

    private final Listener serverListener = new Listener()
    {

        @Override
        public void connected(Connection connection) {

            if(getWorld().isGameOver() ||
               (getWorld().getPlayers().size() >= 2 &&
                (Parameters.restrictJoin || localCoop))) {
                sendEventToPlayer(Event.ON_CONNECTION_REFUSED, -1, connection.
                                  getID());
                return;
            }

            int playerID = connection.getID();

            PacketPlayerID pid = (PacketPlayerID)Packet.createPacket(PacketPlayerID.class, CONNECTED, playerID);
            pid.playerID = playerID;

            ((ServerWorld)world).addPlayer(playerID);
            sendStateToPlayer(playerID);
            sendTileUpdateToPlayer(playerID);
            connection.sendTCP(pid);
            System.out.println("Client connected! ID# " + playerID);

            if(localCoop) {
                playerID = playerID + 1;
                PlayerTank pTank = ((ServerWorld)world).addPlayer(playerID);
                pTank.setName("Player #2");
                Client.instance().local.add(pTank);
                System.out.println("Client connected! ID# " + playerID);
            }
        }

        @Override
        public void disconnected(Connection connection) {
            int playerID = connection.getID();

            PlayerTank pTank = (PlayerTank)getWorld().getPlayer(playerID);

            if(pTank != null) {
                pTank.setState(State.DEAD);
                getWorld().removePlayer(playerID);
                System.out.println("Client disconnected! ID#" + connection.
                                   getID());
            }
        }

        @Override
        public void received(Connection connection, Object object) {
            if(object instanceof PacketEntity) {
                PacketEntity pEntity = (PacketEntity)object;

                PlayerTank pTank = (PlayerTank)((ServerWorld)world).
                        getPlayer(
                                connection.getID());

                switch(pEntity.getType()) {
                    case CONNECTED: {
                        pTank.setName(pEntity.name);
                        break;
                    }
                    case DISCONNECTED: {
                        break;
                    }
                    case UPDATE: {
                        if(getWorld().isGameOver()) {
                            pTank.setState(State.STOPPED);
                            pTank.setAction(Tank.Action.NORMAL);
                            return;
                        }

                        if(pTank.isAlive()) {

                            switch(pTank.getState()) {
                                case RESPAWNING:
                                case EXPLODING:
                                case EXPLODED: {
                                    return;
                                }
                            }

                            if(pTank.getState() != pEntity.state) {
                                pTank.setState(pEntity.state);
                            }

                            if(pTank.getFacing() != pEntity.facing) {
                                pTank.setFacing(pEntity.facing);
                            }

                            if(pEntity.action == Tank.Action.SHOOTING && !pTank.isShooting()) {
                                pTank.setAction(pEntity.action);
                            }

                            pTank.setName(pEntity.name);
                        }

//                        System.out.println("server recieved packetID #"
//                                + pEntity.getPacketID());
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
                        break;
                    }
                }
            }
            else if(object instanceof PacketChatText) {
                PacketChatText pChat = (PacketChatText)object;

                switch(pChat.getType()) {
                    default: {

                        if(pChat.text.startsWith("/")) {
                            
                            String mod = pChat.text.replaceAll(" ", "+");
                            Matcher m = Pattern.compile("(?<cmd>[/].*?)[+](?<trg>.*?)$").matcher(mod);

                            if(m.find()) {
                                String cmd = m.group("cmd").toLowerCase();
                                String target = m.group("trg").toLowerCase();
                                Entity targetEntity = null;

                                System.out.println("found cmd: " + cmd + " target: " + target);

                                try {
                                    int id = Integer.parseInt(target);
                                    targetEntity = getCMDTarget(id);
                                }
                                catch(NumberFormatException ex) {
                                    targetEntity = getCMDTarget(target);
                                }
                                
                                if(targetEntity == null) {
                                    connection.sendTCP("No such player with that id or name!");
                                    return;
                                }

                                switch(cmd) {
                                    case "/kill": {
                                        if(connection.getID() != 1) {
                                            pChat.name = "Server response";
                                            pChat.text= "You can't use that command!";
                                            connection.sendTCP(pChat);
                                            return;
                                        }

                                        ((PlayerTank)targetEntity).removePlayerLife();
                                        ((PlayerTank)targetEntity).setState(State.EXPLODING);
                                        Server.getInstance().sendEventToPlayer(Event.ON_PLAYER_LOST_LIFE,
                                                                               ((PlayerTank)targetEntity).getPlayerID(),
                                                                               ((PlayerTank)targetEntity).getPlayerID());
                                        break;
                                    }
                                }
                            }
                        }
                        else {
                            for(Connection c : getConnections()) {
                                c.sendTCP(pChat);
                            }
                        }
                        break;
                    }
                }
            }
        }
    };

}
