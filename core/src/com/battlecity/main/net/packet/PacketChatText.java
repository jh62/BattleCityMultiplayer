package com.battlecity.main.net.packet;

/**
 *
 * @author JH62
 */
public class PacketChatText extends Packet
{

    public String name = null;
    public String text = null;

    protected PacketChatText() {

    }

    @Override
    public String toString() {
        return name + ": " + text;
    }

}
