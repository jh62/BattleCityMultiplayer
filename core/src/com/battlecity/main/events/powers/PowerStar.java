package com.battlecity.main.events.powers;

import com.battlecity.main.global.enums.Power;

/**
 *
 * @author JH62
 */
public class PowerStar extends PowerUP {

    PowerStar() {

    }

    public PowerStar(float x, float y) {
        super(x, y);
    }

    @Override
    public Power getPowerUp() {
        return Power.STAR;
    }
}
