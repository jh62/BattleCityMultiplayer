package com.battlecity.main.render;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.battlecity.main.assets.AssetsGame;
import com.battlecity.main.entity.Entity;
import com.battlecity.main.entity.tiles.Tile;

/**
 *
 * @author JH62
 */
public class EagleRenderer extends RenderEntity {

    @Override
    public void render(Entity e) {
        Tile tile = (Tile) e;

        batch.end();
        spRender.setAutoShapeType(true);
        spRender.setProjectionMatrix(batch.getProjectionMatrix());
        spRender.begin();
        spRender.set(ShapeRenderer.ShapeType.Filled);
        spRender.setColor(Color.BLACK);
        spRender.box(tile.getX(), tile.getY(), 0, tile.getWidth(),
                tile.getHeight(),
                0);
        spRender.end();
        batch.begin();

        boolean isAlive = tile.isAlive();
        Sprite sprite = getTexture(isAlive);

        sprite.setPosition(tile.getX(), tile.getY());
        sprite.draw(batch);
    }

    private Sprite getTexture(boolean isAlive) {
        if (isAlive) {
            return AssetsGame.manager().textures.get("eagle_full");
        } else {
            return AssetsGame.manager().textures.get("eagle_destroyed");
        }
    }
}
