package com.battlecity.main.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.controllers.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.*;
import com.battlecity.main.*;
import com.battlecity.main.assets.AssetsGame;
import com.battlecity.main.global.*;
import com.battlecity.main.levels.*;
import com.battlecity.main.sound.SoundFX;



/**
 *
 * @author JH62
 */
public class ScreenScore extends GameScreen
{

    private final Texture texture = Parameters.mode == BaseLevel.Mode.ARCADE ? new Texture(Gdx.files.internal(
                                    "data/ui/score_screen.png")) : new Texture(Gdx.files.internal(
                                    "data/ui/score_screen_dm.png"));
    private final SoundFX scoring = new SoundFX("scoring.ogg");

    private int countA = 0;
    private int countB = 0;
    private int countC = 0;
    private int countD = 0;
    private int totalScore = 0;
    private final int[] enemiesKilled;

    public ScreenScore() {
        enemiesKilled = Parameters.enemiesKilledByPlayer;
    }

    @Override
    public void draw(SpriteBatch batch, float d) {
        batch.draw(texture, 0, 0, camera.viewportWidth,
                   camera.viewportHeight);

        AssetsGame.manager().defaultFont.setColor(Color.WHITE);
        AssetsGame.manager().defaultFont.getData().setScale(0.34f);
        AssetsGame.manager().defaultFont.draw(batch, String.format("STAGE: %d", Parameters.lastLevel),
                                              camera.viewportWidth * 0.5f, camera.viewportHeight * 0.9f, 1,
                                              Align.center, false);

        AssetsGame.manager().defaultFont.setColor(Color.RED);
        AssetsGame.manager().defaultFont.getData().setScale(0.32f);
        AssetsGame.manager().defaultFont.draw(batch, Parameters.playerName,
                                              camera.viewportWidth * 0.5f, camera.viewportHeight * 0.8f, 1,
                                              Align.center, false);

        if(scoreDone) {
            AssetsGame.manager().defaultFont.setColor(Color.WHITE);
            AssetsGame.manager().defaultFont.getData().setScale(0.32f);
            AssetsGame.manager().defaultFont.draw(batch, "PRESS ENTER TO EXIT",
                                                  camera.viewportWidth * 0.5f, camera.viewportHeight * 0.7f,
                                                  1,
                                                  Align.center, false);
        }

        float scoreX = camera.viewportWidth * 0.45f;

        float y1 = camera.viewportHeight * 0.58f;
        float y2 = camera.viewportHeight * 0.46f;
        float y3 = camera.viewportHeight * 0.33f;
        float y4 = camera.viewportHeight * 0.21f;
        float y5 = camera.viewportHeight * 0.08f;

        AssetsGame.manager().defaultFont.setColor(Color.WHITE);
        AssetsGame.manager().defaultFont.getData().setScale(0.3f);

        if(Parameters.mode == BaseLevel.Mode.ARCADE) {
            AssetsGame.manager().defaultFont.draw(batch, String.valueOf(
                                                  countA), scoreX, y1, 1, Align.center, false);
            AssetsGame.manager().defaultFont.draw(batch, String.valueOf(
                                                  countB), scoreX, y2, 1, Align.center, false);
            AssetsGame.manager().defaultFont.draw(batch, String.valueOf(
                                                  countC), scoreX, y3, 1, Align.center, false);
            AssetsGame.manager().defaultFont.draw(batch, String.valueOf(
                                                  countD), scoreX, y4, 1, Align.center, false);

            AssetsGame.manager().defaultFont.draw(batch, String.valueOf(
                                                  totalScore), camera.viewportWidth * 0.55f, y5, 1,
                                                  Align.center, false);
        }
        else {
            AssetsGame.manager().defaultFont.draw(batch, String.valueOf(
                                                  countA), scoreX, y1, 1, Align.center, false);
            AssetsGame.manager().defaultFont.draw(batch, String.valueOf(
                                                  countB), scoreX, y2, 1, Align.center, false);
            AssetsGame.manager().defaultFont.draw(batch, String.valueOf(
                                                  totalScore), camera.viewportWidth * 0.55f, y5, 1,
                                                  Align.center, false); 
        }

    }

    private long updateScoreTimer = 0;
    private boolean scoreDone = false;
    private boolean aborted = false;
    private boolean showing = false;

    @Override
    protected void update() {
        if(scoreDone) {
            return;
        }

        if(aborted) {
            scoreDone = true;

            if(Parameters.mode == BaseLevel.Mode.ARCADE) {
                totalScore = (enemiesKilled[0] * 100) + (enemiesKilled[1] * 200) +
                             (enemiesKilled[2] * 300) +
                             (enemiesKilled[3] * 400);
            }
            else {
                totalScore = Parameters.playerScore;
            }
            return;
        }

        if(!showing) {
            return;
        }

        if(System.currentTimeMillis() - updateScoreTimer > 250) {
            updateScoreTimer = System.currentTimeMillis();

            if(Parameters.mode == BaseLevel.Mode.ARCADE) {

                if(countA < enemiesKilled[0]) {
                    countA += 1;
                }
                else if(countB < enemiesKilled[1]) {
                    countB += 1;
                }
                else if(countC < enemiesKilled[2]) {
                    countC += 1;
                }
                else if(countD < enemiesKilled[3]) {
                    countD += 1;
                }
                else {
                    scoreDone = true;
                }

                totalScore = (countA * 100) + (countB * 200) + (countC * 300) +
                             (countD * 400);
            }
            else {
                if(countA < Parameters.playerScore) {
                    countA += 1;
                }
                else if(countB < Parameters.deaths) {
                    countB += 1;
                }
                else {
                    scoreDone = true;
                }

                totalScore = countA;
            }

            if(!scoreDone) {
                scoring.play();
            }
        }
    }

    @Override
    public void show() {
        super.show();
        showing = true;
    }

    @Override
    public void dispose() {
        texture.dispose();
        scoring.dispose();
    }

    @Override
    public boolean buttonDown(Controller cntrlr, int i) {

        if(!aborted) {
            aborted = true;
            return true;
        }
        else if(scoreDone) {
            ScreenGameOver screen = new ScreenGameOver();
            CoreBC.changeScreen(screen);
        }

        return true;
    }

    @Override
    public boolean keyDown(int keycode) {

        if(!aborted) {
            aborted = true;
            return true;
        }

        switch(keycode) {
            case Input.Keys.ENTER:
            case Input.Keys.SPACE:
            case Input.Keys.ESCAPE: {

                if(!aborted) {
                    aborted = true;
                }
                else if(scoreDone) {
                    ScreenGameOver screen = new ScreenGameOver();
                    CoreBC.changeScreen(screen);
                }
            }
        }

        return true;
    }

}
