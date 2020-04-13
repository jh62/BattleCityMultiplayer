package com.battlecity.main.entity;

import com.badlogic.gdx.math.Rectangle;
import com.battlecity.main.entity.Entity.Facing;
import com.battlecity.main.global.enums.Tier;



/**
 * An entity that can change states and move around the world.
 *
 * @author Pablis
 */
public abstract class DynamicEntity extends Entity
{

    public static enum SIDE
    {
        PLAYER,
        ENEMY
    }

    public static enum State
    {
        DEFAULT,
        STOPPED,
        MOVING,
        EXPLODING,
        EXPLODED,
        DEAD,
        RESPAWNING,
    }

    protected SIDE side = SIDE.ENEMY;
    protected Tier tier = Tier.FIRST;
    protected State state = State.STOPPED;
    protected float veloticy = 0.7f;

    protected boolean frozen = false;

    protected DynamicEntity() {

    }

    public DynamicEntity(float x, float y, SIDE side) {
        super(x, y);
        this.side = side;
    }

    public DynamicEntity(float x, float y, float width, float height, Facing facing,
                         SIDE side) {
        super(x, y, width, height);
        this.facing = facing;
        this.side = side;
    }

    @Override
    public void setFacing(Facing facing) {
        if(!isFrozen())
            super.setFacing(facing);
    }

    public Tier getTier() {
        return this.tier;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public boolean isFrozen() {
        return this.frozen;
    }

    public void moveEntity() {
        if(this.getState() != State.MOVING) {
            return;
        }

        switch(facing) {
            case UP: {
                this.y += this.veloticy;
                break;
            }
            case DOWN: {
                this.y -= this.veloticy;
                break;
            }
            case LEFT: {
                this.x -= this.veloticy;
                break;
            }
            case RIGHT: {
                this.x += this.veloticy;
                break;
            }
        }
    }

    @Override
    public Rectangle getBounds() {
        return this.bounds.set(x, y, width, height);
    }

    public SIDE getSide() {
        return side;
    }

    public void setSide(SIDE side) {
        this.side = side;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        if(this.state != state) {
            resetDelta();
        }
        this.state = state;
    }

    public float getVeloticy() {
        return veloticy;
    }

    public void setVeloticy(float veloticy) {
        this.veloticy = veloticy;
    }

    @Override
    public boolean isAlive() {
        return getState() != State.DEAD;
    }

}
