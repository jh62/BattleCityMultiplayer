package com.battlecity.main.entity.tank;

import com.badlogic.gdx.utils.*;
import com.battlecity.main.assets.AssetsGame;
import com.battlecity.main.entity.*;
import com.battlecity.main.global.enums.*;
import com.battlecity.main.net.Server;



/**
 *
 * @author Pablis
 */
public abstract class Tank extends DynamicEntity
{

    public enum Action
    {
        NORMAL,
        SHOOTING,
        SLIDING,
    }

    protected Bullet bullet = null;
    protected Action action = Action.NORMAL;

    transient protected int bulletInstances = 0;

    Tank() {
    }

    public Tank(float x, float y, SIDE side) {
        super(x, y, side);
    }

    public Tank(float x, float y, Tier tier, SIDE side) {
        super(x, y, side);
        this.tier = tier;
    }

    @Override
    public SIDE getSide() {
        return this.side;
    }

    @Override
    public void setState(State state) {
        super.setState(state);

        switch(state) {
            case EXPLODING: {
                AssetsGame.manager().soundManager.getSound("tankDead").play();

                if(this.bullet != null && this.bullet.isAlive()) {
                    this.bullet.setState(State.DEAD);
                }

                break;
            }
        }
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Action getAction() {
        return this.action;
    }

    public Bullet getBullet() {
        return this.bullet;
    }

    public boolean isShooting() {
        return bulletInstances > 0;
    }

    public boolean isMoving() {
        return this.getState() == State.MOVING || this.getState() ==
                                                  State.STOPPED;
    }

    public void onBulletDestroyed() {
        bulletInstances--;

        if(bulletInstances < 0) {
            bulletInstances = 0;
        }
    }

    public void onShooting() {
        if(!this.isMoving()) {
            return;
        }

        this.setAction(Action.NORMAL);
        Bullet b = new Bullet(this);
        bulletInstances++;
        Server.getInstance().getWorld().addEntity(b);

        if(this instanceof PlayerTank) {
            Server.getInstance().sendEventToAll(Event.ON_TANK_SHOOT,
                                                ((PlayerTank)this).getPlayerID());
        }
    }

    @Override
    public boolean onCollision(Entity attacker) {
        if(attacker instanceof Tank) {
            if(((Tank)attacker).getSide() != this.getSide()) {
                if(((Tank)attacker).getState() == State.MOVING ||
                   ((Tank)attacker).getState() == State.STOPPED) {
                    return true;
                }
            }
        }
        return false;
    }

    public void pickRandomDirection() {
        Array<Facing> fpos = new Array(Facing.values());
        fpos.removeValue(getFacing(), true);
        Facing f = fpos.random();
        setFacing(f);
    }

    @Override
    public void update() {
        super.update();

        if(!this.isAlive()) {
            return;
        }

        if(this.getAction() == Action.SHOOTING) {

            if(this.isShooting()) {
                return;
            }

            onShooting();
        }
    }

}
