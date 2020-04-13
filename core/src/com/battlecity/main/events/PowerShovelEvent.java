package com.battlecity.main.events;

import com.battlecity.main.entity.tiles.BaseWall;
import com.battlecity.main.entity.tiles.Tile;
import com.battlecity.main.events.TimerManager.Task;
import com.battlecity.main.global.enums.Power;
import com.battlecity.main.levels.*;
import com.battlecity.main.net.Client;

/**
 *
 * @author JH62
 */
public final class PowerShovelEvent extends Task {

    private static final int POWER_FADE_TIME = Power.SHOVEL.duration / 2;
    private static final int VISIBILITY_TOGGLE_DELAY = 300;

    private int timeElapsed = 0;
    private int visibilityTimer = 0;

    public PowerShovelEvent() {
        timeElapsed = 0;
        visibilityTimer = 0;
        for (Tile t
                : Client.instance().getWorld().getCurrentLevel().baseWallsTiles) {
            ((BaseWall) t).setProtected(true);
        }
    }

    @Override
    public void run() {
        timeElapsed++;

        if (timeElapsed < POWER_FADE_TIME) {
            return;
        }

        if (timeElapsed >= Power.SHOVEL.duration ||
                !Client.instance().getWorld().isWorldInitiated()) {
            for (Tile t
                    : Client.instance().getWorld().getCurrentLevel().baseWallsTiles) {
                t.setVisible(true);
                ((BaseWall) t).setProtected(false);
            }
            this.cancel();
        } else {
            visibilityTimer++;
            if (visibilityTimer > VISIBILITY_TOGGLE_DELAY) {
                for (Tile t
                        : Client.instance().getWorld().getCurrentLevel().baseWallsTiles) {
                    t.setVisible(!t.isVisible());
                }
                visibilityTimer = 0;
            }
        }
    }
}
