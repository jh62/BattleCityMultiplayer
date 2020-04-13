package com.battlecity.main.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.controllers.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.battlecity.main.render.Batch;
import com.battlecity.main.global.Presets;
import com.battlecity.main.net.*;



/**
 *
 * @author Pablis
 */
public abstract class GameScreen extends InputAdapter implements Screen,
                                                                 ControllerListener
{
    public static final IntMap<String> controllers = new IntMap();

    public abstract void draw(SpriteBatch batch, float d);

    protected abstract void update();

    protected final OrthographicCamera camera;
    protected final Viewport viewport;

    public GameScreen() {
        camera = new OrthographicCamera(Presets.WORLD_WIDTH +
                                        Presets.SIDEBAR_WIDTH,
                                        Presets.WORLD_HEIGHT);
        this.camera.position.set((camera.viewportWidth * 0.5f),
                                 (camera.viewportHeight * 0.5f),
                                 0);
        this.camera.update();
        viewport
        = new StretchViewport(camera.viewportWidth,
                              camera.viewportHeight,
                              camera);
        viewport.apply();
    }

    private void detectControllers() {
        GameScreen.controllers.clear();

        for(Controller cntrl : Controllers.getControllers()) {

            if(controllers.size > (Server.localCoop ? 4 : 1)) {
                System.out.println("Every player has a controller assigned. No further controllers will be recognized.");
                break;
            }

//            String name = controllers.size == 0 ? "p1" : "p2";
            String name = "p" + (controllers.size + 1);

            int id = cntrl.hashCode();
            controllers.put(id, name);
            System.out.println("Added controller for " + name + " with id #" + id);
        }
    }

    public int getControllerID(Controller controller) {
        String name = controllers.get(controller.hashCode(), "");
        int id = -1;

        if(name.equals("p1"))
            id = 0;
        else if(name.equals("p2"))
            id = 1;
        else if(name.equals("p3"))
            id = 2;
        else if(name.equals("p4"))
            id = 3;

        return id;
    }

    /**
     * Prepares and renders the screen. This method automatically calls draw on
     * its subclasses.
     *
     * @param f
     */
    @Override
    public void render(float f) {
        Batch.BATCH.setProjectionMatrix(camera.combined);
        Batch.BATCH.begin();
        draw(Batch.BATCH, f);
        Batch.BATCH.end();
        update();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
    }

    @Override
    public void resize(int width, int height) {
        if(viewport != null) {
            viewport.update(width, height);
        }

        Timer.schedule(new Timer.Task()
        {
            @Override
            public void run() {
                detectControllers();
            }
        }, .2f);
    }

    @Override
    public void show() {
        // nothing
    }

    @Override
    public boolean accelerometerMoved(Controller cntrlr, int i, Vector3 vctr) {
        return false;
    }

    @Override
    public boolean axisMoved(Controller cntrlr, int i, float f) {
        return false;
    }

    @Override
    public boolean buttonDown(Controller cntrlr, int i) {
        return false;
    }

    @Override
    public boolean buttonUp(Controller cntrlr, int i) {
        return false;
    }

    @Override
    public void connected(Controller cntrlr) {
        System.out.println("controller connected");
    }

    @Override
    public void disconnected(Controller cntrlr) {
        System.out.println("controller disconnected");
    }

    @Override
    public boolean povMoved(Controller cntrlr, int i, PovDirection pd) {
        return false;
    }

    @Override
    public boolean xSliderMoved(Controller cntrlr, int i, boolean bln) {
        return false;
    }

    @Override
    public boolean ySliderMoved(Controller cntrlr, int i, boolean bln) {
        return false;
    }

}
