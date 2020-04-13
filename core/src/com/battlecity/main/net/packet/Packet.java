package com.battlecity.main.net.packet;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablis
 */
public abstract class Packet {

    public static enum TYPE {
        CONNECTED,
        DISCONNECTED,
        UPDATE;
    }

    private int data;
    private TYPE packetType = TYPE.UPDATE;

    protected Packet() {

    }

    public static Packet createPacket(Class<? extends Packet> packet,
            TYPE packetType, int id) {
        try {
            Packet p = packet.newInstance();
            p.packetType = packetType;
            p.data = id;
            return p;
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Packet.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public TYPE getType() {
        return this.packetType;
    }

    public int getData() {
        return this.data;
    }
}
