package com.battlecity.main.net.packet;

/**
 *
 * @author JH62
 */
public class PacketServerStatus extends Packet {

    public int playerLives;
    public int currentLevelID;
    public int currentEnemies;
    public boolean worldInitiated = false;
    public boolean arcade = false;

    PacketServerStatus() {

    }
}
