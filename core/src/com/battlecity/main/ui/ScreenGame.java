package com.battlecity.main.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.controllers.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;
import com.battlecity.main.CoreBC;
import com.battlecity.main.assets.AssetsGame;
import com.battlecity.main.levels.BaseLevel;
import com.battlecity.main.entity.DynamicEntity.State;
import com.battlecity.main.entity.Entity;
import com.battlecity.main.entity.tank.PlayerTank;
import com.battlecity.main.entity.tank.Tank.Action;
import com.battlecity.main.entity.tiles.Eagle;
import com.battlecity.main.entity.tiles.Tile;
import com.battlecity.main.events.TimerManager.Task;
import com.battlecity.main.global.Parameters;
import com.battlecity.main.net.Client;
import com.battlecity.main.net.Server;
import com.battlecity.main.render.RenderRegistry;
import com.battlecity.main.global.Presets;
import com.battlecity.main.global.Presets.KeyActions;
import static com.battlecity.main.global.Presets.KeyActions.ACCEPT;
import static com.battlecity.main.global.Presets.KeyActions.CANCEL;
import static com.battlecity.main.global.Presets.KeyActions.CHAT;
import static com.battlecity.main.global.Presets.KeyActions.P2_ACTION;
import static com.battlecity.main.global.Presets.KeyActions.P2_MOVE_DOWN;
import static com.battlecity.main.global.Presets.KeyActions.P2_MOVE_LEFT;
import static com.battlecity.main.global.Presets.KeyActions.P2_MOVE_RIGHT;
import static com.battlecity.main.global.Presets.KeyActions.P2_MOVE_UP;
import static com.battlecity.main.global.Presets.KeyActions.SHOW_INFO;
import com.battlecity.main.global.enums.*;
import com.battlecity.main.levels.*;
import com.battlecity.main.render.*;
import com.battlecity.main.render.Batch;
import com.battlecity.main.world.World;
import static com.battlecity.main.global.Presets.KeyActions.MOVE_UP;
import static com.battlecity.main.global.Presets.KeyActions.MOVE_LEFT;
import static com.battlecity.main.global.Presets.KeyActions.MOVE_RIGHT;
import static com.battlecity.main.global.Presets.KeyActions.MOVE_DOWN;
import static com.battlecity.main.global.Presets.KeyActions.ACTION;
import static com.battlecity.main.global.Presets.KeyActions.PREVIOUS_OPTION;



/**
 *
 * @author Pablis
 */
public class ScreenGame extends GameScreen
{

    public static boolean paused = false;

    private enum ScreenState
    {
        DEFAULT,
        TYPING,
    }

    private Task disposeTimer;
    private ScreenState status = ScreenState.DEFAULT;
    private boolean showNames = false;
    private boolean pressEnterOption = false;

    private boolean gameOverQuit = false;
    private boolean showPressEnter = false;
    private int playerDeadAnimationTime = 0;

    private final RenderEntity TileDamageRenderEntity = new TileDamageRenderer();

    private boolean quit = false;
    private float pauseBlink = 0f;

    public ScreenGame() {
        RenderRegistry.initialize();
        AssetsGame.manager();
        Parameters.enemiesKilledByPlayer = new int[4];
        Parameters.playerScore = 0;
    }

    @Override
    public boolean axisMoved(Controller cntrlr, int axis, float dir) {
        if(paused || !Client.instance().getWorld().isWorldInitiated())
            return true;

        int pid = getControllerID(cntrlr);

        if(pid == -1) {
            if(Presets.debugOn)
                System.out.println("No controller(s) detected.");

            return false;
        }

        KeyActions k = axis == 0 ? Presets.KeyActions.JOY_Y : Presets.KeyActions.JOY_X;
        int direction = Math.round(dir);
        PlayerTank pt = Client.instance().local.get(pid);

        switch(k) {
            case JOY_Y: {
                pt.direction.set(0, -direction);
                break;
            }
            case JOY_X: {
                pt.direction.set(direction, 0);
                break;
            }
        }

        if(pt.getState() != State.DEAD) {
            if(!pt.direction.isZero()) {
                pt.setState(State.MOVING);
                pt.setFacing(pt.direction);
                pt.setAction(Action.NORMAL);
            }
            else
                pt.setState(State.STOPPED);

            if(pid == 0)
                Client.instance().sendEntityUpdate(pt.getState(), pt.getFacing(), pt.getAction());
        }

        return true;
    }

    @Override
    public boolean buttonDown(Controller cntrlr, int i) {
        if(!Client.instance().getWorld().isWorldInitiated() && Client.instance().getWorld().getCurrentLevel().getLevelID() != LevelEnd.LEVEL_END_ID)
            return true;

        int pid = getControllerID(cntrlr);

        if(pid == -1) {
            if(Presets.debugOn)
                System.out.println("No controller(s) detected.");

            return false;
        }

        KeyActions k = Parameters.joyMaps.get(i, KeyActions.UNBINDED);

        if(Presets.debugOn)
            System.out.println("button pressed: " + i);

        switch(status) {
            case DEFAULT:
                switch(k) {
                    case ACTION: {
                        PlayerTank pt = Client.instance().local.get(pid);
                        pt.setAction(Action.SHOOTING);

                        if(pid == 0)
                            Client.instance().sendEntityUpdate(Action.SHOOTING);

                        break;
                    }
                    case SHOW_INFO: {
                        if(Client.instance().getWorld().getCurrentLevel().getMode() == BaseLevel.Mode.ARCADE)
                            showNames = true;
                        break;
                    }
                    case VOLUME_UP: {
                        Parameters.setVolume(true);
                        break;
                    }
                    case VOLUME_DOWN: {
                        Parameters.setVolume(false);
                        break;
                    }
                    case CHAT: {
                        if(Client.instance().local.size == 1 && !Client.instance().getWorld().isGameOver()) {
                            this.status = ScreenState.TYPING;

                            // This prevents the player from keep moving in the direction they were facing
                            Client.instance().sendEntityUpdate(State.STOPPED);
                        }
                        break;
                    }
                    case ACCEPT: {
                        if(pressEnterOption && Client.instance().getWorld().
                           isWorldInitiated()) {
                            Client.instance().error = ResponseCode.GAME_OVER;
                            ScreenScore screen = new ScreenScore();
                            CoreBC.changeScreen(screen);
                            return true;
                        }
                        break;
                    }
                    case PAUSE: {
                        pauseGame();
                        break;
                    }
                    case UNBINDED: {
                        break;
                    }
                }
                break;
            case TYPING:
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public boolean buttonUp(Controller cntrlr, int i) {
        int pid = getControllerID(cntrlr);

        if(pid == -1) {
            if(Presets.debugOn)
                System.out.println("No controller(s) detected.");

            return false;
        }

        KeyActions action = Parameters.joyMaps.get(i, KeyActions.UNBINDED);

        switch(status) {
            case DEFAULT: {
                switch(action) {
                    case ACTION: {
                        PlayerTank pt = Client.instance().local.get(pid);
                        pt.setAction(Action.NORMAL);

                        if(pid == 0)
                            Client.instance().sendEntityUpdate(Action.NORMAL);

                        break;
                    }
                    case SHOW_INFO: {
                        showNames = false;
                        break;
                    }
                    case UNBINDED: {
                        break;
                    }
                }
                break;
            }
        }

        return true;
    }

    @Override
    public void show() {
        super.show();
        paused = false;
        quit = false;
    }

    @Override
    public void draw(SpriteBatch batch, float d) {
        final World world = Client.instance().getWorld();

        if(!world.isWorldInitiated()) {

            batch.end();
            ShapeRenderer spRender = new ShapeRenderer();
            spRender.setAutoShapeType(true);
            spRender.setProjectionMatrix(batch.getProjectionMatrix());
            spRender.begin();

            spRender.set(ShapeRenderer.ShapeType.Filled);
            spRender.setColor(Color.GRAY);
            spRender.
                    box(0, 0, 0, camera.viewportWidth, camera.viewportHeight, 0);
            spRender.end();
            batch.begin();
            AssetsGame.manager().defaultFont.setColor(Color.BLACK);
            AssetsGame.manager().defaultFont.getData().setScale(0.25f);

            BaseLevel level = world.getCurrentLevel();

            if(level == null)
                return;

            int lvlID = level.getLevelID();

            if(lvlID >= Presets.MOD_ID_START) {
                String name = ((LevelMod)world.getCurrentLevel()).toString();
                AssetsGame.manager().defaultFont.draw(batch, name,
                                                      camera.viewportWidth * 0.5f, camera.viewportHeight * 0.5f, 1,
                                                      Align.center, false);
            }
            else {
                AssetsGame.manager().defaultFont.draw(batch, "STAGE " + String.valueOf(lvlID),
                                                      camera.viewportWidth * 0.5f, camera.viewportHeight * 0.5f, 1,
                                                      Align.center, false);
            }
            return;
        }

        Sprite sidebar = AssetsGame.manager().textures.get("sidebar");
        sidebar.setPosition(world.getWidth(), 0);
        batch.setProjectionMatrix(camera.combined);
        sidebar.draw(batch);

        for(Tile tile : world.getCurrentLevel().tileList) {
            RenderRegistry.getRenderer(tile.getClass()).render(tile);
        }

        Tile eagle = world.getCurrentLevel().getEagle();

        if(eagle != null)
            RenderRegistry.getRenderer(Eagle.class).render(eagle);

        batch.end();
        Batch.SPRENDERER.setAutoShapeType(true);
        Batch.SPRENDERER.setProjectionMatrix(batch.getProjectionMatrix());
        Batch.SPRENDERER.begin();
        for(Tile tile : world.getCurrentLevel().damagedTileList) {
            TileDamageRenderEntity.render(tile);
        }
        Batch.SPRENDERER.end();
        batch.begin();
        for(Entity e : world.getEntities()) {

            if(e instanceof PlayerTank) {
                if(showNames) {
                    float stringX = e.getX();
                    float stringY = e.getY() + e.getHeight();
                    float fontHeight = AssetsGame.
                            manager().defaultFont.getLineHeight();

                    AssetsGame.manager().defaultFont.setColor(Color.WHITE);
                    AssetsGame.manager().defaultFont.getData().setScale(0.18f);

                    String name = ((PlayerTank)e).getName() + "( #" + ((PlayerTank)e).getPlayerID() + ")";
                    String lives = "lives: " + ((PlayerTank)e).getPlayerLives();

                    AssetsGame.manager().defaultFont.draw(batch,
                                                          name + "\n" + lives, stringX,
                                                          stringY + fontHeight * 2.7f);
                }

                if(Presets.debugOn) {
                    float lineHeight = AssetsGame.manager().defaultFont.
                            getLineHeight();
                    float stringX = e.getX();
                    float stringY = e.getY() + e.getHeight() + lineHeight;

                    AssetsGame.manager().defaultFont.setColor(Color.WHITE);
                    AssetsGame.manager().defaultFont.getData().setScale(0.18f);
                    AssetsGame.manager().defaultFont.draw(batch,
                                                          "Power name: " + ((PlayerTank)e).getPowerUp().
                                                                  name(), stringX, stringY + lineHeight);
                    AssetsGame.manager().defaultFont.draw(batch,
                                                          "Power dur.: " + String.valueOf(((PlayerTank)e).
                                                                  getPowerUpTimeLeft()), stringX, stringY +
                                                                                                  lineHeight + lineHeight);
                    AssetsGame.manager().defaultFont.draw(batch, "Tier: " +
                                                                 String.valueOf(((PlayerTank)e).getTier().name()),
                                                          stringX, stringY +
                                                                   lineHeight + lineHeight + lineHeight);
                }
            }

            RenderRegistry.getRenderer(e.getClass()).render(e);
        }

        /**
         * Special tiles
         */
        for(Tile tile : world.getCurrentLevel().specialTilesList) {
            RenderRegistry.getRenderer(tile.getClass()).render(tile);
        }

        /**
         * Side bar stuff
         */
        AssetsGame.manager().defaultFont.setColor(Color.BLACK);
        AssetsGame.manager().defaultFont.getData().setScale(.18f);

        AssetsGame.manager().defaultFont.draw(batch, String.valueOf(
                                              world.getCurrentLevel().getLevelID()), sidebar.getX() +
                                                                                     (sidebar.getWidth() * .52f),
                                              world.getHeight() * .2f);

        AssetsGame.manager().defaultFont.draw(batch, String.valueOf(Client.instance().getPlayerTank().getPlayerLives()), sidebar.getX() + (sidebar.
                                                                                                                                           getWidth() * .56f), world.getHeight() * .45f);

        if(Server.isServerRunning() && Server.localCoop && Client.instance().local.size == 2) {
            TextureRegion t = AssetsGame.manager().textures.get("twoplayericon");
            batch.draw(t, sidebar.getX() + (sidebar.
                                            getWidth() * .56f) - (t.getRegionWidth() * .5f), world.getHeight() * .45f - (t.getRegionHeight() * 1.75f));
            AssetsGame.manager().defaultFont.draw(batch, String.valueOf(Client.instance().local.get(1).getPlayerLives()), sidebar.getX() + (sidebar.
                                                                                                                                            getWidth() * .56f), world.getHeight() * .45f - (t.getRegionHeight() * 1.45f));
        }

        int totalLives = 0;
        int alive = 0;

        for(PlayerTank pt : Client.instance().local) {
            totalLives += pt.getPlayerLives();

            if(pt.getState() != State.DEAD)
                alive += 1;
        }

        AssetsGame.manager().defaultFont.draw(batch, String.valueOf(
                                              Parameters.playerScore),
                                              sidebar.getX() + (sidebar.
                                                                getWidth() * .5f), world.getHeight() * .05f, 1, Align.center,
                                              false);

        if(world.getCurrentLevel().getMode() == BaseLevel.Mode.ARCADE) {
            Sprite enemyIcon = AssetsGame.manager().textures.get("enemy_icon");
            int enemies_total = Parameters.totalEnemiesCount;
            float iw = enemyIcon.getWidth() * 0.5f;
            float ix = sidebar.getX() + iw;
            float iy = world.getHeight() - enemyIcon.getHeight();

            int i = 0;
            int h = 0;

            while(i < enemies_total) {

                switch(h) {
                    case 0:
                        ix = sidebar.getX() + iw;
                        break;
                    case 1:
                        ix += enemyIcon.getWidth();
                        break;
                    default:
                        ix = sidebar.getX() + iw;
                        iy -= enemyIcon.getHeight();
                        h = 0;
                        break;
                }

                h++;
                i++;

                enemyIcon.setPosition(ix, iy);
                enemyIcon.draw(batch);
            }
        }

        if(world.isGameOver() || (alive == 0 && totalLives == 0 &&
                                  world.getCurrentLevel().getMode() == BaseLevel.Mode.ARCADE)) {
            Sprite gameOverSprite = AssetsGame.manager().textures.get(
                    "game_over");
            gameOverSprite.draw(batch);

            if(gameOverSprite.getY() < world.getHeight() * .5f) {
                gameOverSprite.setY(gameOverSprite.getY() + 1f);
            }
            else if(disposeTimer == null) {

                if(world.isGameOver()) {
                    if(!gameOverQuit) {
                        Task t = new Task()
                        {
                            @Override
                            public void run() {
                                gameOverQuit = true;
                            }
                        };

                        World.getTimerManager().scheduleTask(t, 2000);
                    }
                    else {
                        Client.instance().error = ResponseCode.GAME_OVER;
                        ScreenScore screen = new ScreenScore();
                        CoreBC.changeScreen(screen);
                        return;
                    }
                }
                else {
                    pressEnterOption = true;

                    if(showPressEnter) {
                        float x = world.getWidth() * 0.5f;
                        float y = world.getHeight() -
                                  AssetsGame.manager().defaultFont.
                                          getLineHeight();
                        AssetsGame.manager().defaultFont.setColor(
                                Color.WHITE);
                        AssetsGame.manager().defaultFont.getData().setScale(
                                0.2f);
                        AssetsGame.manager().defaultFont.draw(batch,
                                                              "Press ENTER to exit", x, y, 1, Align.center,
                                                              false);
                    }

                    playerDeadAnimationTime++;

                    if(playerDeadAnimationTime > 60) {
                        playerDeadAnimationTime = 0;
                        showPressEnter = !showPressEnter;
                    }
                }
            }
        }
        else if(world.getCurrentLevel().getLevelID() == LevelEnd.LEVEL_END_ID) {
            float stringX = world.getWidth() * 0.5f;
            float stringY = world.getHeight() * 0.5f;
            AssetsGame.manager().defaultFont.setColor(Color.LIGHT_GRAY);
            AssetsGame.manager().defaultFont.getData().scale(0.56f);

            if(Parameters.mode == BaseLevel.Mode.ARCADE) {
                AssetsGame.manager().defaultFont.draw(batch, "You won!", stringX,
                                                      stringY, 1, Align.center, true);
            }
        }

        if(status == ScreenState.TYPING) {
            ChatRenderer.renderChatInput();
        }
        else if(!Client.instance().getChatText().isEmpty()) {
            boolean timeUp = ChatRenderer.renderChatOutput(Client.instance().
                    getChatText());

            if(timeUp) {
                Client.instance().emtpyChatBuffer();
            }
        }
        else if(Presets.debugOn) {
            float stringX = sidebar.getX() * 1.1f;
            float stringY = sidebar.getY() + AssetsGame.manager().defaultFont.
                            getLineHeight();
            AssetsGame.manager().defaultFont.draw(batch, "Debuggin", stringX,
                                                  stringY);
        }

        if(paused) {
            pauseBlink += .01f;

            if(pauseBlink > .25f)
                pauseBlink = 0f;

            AssetsGame.manager().defaultFont.setColor(Color.RED);
            AssetsGame.manager().defaultFont.draw(batch, pauseBlink > .125f ? "PAUSED" : "", world.getWidth() * .425f, world.getHeight() * .97f);
        }

        if(quit) {
            AssetsGame.manager().defaultFont.setColor(Color.YELLOW);
            AssetsGame.manager().defaultFont.draw(batch, pauseBlink > .125f ? "HIT ESC AGAIN TO QUIT" : "", world.getWidth() * .22f, world.getHeight() * .57f);
        }

    }

    @Override
    protected void update() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {

            if(paused) {
                if(quit) {
                    ScreenMainMenu screen = new ScreenMainMenu();
                    CoreBC.changeScreen(screen);
                    return;
                }
                else
                    quit = true;
            }
        }

        if(paused)
            return;

        if(Server.isServerRunning()) {
            Server.getInstance().update();
        }

        if(Client.instance().isConnected()) {
            Client.instance().update();
        }

//        System.out.println("FPS: " + Gdx.app.getGraphics().getFramesPerSecond());
    }

    @Override
    public void resize(int i, int i1
    ) {
        super.resize(i, i1);
        float w = camera.viewportWidth - (AssetsGame.manager().SIDEBAR_WIDTH);
        float h = camera.viewportHeight;

        if(Client.instance().getWorld() != null) {
            Client.instance().getWorld().setWorldBounds(w, h);
        }
    }

    @Override
    public void dispose() {
        Client.instance().shutdown();

        if(Server.isServerRunning()) {
            Server.getInstance().shutdownServer();
        }

        AssetsGame.manager().dispose();
    }

    int playerID = Client.instance().getID();

    @Override
    public boolean keyDown(int keycode) {

        if(!Client.instance().getWorld().isWorldInitiated() && Client.instance().getWorld().getCurrentLevel().getLevelID() != LevelEnd.LEVEL_END_ID)
            return true;

        KeyActions action = Parameters.keyMaps.get(keycode, KeyActions.UNBINDED);

        if(action == null)
            return true;

        if(paused && action != KeyActions.PAUSE)
            return true;

        int pid = (Client.instance().local.size > 1 && action.name().startsWith("P2")) ? 1 : 0;
        PlayerTank pt = Client.instance().local.get(pid);

        switch(status) {
            case DEFAULT:
                switch(action) {
                    case MOVE_UP:
                    case P2_MOVE_UP:
                        pt.direction.set(0, 1);
                        break;
                    case MOVE_DOWN:
                    case P2_MOVE_DOWN:
                        pt.direction.set(0, -1);
                        break;
                    case MOVE_RIGHT:
                    case P2_MOVE_RIGHT:
                        pt.direction.set(1, 0);
                        break;
                    case MOVE_LEFT:
                    case P2_MOVE_LEFT:
                        pt.direction.set(-1, 0);
                        break;
                    case ACTION:
                    case P2_ACTION: {
                        pt.setAction(Action.SHOOTING);
                        break;
                    }
                    case SHOW_INFO: {
                        if(Client.instance().getWorld().getCurrentLevel().getMode() == BaseLevel.Mode.ARCADE)
                            showNames = true;
                        break;
                    }
                    case VOLUME_UP: {
                        Parameters.setVolume(true);
                        break;
                    }
                    case VOLUME_DOWN: {
                        Parameters.setVolume(false);
                        break;
                    }
                    case CHAT: {
                        if(Client.instance().local.size == 1 && !Client.instance().getWorld().isGameOver()) {
                            this.status = ScreenState.TYPING;

                            // This prevents the player from keep moving in the direction they were facing
                            Client.instance().sendEntityUpdate(State.STOPPED);
                        }
                        break;
                    }
                    case ACCEPT: {
                        if(pressEnterOption && Client.instance().getWorld().
                           isWorldInitiated()) {
                            Client.instance().error = ResponseCode.GAME_OVER;
                            ScreenScore screen = new ScreenScore();
                            CoreBC.changeScreen(screen);
                            return true;
                        }
                        break;
                    }
                    case PAUSE: {
                        pauseGame();
                        break;
                    }
                }
                break;
            case TYPING:
                switch(action) {
                    case CANCEL: {
                        this.status = ScreenState.DEFAULT;
                        break;
                    }
                    case ACCEPT: {
                        this.status = ScreenState.DEFAULT;

                        if(ChatRenderer.chatText.equalsIgnoreCase("/quit") ||
                           ChatRenderer.chatText.equalsIgnoreCase(
                                   "/exit") ||
                           ChatRenderer.chatText.
                                   equalsIgnoreCase("/q")) {
                            Client.instance().error = ResponseCode.CLIENT_DISCONNECTED;
                            GameScreen screen = new ScreenMainMenu();
                            CoreBC.changeScreen(screen);
                            return true;
                        }
                        else if(!ChatRenderer.chatText.isEmpty()) {
                            Client.instance().
                                    sendChat(ChatRenderer.chatText);
                            ChatRenderer.onChatSent();
                        }
                        break;
                    }
                    case PREVIOUS_OPTION: {
                        ChatRenderer.deleteCharacter();
                        break;
                    }
                }
                break;
            default:
                break;
        }

        if(pt.getState() != State.DEAD && !Client.instance().getWorld().isGameOver()) {
            if(!pt.direction.isZero()) {
                pt.setState(State.MOVING);
                pt.setFacing(pt.direction);
            }
            else
                pt.setState(State.STOPPED);

            if(pid == 0)
                Client.instance().sendEntityUpdate(pt.getState(), pt.getFacing(), pt.getAction());
        }

        return true;
    }

    private void pauseGame() {
        if(!Server.localCoop)
            return;

        if(!Client.instance().getWorld().isWorldInitiated())
            return;

        if(Client.instance().getWorld().isGameOver())
            return;

        if(status == ScreenState.TYPING)
            return;

        paused = !paused;

        if(!paused) {
            quit = false;
        }
    }

    @Override
    public boolean keyUp(int keycode) {
        if(!Client.instance().getWorld().isWorldInitiated())
            return true;

        KeyActions action = Parameters.keyMaps.get(keycode);

        if(action == null)
            return false;

        int pid = (Client.instance().local.size > 1 && action.name().startsWith("P2")) ? 1 : 0;
        PlayerTank pt = Client.instance().local.get(pid);

        switch(status) {
            case DEFAULT: {
                switch(action) {
                    case MOVE_UP:
                    case P2_MOVE_UP:
                        if(pt.direction.y > 0)
                            pt.direction.y = 0;
                        break;
                    case MOVE_DOWN:
                    case P2_MOVE_DOWN:
                        if(pt.direction.y < 0)
                            pt.direction.y = 0;
                        break;
                    case MOVE_RIGHT:
                    case P2_MOVE_RIGHT:
                        if(pt.direction.x > 0)
                            pt.direction.x = 0;
                        break;
                    case MOVE_LEFT:
                    case P2_MOVE_LEFT:
                        if(pt.direction.x < 0)
                            pt.direction.x = 0;
                        break;
                    case ACTION:
                    case P2_ACTION: {
                        pt.setAction(Action.NORMAL);
                        break;
                    }
                    case SHOW_INFO: {
                        showNames = false;
                        break;
                    }
                }
                break;
            }
        }

        if(pt.getState() != State.DEAD) {
            if(!pt.direction.isZero())
                pt.setState(State.MOVING);
            else
                pt.setState(State.STOPPED);

            if(pid == 0)
                Client.instance().sendEntityUpdate(pt.getState(), pt.getFacing(), pt.getAction());
        }

        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        if(this.status == ScreenState.TYPING) {
            ChatRenderer.appendToChat(character);
        }
        return true;
    }

}
