package com.battlecity.main.events.powers;

import com.battlecity.main.entity.Entity;
import com.battlecity.main.entity.tank.PlayerTank;
import com.battlecity.main.global.enums.Event;
import com.battlecity.main.global.enums.Power;
import com.battlecity.main.net.Server;

/**
 *
 * @author JH62
 */
public class PowerTank extends PowerUP {

    PowerTank() {

    }

    public PowerTank(float x, float y) {
        super(x, y);
    }

    @Override
    void onPickUp(Entity entity) {
        if (entity instanceof PlayerTank) {
            ((PlayerTank) entity).addPlayerLife();
            Server.getInstance().sendEventToPlayer(Event.ON_POWERUP_TANK_PICKUP,
                    ((PlayerTank) entity).getPlayerID(),
                    ((PlayerTank) entity).getPlayerID());
        }
    }

    @Override
    public Power getPowerUp() {
        return Power.TANK;
    }
}
