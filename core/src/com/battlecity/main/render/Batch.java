package com.battlecity.main.render;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 *
 * @author Pablis
 */
public final class Batch extends SpriteBatch {

    public static final Batch BATCH = new Batch();
    public static final ShapeRenderer SPRENDERER = new ShapeRenderer();

    private Batch() {

    }
}
