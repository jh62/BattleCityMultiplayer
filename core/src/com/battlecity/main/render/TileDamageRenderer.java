package com.battlecity.main.render;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.math.*;
import com.battlecity.main.entity.*;
import com.battlecity.main.entity.tiles.*;
import com.battlecity.main.global.*;



/**
 *
 * @author Pablo Da Costa Leite
 */
public class TileDamageRenderer extends RenderEntity
{
    @Override
    public void render(Entity e) {
        Tile tile = (Tile)e;

        if(!tile.isAlive() || !tile.isVisible()) {
            Rectangle bounds = tile.getBounds();

            spRender.set(ShapeRenderer.ShapeType.Filled);
            spRender.setColor(Color.BLACK);
            spRender.box(bounds.x, bounds.y, 0, bounds.width,
                         bounds.height,
                         0);
        }
        else {
            for(TileRegion t : tile.getRegions()) {
                Rectangle bounds = t.getBounds();

                if(Presets.debugOn) {
                    spRender.set(ShapeRenderer.ShapeType.Line);
                    spRender.setColor(Color.YELLOW);
                    spRender.box(bounds.x, bounds.y, 0, bounds.width,
                                 bounds.height,
                                 0);
                }

                if(t.isDestroyed()) {
                    spRender.set(ShapeRenderer.ShapeType.Filled);
                    spRender.setColor(Color.BLACK);
                    spRender.box(bounds.x, bounds.y, 0, bounds.width,
                                 bounds.height,
                                 0);
                }
                else if(t.isDamaged()) {
                    spRender.set(ShapeRenderer.ShapeType.Filled);
                    spRender.setColor(Color.BLACK);

                    if(t.isHitTop()) {
                        spRender.
                                box(bounds.x, bounds.y + (bounds.height *
                                                          0.5f),
                                    0, bounds.width,
                                    (bounds.height * 0.5f),
                                    0);
                    }
                    else if(t.isHitBottom()) {
                        spRender.box(bounds.x, bounds.y, 0, bounds.width,
                                     (bounds.height * 0.5f),
                                     0);
                    }
                    else if(t.isHitLeft()) {
                        spRender.box(bounds.x, bounds.y, 0,
                                     (bounds.width * 0.5f),
                                     bounds.height,
                                     0);
                    }
                    else if(t.isHitRight()) {
                        spRender.box(bounds.x + (bounds.width * 0.5f),
                                     bounds.y,
                                     0, (bounds.width * 0.5f),
                                     bounds.height,
                                     0);
                    }
                }
            }
        }
    }

}
