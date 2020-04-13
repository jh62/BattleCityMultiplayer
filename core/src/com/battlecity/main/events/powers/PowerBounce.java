package com.battlecity.main.events.powers;

import com.battlecity.main.entity.*;
import com.battlecity.main.global.enums.*;



/**
 *
 * @author Pablo Da Costa Leite
 */
public class PowerBounce extends PowerUP
{
    PowerBounce() {

    }

    public PowerBounce(float x, float y) {
        super(x, y);
    }

    @Override
    public Power getPowerUp() {
        return Power.BOUNCE;
    }
}
