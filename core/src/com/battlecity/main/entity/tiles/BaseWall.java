package com.battlecity.main.entity.tiles;

import com.battlecity.main.entity.Entity;

/**
 *
 * @author JH62
 */
public class BaseWall extends Tile {

    private boolean isProtected = false;

    protected BaseWall() {

    }

    public BaseWall(float x, float y, RegionType regionType) {
        super(x, y, TileType.EAGLE_WALL, regionType);
    }

    public boolean isProtected() {
        return this.isProtected;
    }

    public void setProtected(boolean on) {
        this.isProtected = on;

        if (on) {
            for (TileRegion region : this.getRegions()) {
                region.repair();
            }
        }
    }

    @Override
    public boolean onCollision(Entity attacker) {
        return isProtected ? true : super.onCollision(attacker);
    }
}
