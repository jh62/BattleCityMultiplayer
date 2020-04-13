package com.battlecity.main.render;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.battlecity.main.assets.AssetsGame;
import com.battlecity.main.entity.DynamicEntity;
import com.battlecity.main.entity.DynamicEntity.State;
import com.battlecity.main.entity.Entity;
import com.battlecity.main.entity.tank.EnemyTank;
import com.battlecity.main.entity.tank.Tank;



/**
 *
 * @author JH62
 */
public class EnemyTankRender extends TankRenderer
{
    
    @Override
    public void render(Entity e) {
        
        EnemyTank eTank = (EnemyTank)e;
        
        if(eTank.getState() == State.EXPLODED) {
            BitmapFont font = AssetsGame.manager().defaultFont;
            font.setColor(Color.WHITE);
            font.getData().setScale(0.2f);
            int score = eTank.getTier().score;
            Vector2 center = eTank.getBounds().getCenter(Vector2.Zero);
            font.draw(batch, String.valueOf(score), center.x, center.y, 1,
                      Align.center, false);
            return;
        }
        else if(eTank.getState() == State.EXPLODING) {
            sp.setColor(Color.WHITE);
        }
        
        super.render(e);
    }
    
    @Override
    public TextureRegion getStateSprite(Tank tank) {
        TextureRegion tr;
        
        switch(tank.getTier()) {
            default: {
                tr = AssetsGame.manager().animations.
                        get(
                                "enemy_a").getKeyFrame(tank.state(), tank.
                                                       getState() == DynamicEntity.State.MOVING);
            }
            break;
            case SECOND: {
                tr = AssetsGame.manager().animations.
                        get(
                                "enemy_b").getKeyFrame(tank.state(), tank.
                                                       getState() == DynamicEntity.State.MOVING);
            }
            break;
            case THIRD: {
                tr = AssetsGame.manager().animations.
                        get(
                                "enemy_c").getKeyFrame(tank.state(), tank.
                                                       getState() == DynamicEntity.State.MOVING);
            }
            break;
            case FOUR: {
                int damage = ((EnemyTank)tank).getDamage();
                
                switch(damage) {
                    default: {
                        tr = AssetsGame.manager().animations.
                                get(
                                        "enemy_d_a").getKeyFrame(tank.state(),
                                                                 tank.getState() ==
                                                                 DynamicEntity.State.MOVING);
                        break;
                    }
                    case 1:
                    case 2: {
                        tr = AssetsGame.manager().animations.
                                get(
                                        "enemy_d_b").getKeyFrame(tank.state(),
                                                                 tank.getState() ==
                                                                 DynamicEntity.State.MOVING);
                        break;
                    }
                    case 3: {
                        tr = AssetsGame.manager().animations.
                                get(
                                        "enemy_d_c").getKeyFrame(tank.state(),
                                                                 tank.getState() ==
                                                                 DynamicEntity.State.MOVING);
                        break;
                    }
                }
                
                break;
            }
        }
        
        if(((EnemyTank)tank).powerEffectShowing() && (tank.getState() ==
                                                      State.MOVING || tank.getState() == State.STOPPED)) {
            sp.setColor(Color.RED);
        }
        else {
            sp.setColor(Color.WHITE);
        }
        
        return tr;
    }
    
}
