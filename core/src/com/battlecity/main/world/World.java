package com.battlecity.main.world;

import com.badlogic.gdx.math.Rectangle;
import com.battlecity.main.levels.BaseLevel;
import com.battlecity.main.entity.Entity;
import com.battlecity.main.events.*;
import com.battlecity.main.global.*;
import com.battlecity.main.levels.*;
import java.util.ArrayList;



/**
 *
 * @author Pablis
 */
public abstract class World
{

    public abstract void addEntity(Entity e);

    public abstract ArrayList<Entity> getEntities();

    public abstract boolean isGameOver();

    public abstract void update();

    protected final Rectangle worldBounds;
    protected boolean isWorldInitiated = false;

    private static BaseLevel currentLevel = null;
    private static TimerManager WORLDTIMER;

    public World() {
        this.worldBounds = new Rectangle(-1, -1, Presets.WORLD_WIDTH + 1,
                                         Presets.WORLD_HEIGHT + 1);
    }

    public World(Rectangle bounds) {
        worldBounds = bounds;
    }

    public World(Rectangle bounds, BaseLevel level) {
        this(bounds);
        currentLevel = level;
    }

    public void dispose() {
        World.getTimerManager().stop();

        if(World.currentLevel != null) {
            World.currentLevel.dispose();
        }
    }

    public static TimerManager getTimerManager() {
        if(World.WORLDTIMER == null || World.WORLDTIMER.isShutdown()) {
            World.WORLDTIMER = new TimerManager();
        }
        return World.WORLDTIMER;
    }

    public void setWorldBounds(float width, float height) {
        this.worldBounds.width = width;
        this.worldBounds.height = height;
    }

    public Rectangle getWorldBounds() {
        return worldBounds;
    }

    public float getWidth() {
        return worldBounds.width;
    }

    public float getHeight() {
        return worldBounds.height;
    }

    public BaseLevel getCurrentLevel() {
        return World.currentLevel;
    }

    public void changeLevel(BaseLevel level) {
        if(World.currentLevel != null) {
            World.currentLevel.dispose();
        }
        World.currentLevel = level;
        World.currentLevel.init();

        if(World.currentLevel.getMode() == BaseLevel.Mode.DEATHMATCH)
            Parameters.friendlyFire = true;

        Parameters.totalEnemiesCount = level.getSpawnList().length;
    }

    public boolean isWorldInitiated() {
        return this.isWorldInitiated && (World.currentLevel != null);
    }

}
