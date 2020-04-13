package com.battlecity.main.entity;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;
import com.battlecity.main.assets.AssetsGame;
import com.battlecity.main.entity.tank.EnemyTank;
import com.battlecity.main.entity.tank.PlayerTank;
import com.battlecity.main.entity.tank.Tank;
import com.battlecity.main.entity.tiles.*;
import com.battlecity.main.events.TimerManager.Task;
import com.battlecity.main.events.powers.*;
import com.battlecity.main.global.Presets;
import com.battlecity.main.global.enums.*;
import com.battlecity.main.levels.*;
import com.battlecity.main.net.*;
import com.battlecity.main.sound.*;
import com.battlecity.main.world.*;



/**
 *
 * @author Pablis
 */
public class Bullet extends DynamicEntity
{

    transient Entity owner = null;
    transient boolean isBouncing = false;

    Bullet() {
    }

    public Bullet(Tank owner) {
        this.owner = owner;
        this.facing = owner.facing;
        this.width = Presets.BULLET_WIDTH;
        this.height = Presets.BULLET_HEIGHT;
        this.side = owner.side;
        this.tier = (owner instanceof PlayerTank) ? owner.tier : Tier.FIRST;
        this.state = State.MOVING;
        calculatePosition();
        calculateVelocity();
    }

    private void calculatePosition() {
        Vector2 center = this.owner.getBounds().getCenter(Vector2.Zero);
        this.x = center.x - (this.width * .5f);
        this.y = center.y - (this.height * .5f);

//        switch(facing) {
//            case UP: {
//                this.y += owner.getBounds().height;
//                break;
//            }
//            case DOWN: {
//                this.y -= owner.getBounds().height;
//                break;
//            }
//            case LEFT: {
//                this.x -= owner.getBounds().width;
//                break;
//            }
//            case RIGHT: {
//                this.x += owner.getBounds().width;
//                break;
//            }
//        }
    }

    private void calculateVelocity() {
        Tank t = (Tank)this.owner;

        if(t instanceof EnemyTank) {
            switch(this.tier) {
                case FIRST: {
                    this.setVeloticy(Presets.BULLET_SLOW);
                    break;
                }
                case THIRD: {
                    this.setVeloticy(Presets.BULLET_FAST);
                    break;
                }
                default: {
                    this.setVeloticy(Presets.BULLET_NORMAL);
                    break;
                }
            }
        }
        else {
            switch(this.tier) {
                case FIRST: {
                    this.setVeloticy(Presets.BULLET_NORMAL);
                    break;
                }
                case SECOND:
                case THIRD:
                case FOUR: {
                    this.setVeloticy(Presets.BULLET_FAST);
                    break;
                }
            }
        }
    }

    public Entity getOwner() {
        return this.owner;
    }

    @Override
    public boolean isAlive() {
        return getState() != State.DEAD;
    }

    @Override
    public boolean onCollision(Entity attacker) {
        if(attacker instanceof PowerUP) {
            return false;
        }

        if(attacker instanceof Tank && (((Tank)attacker).getState() ==
                                        State.EXPLODING || ((Tank)attacker).getState() ==
                                                           State.RESPAWNING || ((Tank)attacker).getState() ==
                                                                               State.EXPLODED)) {
            return false;
        }

//        if(attacker == this.getOwner() && Server.getInstance().getWorld().getCurrentLevel().getMode() == BaseLevel.Mode.ARCADE) {
//            return false;
//        }
        if(attacker == this.getOwner() && !isBouncing) {
            return false;
        }

        if(attacker instanceof Bullet && (((Bullet)attacker).getState() != State.MOVING || ((Bullet)attacker).getOwner() ==
                                                                                           getOwner())) {

            return false;
        }

        if(attacker instanceof PlayerTank && ((PlayerTank)attacker).getPowerUp() == Power.BOUNCE) {
            final PlayerTank pTank = (PlayerTank)attacker;
            isBouncing = true;

            if(pTank.bouncing_field_hitpoints < 1) {
                pTank.setPowerUp(Power.NONE);
            }

            if(!pTank.bouncing_field) {
                pTank.bouncing_field = true;

                AssetsGame.manager().soundManager.getSound("bounceField").play();

                World.getTimerManager().scheduleTask(new Task()
                {
                    @Override
                    public void run() {
                        this.cancel();
                        pTank.bouncing_field = false;
                        pTank.bouncing_field_hitpoints--;
                    }
                }, 80);
            }

            pickRandomDirection();
            return false;
        }

        onDammageBy(attacker);
        attacker.onDammageBy(this);

        return true;
    }

    @Override
    public void onDammageBy(Entity attacker) {
        if(attacker instanceof Bullet) {
            this.setState(State.DEAD);
        }
        else {
            kill();
        }

        ((Tank)this.getOwner()).onBulletDestroyed();
    }

    public void pickRandomDirection() {

        if(MathUtils.randomBoolean(.33f))
            setFacing(facing.getOpposite());
        else {
            Array<Facing> fpos = new Array(Facing.values());
            fpos.removeValue(getFacing(), true);
            Facing f = fpos.random();
            setFacing(f);
        }
    }

    @Override
    public void update() {
        super.update();

        if(!this.getOwner()
                .isAlive()) {

        }
    }

    public void kill() {
        this.setState(State.EXPLODING);

        float d = AssetsGame.manager().animations.get("bullet_hit").
                getAnimationDuration() * 1000;

        final Task deadAction = new Task()
        {
            @Override
            public void run() {
                setState(State.DEAD);
            }
        };

        World.getTimerManager().scheduleTask(deadAction, (int)d);
    }

}
