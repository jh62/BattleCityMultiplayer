package com.battlecity.main.render;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.*;
import com.battlecity.main.assets.AssetsGame;
import com.battlecity.main.entity.Entity;
import com.battlecity.main.entity.tank.Tank;
import com.battlecity.main.global.Presets;



/**
 *
 * @author Pablis
 */
public abstract class TankRenderer extends RenderEntity
{

    public abstract TextureRegion getStateSprite(Tank tank);
    Sprite sp = new Sprite();

    @Override
    public void render(Entity e) {

        if(!e.isAlive()) {
            return;
        }

        if(!e.isVisible()) {
            return;
        }

        Tank t = (Tank)e;

        sp = getDefaultAnimationForState(t);

        float rotation = .0f;

        switch(t.getFacing()) {
            case UP: {
                rotation = 0f;
                break;
            }
            case DOWN: {
                rotation = 180.0f;
                break;
            }
            case RIGHT: {
                rotation = 270.0f;
                break;
            }
            case LEFT: {
                rotation = 90.0f;
                break;
            }
        }

        Vector2 center = t.getBounds().getCenter(Vector2.Zero);
        sp.setPosition(t.getX(), t.getY());
        sp.setCenter(center.x, center.y);
        sp.setRotation(rotation);
        sp.draw(batch);

        if(Presets.debugOn) {
            batch.end();
            spRender.setAutoShapeType(true);
            spRender.setColor(Color.CLEAR);
            spRender.setProjectionMatrix(batch.getProjectionMatrix());
            spRender.begin();
            spRender.set(ShapeRenderer.ShapeType.Line);
            spRender.setColor(Color.LIGHT_GRAY);
            Rectangle bounds = t.getBounds();
            spRender.box(bounds.x, bounds.y, 0, bounds.width,
                         bounds.height,
                         0);
            spRender.end();
            batch.begin();
        }
    }

    private Sprite getDefaultAnimationForState(Tank tank) {
        TextureRegion tr;

        switch(tank.getState()) {
            case RESPAWNING: {
                tr = AssetsGame.manager().animations.
                        get(
                                "respawn").getKeyFrame(tank.state(), false);
                break;
            }
            case EXPLODING: {
                tr = AssetsGame.manager().animations.
                        get(
                                "explosion").getKeyFrame(tank.state(), false);
                break;
            }
            default: {
                tr = getStateSprite(tank);
                break;
            }
        }

        sp.setRegion(tr);
        sp.setSize(tr.getRegionWidth(), tr.getRegionHeight());
        sp.setOrigin(sp.getWidth() / 2, sp.getHeight() / 2);

        return sp;
    }

}
