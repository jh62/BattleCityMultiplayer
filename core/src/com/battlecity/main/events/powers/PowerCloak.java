package com.battlecity.main.events.powers;

import com.battlecity.main.entity.*;
import com.battlecity.main.entity.tank.*;
import com.battlecity.main.global.enums.*;
import com.battlecity.main.net.*;



/**
 *
 * @author Pablo Da Costa Leite
 */
public class PowerCloak extends PowerUP
{

    PowerCloak() {

    }

    public PowerCloak(float x, float y) {
        super(x, y);
    }

    @Override
    public Power getPowerUp() {
        return Power.CLOAK;
    }
}
