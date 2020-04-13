package com.battlecity.main.net.packet;

import com.badlogic.gdx.math.Rectangle;
import com.battlecity.main.entity.Bullet;
import com.battlecity.main.entity.DynamicEntity;
import com.battlecity.main.entity.Entity;
import com.battlecity.main.entity.tank.EnemyTank;
import com.battlecity.main.entity.tank.PlayerTank;
import com.battlecity.main.entity.tank.Tank;
import com.battlecity.main.events.powers.PowerUP;
import com.battlecity.main.entity.tiles.TileData;
import com.battlecity.main.entity.tiles.TileRegion;
import com.battlecity.main.events.powers.*;
import com.battlecity.main.global.enums.Event;
import com.battlecity.main.global.enums.Power;
import com.battlecity.main.global.enums.Tier;
import com.esotericsoftware.kryo.Kryo;
import java.util.ArrayList;
import java.util.LinkedList;



/**
 *
 * @author Pablis
 */
public final class PacketRegistry
{

    public static void registerPackets(Kryo kryo) {
        kryo.register(ArrayList.class);
        kryo.register(Bullet.class);
        kryo.register(DynamicEntity.Facing.class);
        kryo.register(DynamicEntity.SIDE.class);
        kryo.register(DynamicEntity.State.class);
        kryo.register(DynamicEntity.class);
        kryo.register(Event.class);
        kryo.register(EnemyTank.class);
        kryo.register(Entity.class);
        kryo.register(LinkedList.class);
        kryo.register(Power.class);
        kryo.register(Packet.TYPE.class);
        kryo.register(Packet.class);
        kryo.register(PacketChatText.class);
        kryo.register(PacketEntity.class);
        kryo.register(PacketEvent.class);
        kryo.register(PacketServerStatus.class);
        kryo.register(PacketServerUpdateEntities.class);
        kryo.register(PacketPlayerID.class);
        kryo.register(PacketTileData.class);
        kryo.register(PacketScore.class);
        kryo.register(PlayerTank.class);
        kryo.register(PowerGrenade.class);
        kryo.register(PowerHelmet.class);
        kryo.register(PowerShovel.class);
        kryo.register(PowerStar.class);
        kryo.register(PowerStar.class);
        kryo.register(PowerTank.class);
        kryo.register(PowerClock.class);
        kryo.register(PowerCloak.class);
        kryo.register(PowerHealth.class);
        kryo.register(PowerBounce.class);
        kryo.register(PowerUP.class);
        kryo.register(Rectangle.class);
        kryo.register(Tier.class);
        kryo.register(Tank.Action.class);
        kryo.register(TileData.class);
        kryo.register(TileRegion.class);
        kryo.register(TileRegion[].class);
//        kryo.register(int.class);
    }

}
