package com.battlecity.main.render;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.math.*;
import com.battlecity.main.assets.AssetsGame;
import com.battlecity.main.entity.Entity;
import com.battlecity.main.entity.tiles.*;
import com.battlecity.main.global.*;



/**
 *
 * @author Pablis
 */
public class TileRenderer extends RenderEntity
{
    private static float state = 0f;

    @Override
    public void render(Entity e) {
        Tile tile = (Tile)e;

        state += Gdx.graphics.getDeltaTime();

        if(!tile.isVisible())
            return;

        Sprite sp = null;

        switch(tile.getTileType()) {
            case BRICKS:
                sp = AssetsGame.manager().textures.get("wall_single");
                break;
            case EAGLE_WALL:
                BaseWall bs = (BaseWall)e;
                if(bs.isProtected())
                    sp = AssetsGame.manager().textures.get("iron_single");
                else
                    sp = AssetsGame.manager().textures.get("wall_single");
                break;
            case ICE:
                sp = AssetsGame.manager().textures.get("ice_full");
                sp.setPosition(tile.getBounds().x, tile.getBounds().y);
                sp.setSize(tile.getBounds().width, tile.getBounds().height);
                sp.draw(batch);
                return;
            case TREES:
                sp = AssetsGame.manager().textures.get("grass_full");
                sp.setPosition(tile.getBounds().x, tile.getBounds().y);
                sp.setSize(tile.getBounds().width, tile.getBounds().height);
                sp.draw(batch);
                return;
            case WALL:
                sp = AssetsGame.manager().textures.get("iron_single");
                break;
            case WATER:
                Animation anim = AssetsGame.manager().animations.get("water_full");
                TextureRegion tr = anim.getKeyFrame(state, true);
                batch.draw(tr, tile.getX(), tile.getY());
                return;
            default:
                break;
        }

        if(sp != null) {
            for(TileRegion tr : tile.getRegions()) {
                sp.setPosition(tr.getBounds().x, tr.getBounds().y);
                sp.setSize(tr.getBounds().width, tr.getBounds().height);
                sp.draw(batch);
            }
        }

        if(Presets.debugOn) {
            batch.end();
            spRender.setAutoShapeType(true);
            spRender.setProjectionMatrix(batch.getProjectionMatrix());
            spRender.begin();
            spRender.set(ShapeRenderer.ShapeType.Line);
            spRender.setColor(Color.RED);
            Rectangle bounds = tile.getBounds();
            spRender.box(bounds.x, bounds.y, 0, bounds.width,
                         bounds.height,
                         0);
            spRender.end();
            batch.begin();
        }
    }

}
