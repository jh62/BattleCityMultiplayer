package com.battlecity.main.entity.tiles;

import com.badlogic.gdx.math.Rectangle;
import com.battlecity.main.entity.Entity.Facing;

/**
 *
 * @author Pablis
 */
public class TileRegion {

    transient Rectangle bounds = null;
    int regionValue;
    boolean isDestroyed = false;
    boolean hitLeft = false;
    boolean hitRight = false;
    boolean hitTop = false;
    boolean hitBottom = false;

    TileRegion() {

    }

    public TileRegion(Rectangle bounds, byte regionValue) {
        this.bounds = bounds;
        this.regionValue = regionValue;
    }

    public TileRegion(float x, float y, float width, float height,
            byte regionValue) {
        this.bounds = new Rectangle(x, y, width, height);
        this.regionValue = regionValue;
    }

    public int regionValue() {
        return this.regionValue;
    }

    public void repair() {
        hitLeft = hitRight = hitTop = hitBottom = false;
    }

    public boolean isDamaged() {
        return hitLeft || hitRight || hitBottom || hitTop;
    }

    public boolean isDestroyed() {
        return this.isDestroyed;
    }

    public void destroy() {
        this.isDestroyed = true;
    }

    public void damage(Facing facing) {

        if (this.isDamaged()) {
            isDestroyed = true;
            return;
        }

        switch (facing) {
            case UP: {
                hitBottom = true;
                break;
            }
            case LEFT: {
                hitRight = true;
                break;
            }
            case DOWN: {
                hitTop = true;
                break;
            }
            case RIGHT: {
                hitLeft = true;
                break;
            }
        }
    }

    void set(Rectangle bounds) {
        this.bounds = bounds;
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    public boolean isHitLeft() {
        return hitLeft;
    }

    public boolean isHitRight() {
        return hitRight;
    }

    public boolean isHitTop() {
        return hitTop;
    }

    public boolean isHitBottom() {
        return hitBottom;
    }
}
