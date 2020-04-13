package com.battlecity.main.render;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.ObjectMap.Entry;
import com.battlecity.main.assets.AssetsGame;
import com.battlecity.main.entity.DynamicEntity.State;
import com.battlecity.main.entity.Entity;
import com.battlecity.main.entity.tank.PlayerTank;
import com.battlecity.main.entity.tank.Tank;
import com.battlecity.main.global.enums.Power;
import com.battlecity.main.net.*;



/**
 *
 * @author JH62
 */
public class PlayerTankRender extends TankRenderer
{
    private static IntMap<Color> colors = new IntMap();
    private static Sprite fx = new Sprite();

    static {
        colors.put(3, Color.CORAL);
        colors.put(4, Color.GREEN);
        colors.put(5, Color.PINK);
        colors.put(6, Color.LIME);
        colors.put(7, Color.CYAN);
        colors.put(8, Color.ORANGE);
        colors.put(9, Color.VIOLET);
    }

    @Override
    public void render(Entity e) {
        PlayerTank pTank = (PlayerTank)e;

        if(pTank.getPowerUp() == Power.CLOAK) {
            if(pTank.getPlayerID() == Client.instance().getPlayerTank().getPlayerID())
                sp.setColor(Color.DARK_GRAY);
            else
                return;
        }
        else {
            if(pTank.getPlayerID() <= 2 || pTank.getState() == State.EXPLODING || pTank.getState() == State.EXPLODED || pTank.getState() == State.RESPAWNING) {
                sp.setColor(Color.WHITE);
            }
            else {
                Color c = colors.get(pTank.getPlayerID(), Color.SLATE);
                sp.setColor(c);
            }
        }

        super.render(e);

        switch(pTank.getPowerUp()) {
            case BOUNCE: {
                if(pTank.bouncing_field) {
                    sp.setColor(Color.YELLOW);
                    sp.draw(batch);
                }

                break;
            }
            case HELMET:
            case FORCE_FIELD: {
                TextureRegion t = AssetsGame.manager().animations.
                        get(
                                "field").getKeyFrame(
                                pTank.state(), true);
                fx.setRegion(t);
                fx.setBounds(pTank.getBounds().x, pTank.getBounds().y, t.getRegionWidth(), t.getRegionHeight());
                Vector2 center = pTank.getBounds().getCenter(Vector2.Zero);
                fx.setCenter(center.x, center.y);

                if(pTank.getPowerUp() == Power.BOUNCE)
                    fx.setColor(Color.DARK_GRAY);
                else
                    fx.setColor(Color.WHITE);

                fx.draw(batch);
                break;
            }
        }

        if(pTank.getHealth() > 0 && (pTank.getPlayerID() == Client.instance().getPlayerTank().getPlayerID() || pTank.getShowHealth() || Server.localCoop)) {
            float time = AssetsGame.manager().animations.get("health").getAnimationDuration() * pTank.getHealth();
            TextureRegion t = AssetsGame.manager().animations.get("health").getKeyFrame(time);
            float x = pTank.getX() + (pTank.getWidth() * .5f) - (t.getRegionWidth() * .5f);
            float y = pTank.getY() + pTank.getHeight() + t.getRegionHeight();
            batch.draw(t, x, y);
        }
    }

    @Override
    public TextureRegion getStateSprite(Tank tank) {
        final int playerId = ((PlayerTank)tank).getPlayerID();

        switch(tank.getTier()) {
            default: {
                if(playerId == 1 || playerId > 2) {
                    return AssetsGame.manager().animations.
                            get(
                                    "tank_y_a").getKeyFrame(tank.state(), tank.
                                                            getState() == State.MOVING);
                }
                else {
                    return AssetsGame.manager().animations.
                            get(
                                    "tank_g_a").getKeyFrame(tank.state(), tank.
                                                            getState() == State.MOVING);
                }
            }
            case SECOND: {
                if(playerId == 1 || playerId > 2) {
                    return AssetsGame.manager().animations.
                            get(
                                    "tank_y_b").getKeyFrame(tank.state(), tank.
                                                            getState() == State.MOVING);
                }
                else {
                    return AssetsGame.manager().animations.
                            get(
                                    "tank_g_b").getKeyFrame(tank.state(), tank.
                                                            getState() == State.MOVING);
                }
            }
            case THIRD: {
                if(playerId == 1 || playerId > 2) {
                    return AssetsGame.manager().animations.
                            get(
                                    "tank_y_c").getKeyFrame(tank.state(), tank.
                                                            getState() == State.MOVING);
                }
                else {
                    return AssetsGame.manager().animations.
                            get(
                                    "tank_g_c").getKeyFrame(tank.state(), tank.
                                                            getState() == State.MOVING);
                }
            }
            case FOUR: {
                if(playerId == 1 || playerId > 2) {
                    return AssetsGame.manager().animations.
                            get(
                                    "tank_y_d").getKeyFrame(tank.state(), tank.
                                                            getState() == State.MOVING);
                }
                else {
                    return AssetsGame.manager().animations.
                            get(
                                    "tank_g_d").getKeyFrame(tank.state(), tank.
                                                            getState() == State.MOVING);
                }
            }
        }
    }

}
