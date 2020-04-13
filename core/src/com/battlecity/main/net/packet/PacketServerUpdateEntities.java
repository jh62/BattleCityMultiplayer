package com.battlecity.main.net.packet;

import com.battlecity.main.entity.Entity;
import java.util.ArrayList;

/**
 *
 * @author Pablis
 */
public class PacketServerUpdateEntities extends Packet {

    public ArrayList<Entity> entityList;

    protected PacketServerUpdateEntities() {

    }
}
