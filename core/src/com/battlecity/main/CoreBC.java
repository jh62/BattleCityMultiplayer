package com.battlecity.main;

import com.badlogic.gdx.*;
import com.badlogic.gdx.controllers.*;
import com.badlogic.gdx.files.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.utils.*;
import com.battlecity.main.global.*;
import com.battlecity.main.render.Batch;
import com.battlecity.main.ui.*;
import java.util.logging.Level;
import java.util.logging.Logger;



public final class CoreBC extends Game
{

    public static final Game INSTANCE = new CoreBC();
    private boolean undecorated = true;

    private CoreBC() {
    }

    @Override
    public void create() {
        Gdx.graphics.setTitle("Battle City Multiplayer!");

        FileHandle mods = Presets.MOD_PATH;

        if(!mods.exists()) {
            mods.mkdirs();
            Gdx.app.log(this.getClass().toString(), "Creating new mod folder");
        }

        try {
            Class.forName(Batch.class.getName());
        }
        catch(ClassNotFoundException ex) {
            Logger.getLogger(CoreBC.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }

        FileHandle fh = Gdx.files.local("input.json");

        if(!fh.exists()) {
            JsonValue js = Parameters.getMappingsFile();
            fh.writeString(js.prettyPrint(JsonWriter.OutputType.minimal, 0), false);
        }

        ScreenMainMenu menu = new ScreenMainMenu();
        changeScreen(menu);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.getScreen().render(Gdx.graphics.getDeltaTime());

        if(Gdx.input.isKeyJustPressed(Input.Keys.F10)) {
            boolean fullScreen = Gdx.graphics.isFullscreen();
            Graphics.DisplayMode mode = Gdx.graphics.getDisplayMode();

            if(fullScreen) {
                Gdx.graphics.setWindowedMode(Presets.SCREEN_WIDTH, Presets.SCREEN_HEIGHT);
                this.getScreen().resize(Presets.SCREEN_WIDTH, Presets.SCREEN_HEIGHT);
            }
            else {
                Gdx.graphics.setFullscreenMode(mode);
            }
        }
    }

    public static void changeScreen(Screen screen) {
        GameScreen old = (GameScreen)INSTANCE.getScreen();
        if(old != null) {
//            Controllers.removeListener(old);
            old.dispose();
        }

        INSTANCE.setScreen(screen);
        screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        if(screen instanceof InputAdapter) {
            Gdx.input.setInputProcessor((GameScreen)screen);
//            Controllers.addListener((GameScreen)screen);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        Parameters.save();
    }

}
