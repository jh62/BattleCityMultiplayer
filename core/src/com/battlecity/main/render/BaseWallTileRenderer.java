package com.battlecity.main.render;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.battlecity.main.assets.AssetsGame;
import com.battlecity.main.entity.Entity;
import com.battlecity.main.entity.tiles.BaseWall;
import com.battlecity.main.entity.tiles.Tile;
import com.battlecity.main.entity.tiles.TileRegion;

/**
 *
 * @author JH62
 */
public class BaseWallTileRenderer extends TileRenderer {

    @Override
    public void render(Entity e) {

        BaseWall tile = (BaseWall) e;

        if (!tile.isProtected() || !tile.isVisible()) {
            super.render(e);
        } else {

            TextureRegion tr = AssetsGame.manager().textures.get("iron_single");

            for (TileRegion t : tile.getRegions()) {
                Rectangle bounds = t.getBounds();
                Batch.BATCH.draw(tr, bounds.x, bounds.y, tr.getRegionX(), tr.getRegionY(), bounds.width,
                        bounds.height, 1, 1, 0, false);
            }
        }
    }
}
