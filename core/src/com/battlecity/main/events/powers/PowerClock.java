package com.battlecity.main.events.powers;

import com.battlecity.main.entity.Entity;
import com.battlecity.main.events.TimerManager.Task;
import com.battlecity.main.global.enums.Power;
import com.battlecity.main.net.Server;
import com.battlecity.main.world.*;

/**
 *
 * @author JH62
 */
public class PowerClock extends PowerUP {

    PowerClock() {

    }

    public PowerClock(float x, float y) {
        super(x, y);
    }

    @Override
    public Power getPowerUp() {
        return Power.CLOCK;
    }

    @Override
    void onPickUp(Entity entity) {
        final Task freezeEnemies = new Task() {
            @Override
            public void run() {
                ((ServerWorld) Server.getInstance().getWorld()).freezeEnemies(
                        false, entity);
            }
        };

        ((ServerWorld) Server.getInstance().getWorld()).freezeEnemies(true, entity);
        World.getTimerManager().scheduleTask(freezeEnemies, Power.CLOCK.duration);
    }
}
