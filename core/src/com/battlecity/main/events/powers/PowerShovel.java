package com.battlecity.main.events.powers;

import com.battlecity.main.entity.Entity;
import com.battlecity.main.entity.tank.*;
import com.battlecity.main.global.enums.Event;
import com.battlecity.main.global.enums.Power;
import com.battlecity.main.net.Server;

/**
 *
 * @author JH62
 */
public class PowerShovel extends PowerUP {

    PowerShovel() {

    }

    public PowerShovel(float x, float y) {
        super(x, y);
    }

    @Override
    void onPickUp(Entity entity) {
        Server.getInstance().sendEventToAll(Event.ON_POWERUP_SHOVEL_PICKUP,
                ((PlayerTank) entity).getPlayerID());
    }

    @Override
    public Power getPowerUp() {
        return Power.SHOVEL;
    }
}
