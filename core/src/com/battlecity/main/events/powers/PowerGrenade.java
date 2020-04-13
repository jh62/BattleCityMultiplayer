package com.battlecity.main.events.powers;

import com.battlecity.main.entity.DynamicEntity;
import com.battlecity.main.entity.Entity;
import com.battlecity.main.entity.tank.*;
import com.battlecity.main.global.enums.Power;
import com.battlecity.main.levels.*;
import com.battlecity.main.net.Server;



/**
 *
 * @author JH62
 */
public class PowerGrenade extends PowerUP
{

    PowerGrenade() {

    }

    public PowerGrenade(float x, float y) {
        super(x, y);
    }

    @Override
    void onPickUp(Entity entity) {
        for(Entity e : Server.getInstance().getWorld().getEntities()) {
            if(e instanceof EnemyTank || Server.getInstance().getWorld().getCurrentLevel().getMode() == BaseLevel.Mode.DEATHMATCH) {
                if(!(e instanceof Tank) || e == entity)
                    continue;

                ((Tank)e).setState(DynamicEntity.State.EXPLODING);
            }
        }
    }

    @Override
    public Power getPowerUp() {
        return Power.GRENADE;
    }

}
