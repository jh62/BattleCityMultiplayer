package com.battlecity.main.net.packet;

import com.battlecity.main.entity.DynamicEntity.State;
import com.battlecity.main.entity.Entity.Facing;
import com.battlecity.main.entity.tank.Tank.Action;

/**
 *
 * @author Pablis
 */
public class PacketEntity extends Packet {

//    public float x = 0;
//    public float y = 0;
    public String name = "";
    public Facing facing = Facing.UP;
    public State state = State.STOPPED;
    public Action action = Action.NORMAL;

    protected PacketEntity() {

    }

}
