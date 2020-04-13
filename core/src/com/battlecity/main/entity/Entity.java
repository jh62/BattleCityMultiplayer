package com.battlecity.main.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.*;
import com.battlecity.main.render.IRenderable;
import com.battlecity.main.global.Presets;



/**
 *
 * @author Pablis
 */
public abstract class Entity implements IRenderable
{

    public static enum Facing
    {
        UP,
        DOWN,
        LEFT,
        RIGHT;

        public Facing getOpposite() {
            switch(this) {
                case UP: {
                    return DOWN;
                }
                case DOWN: {
                    return UP;
                }
                case LEFT: {
                    return RIGHT;
                }
            }

            return LEFT;
        }
    }

    transient protected Rectangle bounds = new Rectangle();

    protected float x = 0;
    protected float y = 0;
    protected float width = 0;
    protected float height = 0;
    protected Facing facing = Facing.UP;
    protected boolean isVisible = true;
    private float stateTime = 0f;

    public Entity() {

    }

    public Entity(float x, float y) {
        this(x, y, Presets.ENTITY_WIDTH, Presets.ENTITY_HEIGHT);
    }

    public Entity(float x, float y, float width, float height) {
        this(x, y, width, height, Facing.UP);
    }

    public Entity(float x, float y, float width, float height, Facing facing) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.facing = facing;
        this.bounds.set(x, y, width, height);
    }

    public final float state() {
        return this.stateTime;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    public void setFacing(Facing facing) {
        this.facing = facing == null ? this.facing : facing;
    }

    public void setFacing(Vector2 v) {
        if(v.isZero())
            return;

        if(v.x == -1)
            setFacing(Facing.LEFT);
        else if(v.x == 1)
            setFacing(Facing.RIGHT);
        else if(v.y == -1)
            setFacing(Facing.DOWN);
        else if(v.y == 1)
            setFacing(Facing.UP);
    }

    public Facing getFacing(int dir, float amount) {
        if(dir == 0) {
            if(amount == -1)
                return Facing.DOWN;
            else if(amount == 1)
                return Facing.UP;
        }
        else if(dir == 1) {
            if(amount == -1)
                return Facing.LEFT;
            else if(amount == 1)
                return Facing.RIGHT;
        }

        return facing;
    }

    public Facing getFacing() {
        return this.facing;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }

    public boolean isAlive() {
        return true;
    }

    /**
     * Called when an entity is spawned.
     */
    public void onSpawn() {

    }

    /**
     * Called when an entity damages this entity.
     *
     * @param attacker
     */
    public void onDammageBy(Entity attacker) {

    }

    /**
     * Called when this entity collides with another.
     *
     * @param attacker
     *
     * @return
     */
    public boolean onCollision(Entity attacker) {
        return false;
    }

    /**
     * Called every server update.
     */
    public void update() {
        updateDelta();
    }

    private void updateDelta() {
        this.stateTime += Gdx.graphics.getDeltaTime();
    }

    public final void resetDelta() {
        this.stateTime = 0f;
    }

}
