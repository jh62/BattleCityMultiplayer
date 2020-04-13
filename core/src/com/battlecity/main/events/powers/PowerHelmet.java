package com.battlecity.main.events.powers;

import com.battlecity.main.global.enums.Power;

/**
 *
 * @author JH62
 */
public class PowerHelmet extends PowerUP {

    PowerHelmet() {

    }

    public PowerHelmet(float x, float y) {
        super(x, y);
    }

    @Override
    public Power getPowerUp() {
        return Power.HELMET;
    }
}
