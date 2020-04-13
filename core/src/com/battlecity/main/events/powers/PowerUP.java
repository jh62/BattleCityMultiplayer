package com.battlecity.main.events.powers;

import com.battlecity.main.entity.Entity;
import com.battlecity.main.entity.tank.PlayerTank;
import com.battlecity.main.events.TimerManager.Task;
import com.battlecity.main.global.Presets;
import com.battlecity.main.global.enums.Event;
import com.battlecity.main.global.enums.Power;
import com.battlecity.main.net.Server;
import com.battlecity.main.world.*;

/**
 *
 * @author JH62
 */
public abstract class PowerUP extends Entity {

    transient private boolean isAlive = true;

    public abstract Power getPowerUp();

    PowerUP() {

    }

    public PowerUP(float x, float y) {
        super(x, y, 16, 16);
    }

    @Override
    public void onSpawn() {
        super.onSpawn();
        Server.getInstance().sendEventToAll(Event.ON_POWERUP_SHOW, -1);
    }

    @Override
    public final boolean onCollision(Entity attacker) {
        if (attacker instanceof PlayerTank) {
            onPickUp(attacker);
            destroy();

            if (this.getPowerUp() == Power.TANK) {
                Server.getInstance().sendEventToAll(Event.ON_LIFE_PICKUP,
                        ((PlayerTank) attacker).getPlayerID());
            } else {
                Server.getInstance().sendEventToAll(Event.ON_POWERUP_PICKUP,
                        ((PlayerTank) attacker).getPlayerID());
            }
        }
        return false;
    }

    /**
     * Called after an entity walks over this powerup.
     *
     * @param entity - the entity that picked up the powerup.
     */
    void onPickUp(Entity entity) {
        ((PlayerTank) entity).setPowerUp(getPowerUp());

        final Task task = new Task() {
            @Override
            public void run() {
                if (isAlive()) {
                    destroy();
                }
            }
        };

        World.getTimerManager().scheduleTask(task, Presets.POWERUP_DURATION);
    }

    public void destroy() {
        this.isAlive = false;
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }
}
