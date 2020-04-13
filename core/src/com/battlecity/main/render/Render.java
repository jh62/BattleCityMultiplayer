package com.battlecity.main.render;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 *
 * @author Pablis
 */
public abstract class Render {

    protected Batch batch = Batch.BATCH;
    protected ShapeRenderer spRender = Batch.SPRENDERER;
    
    public abstract void render(IRenderable e);
}
