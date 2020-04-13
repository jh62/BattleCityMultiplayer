package com.battlecity.main.render;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.battlecity.main.assets.AssetsGame;
import com.battlecity.main.entity.Entity;
import com.battlecity.main.entity.tiles.Tile;

/**
 *
 * @author JH62
 */
public class TileSpecialRenderer extends RenderEntity {

    @Override
    public void render(Entity e) {
        Tile tile = (Tile) e;

        if (!tile.isVisible()) {
            return;
        }

        switch (tile.getTileType()) {
            case TREES: {
                Sprite sp = AssetsGame.manager().textures.get("grass_full");
                sp.setPosition(tile.getX(), tile.getY());
                sp.draw(batch);
                break;
            }
        }
    }
}
