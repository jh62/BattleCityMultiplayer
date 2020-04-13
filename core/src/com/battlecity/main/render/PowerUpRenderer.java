package com.battlecity.main.render;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.battlecity.main.assets.AssetsGame;
import com.battlecity.main.entity.Entity;
import com.battlecity.main.events.powers.PowerUP;
import com.battlecity.main.global.enums.Power;

/**
 *
 * @author JH62
 */
public class PowerUpRenderer extends RenderEntity {

    private static int blinkTime = 0;
    private static boolean show = true;

    @Override
    public void render(Entity e) {
        if (blinkTime >= 18) {
            show = !show;
            blinkTime = 0;
        } else {
            blinkTime++;
        }

        if (!show) {
            return;
        }

        PowerUP power = (PowerUP) e;

        Sprite sp = getTexture(power.getPowerUp());
        sp.setPosition(power.getX(), power.getY());
        sp.draw(batch);
    }

    private Sprite getTexture(Power powerUP) {
        switch (powerUP) {
            case HELMET: {
                return AssetsGame.manager().textures.get("helmet");
            }
            case SHOVEL: {
                return AssetsGame.manager().textures.get("shovel");
            }
            case TANK: {
                return AssetsGame.manager().textures.get("tank");
            }
            case GRENADE: {
                return AssetsGame.manager().textures.get("grenade");
            }
            case STAR: {
                return AssetsGame.manager().textures.get("star");
            }
            case CLOCK: {
                return AssetsGame.manager().textures.get("clock");
            }
            case CLOAK: {
                return AssetsGame.manager().textures.get("cloak");
            }
            case HEALTH: {
                return AssetsGame.manager().textures.get("healthpack");
            }
            case BOUNCE: {
                return AssetsGame.manager().textures.get("bounce");
            }
            default: {
                return null;
            }
        }
    }
}
