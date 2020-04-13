package com.battlecity.main.events.powers;

import com.battlecity.main.global.enums.*;



/**
 *
 * @author Pablo Da Costa Leite
 */
public class PowerHealth extends PowerUP
{

    PowerHealth() {

    }

    public PowerHealth(float x, float y) {
        super(x, y);
    }

    @Override
    public Power getPowerUp() {
        return Power.HEALTH;
    }

}
