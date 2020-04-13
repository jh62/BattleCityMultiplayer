package com.battlecity.main.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.battlecity.main.CoreBC;
import com.battlecity.main.sound.SoundFX;

/**
 *
 * @author JH62
 */
public class ScreenGameOver extends GameScreen {

    private final Texture texture = new Texture(Gdx.files.internal(
            "data/ui/game_over.png"));
    private final SoundFX gameOverSound = new SoundFX("gameover.ogg");

    private int timeElapsed = 0;

    @Override
    public void draw(SpriteBatch batch, float d) {
        batch.draw(texture, 0, 0, camera.viewportWidth,
                camera.viewportHeight);
    }

    @Override
    protected void update() {
        if (timeElapsed < 200) {
            timeElapsed++;
        } else {
            ScreenMainMenu screen = new ScreenMainMenu();
            CoreBC.changeScreen(screen);
        }
    }

    @Override
    public void dispose() {
        texture.dispose();
        gameOverSound.dispose();
    }

    @Override
    public void show() {
        super.show();
        gameOverSound.play();
    }
}
