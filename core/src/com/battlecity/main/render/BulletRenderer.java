/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.battlecity.main.render;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.battlecity.main.assets.AssetsGame;
import com.battlecity.main.entity.Bullet;
import com.battlecity.main.entity.DynamicEntity.State;
import com.battlecity.main.entity.Entity;
import com.battlecity.main.global.Presets;

/**
 *
 * @author Pablis
 */
public class BulletRenderer extends RenderEntity {

    @Override
    public void render(Entity e) {
        Bullet b = (Bullet) e;

        if (b.getState() == State.MOVING) {
            float rotation = 0;

            switch (b.getFacing()) {
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

            Vector2 center = b.getBounds().getCenter(Vector2.Zero);

            Sprite bullet = AssetsGame.manager().textures.get("bullet");
            bullet.setPosition(b.getX(), b.getY());
            bullet.setCenter(center.x, center.y);
            bullet.setRotation(rotation);
            bullet.draw(batch);

            if (Presets.debugOn) {
                batch.end();
                spRender.setProjectionMatrix(batch.getProjectionMatrix());
                spRender.begin();
                spRender.set(ShapeRenderer.ShapeType.Line);
                spRender.setColor(Color.LIGHT_GRAY);
                Rectangle bounds = b.getBounds();
                spRender.box(bounds.x, bounds.y, 0, bounds.width,
                        bounds.height,
                        0);
                spRender.end();
                batch.begin();
            }
        } else if (b.getState() == State.EXPLODING) {
            Vector2 center = b.getBounds().getCenter(Vector2.Zero);
            Animation bullet_hit = AssetsGame.manager().animations.get(
                    "bullet_hit");
            Sprite sp = new Sprite(bullet_hit.getKeyFrame(b.state()));
            sp.setCenter(center.x, center.y);
            sp.draw(batch);
        }
    }
}
