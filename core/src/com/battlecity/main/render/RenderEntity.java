package com.battlecity.main.render;

import com.battlecity.main.entity.Entity;

/**
 *
 * @author JH62
 */
public abstract class RenderEntity extends Render {

    public abstract void render(Entity e);

    @Override
    public final void render(IRenderable e) {
        this.render((Entity) e);
    }
}
