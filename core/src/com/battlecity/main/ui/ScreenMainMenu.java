package com.battlecity.main.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.controllers.*;
import com.badlogic.gdx.files.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.*;
import com.battlecity.main.CoreBC;
import com.battlecity.main.assets.AssetsMainMenu;
import com.battlecity.main.entity.*;
import com.battlecity.main.entity.tank.*;
import com.battlecity.main.global.*;
import static com.battlecity.main.global.Parameters.keyMaps;
import com.battlecity.main.global.Presets.KeyActions;
import com.battlecity.main.global.enums.*;
import com.battlecity.main.levels.*;
import com.battlecity.main.net.*;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 *
 * @author Pablis
 */
public class ScreenMainMenu extends GameScreen
{

    private enum STATE
    {
        SELECTION,
        ENTER_IP,
        OPTIONS,
        ABOUT,
        ENTER_NAME,
        OPTIONS_KEYMAP;
    }

    private abstract class MenuAction
    {
        public abstract void onActivation(STATE menuState);

        public void onDeactivation(STATE menuState) {
        }

    ;

    }

    private final ObjectMap<Presets.KeyActions, MenuAction[]> menuActions = new ObjectMap();

    private final AssetsMainMenu assets;
    private int row = 0;
    private STATE state = STATE.SELECTION;
    private String ipAdress = "";
    private final Sound snd = Gdx.audio.newSound(Gdx.files.internal(
            "data/sfx/song.ogg"));

    private final String creditsText;

    public ScreenMainMenu() {
        assets = new AssetsMainMenu();

        try {
            Class.forName(Parameters.class.getName());
            Parameters.load();
        }
        catch(ClassNotFoundException ex) {
            Logger.getLogger(ScreenMainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

        ipAdress = Parameters.lastIP;

        if(Parameters.startingLevel <= 0)
            Parameters.startingLevel = 1;

        creditsText = Gdx.files.internal("data/eula").readString();
        Presets.MOD_PATH.mkdirs();
        scanForMods();

        menuActions.put(KeyActions.HELP, new MenuAction[]{action_Help});
        menuActions.put(KeyActions.MOVE_UP, new MenuAction[]{action_moveUp});
        menuActions.put(KeyActions.MOVE_DOWN, new MenuAction[]{action_moveDown});
        menuActions.put(KeyActions.ACTION, new MenuAction[]{action_next});
        menuActions.put(KeyActions.P2_MOVE_UP, new MenuAction[]{action_moveUp});
        menuActions.put(KeyActions.P2_MOVE_DOWN, new MenuAction[]{action_moveDown});
        menuActions.put(KeyActions.P2_ACTION, new MenuAction[]{action_next});
        menuActions.put(KeyActions.ACCEPT, new MenuAction[]{action_accept, action_next});
        menuActions.put(KeyActions.PREVIOUS_OPTION, new MenuAction[]{action_prev});
        menuActions.put(KeyActions.CANCEL, new MenuAction[]{action_cancel});
    }

    @Override
    public boolean axisMoved(Controller cntrlr, int axis, float dir) {
        int pid = getControllerID(cntrlr);

        if(pid != 0)
            return true;

        KeyActions k = KeyActions.UNBINDED;
        int direction = Math.round(dir);

        if(axis == 0) {
            if(direction == -1)
                k = KeyActions.MOVE_UP;
            else if(direction == 1)
                k = KeyActions.MOVE_DOWN;
        }
        else if(axis == 1) {
            if(direction == 1)
                k = KeyActions.MOVE_RIGHT;
            else if(direction == -1)
                k = KeyActions.MOVE_LEFT;
        }

        MenuAction[] act = menuActions.get(k);

        if(act != null) {
            for(MenuAction m : act) {
                m.onActivation(state);
            }
        }

        return true;
    }

    @Override
    public boolean buttonDown(Controller cntrlr, int i) {
        int pid = getControllerID(cntrlr);

        if(pid != 0)
            return true;

        MenuAction[] act = menuActions.get(Parameters.joyMaps.get(i, KeyActions.UNBINDED));

        if(act != null) {
            for(MenuAction m : act) {
                m.onActivation(state);
            }
        }

        return true;
    }

    @Override
    public void draw(SpriteBatch batch, float d) {
        if(this.state != STATE.ABOUT) {
            batch.draw(assets.mainMenuTexture, 0, 0, camera.viewportWidth,
                       camera.viewportHeight);
        }

        switch(this.state) {
            case ABOUT: {
                float x = camera.viewportWidth * 0.5f;
                float y = camera.viewportHeight * 0.9f;

                assets.defaultFont.getData().setScale(.16f);
                assets.defaultFont.getData().markupEnabled = true;

                assets.defaultFont.
                        draw(batch, creditsText, x, y, 0.05f, Align.center, true);
                break;
            }
            case SELECTION: {
                batch.draw(assets.mainMenuTexture, 0, 0, camera.viewportWidth,
                           camera.viewportHeight);

                if(Client.instance().error != ResponseCode.NOREASON) {
                    float x = 0;
                    float y = camera.viewportHeight - assets.defaultFont.
                              getLineHeight() * .5f;
                    assets.defaultFont.getData().setScale(.2f);

                    if(Presets.debugOn) {
                        assets.defaultFont.
                                draw(batch, "EXIT CAUSED BY: " + Client.instance().error,
                                     x, y, camera.viewportWidth, Align.center, false);
                    }

                }

                assets.defaultFont.getData().setScale(.14f);
                assets.defaultFont.
                        draw(batch, "F1:About",
                             camera.viewportWidth * 0.99f,
                             assets.defaultFont.
                                     getLineHeight(), 1, Align.right, false);

                assets.defaultFont.setColor(Color.WHITE);
                assets.defaultFont.getData().setScale(.25f);

                final float textX = camera.viewportWidth * .5f;
                final float textY = camera.viewportHeight * .53f;
                final float fontHeight = assets.defaultFont.getCapHeight();

                assets.defaultFont.draw(batch, "Host game", textX, textY, 1,
                                        Align.center, false);
                assets.defaultFont.draw(batch, "Join game", textX, textY -
                                                                   (fontHeight * 2f), 1, Align.center, false);
                assets.defaultFont.draw(batch, "Two Players", textX, textY -
                                                                     (fontHeight * 4f), 1, Align.center, false);
                assets.defaultFont.draw(batch, "Options", textX, textY -
                                                                 (fontHeight * 6f), 1, Align.center, false);

                float iconX = camera.viewportWidth * .18f;
                float iconY = camera.viewportHeight * .53f;

                switch(row) {
                    case 0: {
                        iconY = iconY - (fontHeight * .5f) -
                                (assets.selectionIcon.getHeight() * .5f);
                        break;
                    }
                    case 1: {
                        iconY = iconY - (fontHeight * 2.5f) -
                                (assets.selectionIcon.getHeight() * .5f);
                        break;
                    }
                    case 2: {
                        iconY = iconY - (fontHeight * 4.5f) -
                                (assets.selectionIcon.getHeight() * .5f);
                        break;
                    }
                    case 3: {
                        iconY = iconY - (fontHeight * 6.5f) -
                                (assets.selectionIcon.getHeight() * .5f);
                        break;
                    }
                }

                assets.selectionIcon.setPosition(iconX, iconY);
                assets.selectionIcon.draw(batch);
                break;
            }

            case ENTER_IP: {
                float textX = (camera.viewportWidth * .5f);
                float textY = (camera.viewportHeight * .44f);
                assets.defaultFont.setColor(Color.WHITE);
                assets.defaultFont.getData().setScale(.16f);
                assets.defaultFont.draw(batch, "TCP: " + Server.TCP + " UDP: " + Server.UDP + " need to be unblocked!", textX, textY, 1,
                                        Align.center, false);
                assets.defaultFont.getData().setScale(.25f);
                assets.defaultFont.draw(batch, "Enter IP:", textX, textY - (assets.defaultFont.getLineHeight() * 1.5f), 1,
                                        Align.center, false);
                if(ipAdress != null && ipAdress.length() > 0) {
                    assets.defaultFont.getData().setScale(.35f);
                    assets.defaultFont.setColor(Color.GRAY);
                    assets.defaultFont.draw(batch, ipAdress, textX,
                                            textY - (assets.defaultFont.getLineHeight() * 2), 1, Align.center, false);
                }
                break;
            }
            case OPTIONS:
            case ENTER_NAME: {
                final float startX = camera.viewportWidth * .3f;
                final float startY = camera.viewportHeight * .56f;
                final float lineH = assets.defaultFont.getLineHeight() * 1.75f;

                float textX = startX;
                float textY = startY;

                assets.defaultFont.setColor(Color.WHITE);
                assets.defaultFont.getData().setScale(.16f);

                assets.defaultFont.draw(batch, "Player name: " +
                                               Parameters.playerName +
                                               (this.state == STATE.ENTER_NAME ? "_" : ""), textX,
                                        textY, 1, Align.left, false);
                assets.defaultFont.draw(batch, "Lives: " +
                                               Parameters.playerLives, textX, textY -= lineH, 1,
                                        Align.left, false);
                assets.defaultFont.draw(batch, "Friendly fire: " +
                                               Parameters.friendlyFire, textX, textY -= lineH, 1,
                                        Align.left, false);
                assets.defaultFont.draw(batch, "Restrict joining: " +
                                               Parameters.restrictJoin, textX, textY -= lineH, 1,
                                        Align.left, true);

                if(Parameters.startingLevel >= Presets.MOD_ID_START) {
                    String name = BaseLevel.userLevels.get(Parameters.startingLevel - Presets.MOD_ID_START).toString();
                    assets.defaultFont.draw(batch, "Start level: " + name,
                                            textX,
                                            textY -= lineH, 1, Align.left, false);
                }
                else {
                    assets.defaultFont.draw(batch, "Start level: " +
                                                   Parameters.startingLevel + "/" + Parameters.MAX_LEVELS,
                                            textX,
                                            textY -= lineH, 1, Align.left, false);
                }

                assets.defaultFont.draw(batch, "Change controls", textX,
                                        textY -= lineH, 1, Align.left, false);

                float iconX = camera.viewportWidth * .12f;
                float iconY = startY - (assets.selectionIcon.getHeight() * .21f);

                if(row > 0) {
                    iconY -= lineH * row;
                }

                assets.selectionIcon.setCenter(iconX, iconY);
                assets.selectionIcon.draw(batch);
                break;
            }
            case OPTIONS_KEYMAP: {
                assets.defaultFont.setColor(Color.WHITE);
                assets.defaultFont.getData().setScale(.1f);

                final float COL_P1_X = camera.viewportWidth * .1f;
                final float COL_P2_X = camera.viewportWidth * .45f;
                final float PLAYER_ROWS_Y = camera.viewportHeight * .47f;

                final float COMMON_ROWS_Y = camera.viewportHeight * .3f;

                float col_p1_y = 0;
                float col_p2_y = 0;

                float col_count = 0;
                float col_x = 0;
                float col_y = 0;

                for(IntMap.Entry<Presets.KeyActions> keyMap : keyMaps) {
                    KeyActions k = keyMap.value;
                    int keycode = keyMap.key;

                    if(k == null || k.name().startsWith("UNBINDED"))
                        continue;

                    if(k.name().equalsIgnoreCase("cancel")) {
                        assets.defaultFont.setColor(Color.RED);
                    }
                    else {
                        assets.defaultFont.setColor(Color.WHITE);
                    }

                    if(k.name().startsWith("MOVE") || k.name().startsWith("ACTION")) {
                        assets.defaultFont.draw(batch, k.name() + ": " + Input.Keys.toString(keycode),
                                                COL_P1_X, PLAYER_ROWS_Y - col_p1_y, 1, Align.left, false);
                        col_p1_y -= assets.defaultFont.getLineHeight();
                    }
                    else if(k.name().startsWith("P2")) {
                        assets.defaultFont.draw(batch, k.name() + ": " + Input.Keys.toString(keycode),
                                                COL_P2_X, PLAYER_ROWS_Y - col_p2_y, 1, Align.left, false);
                        col_p2_y -= assets.defaultFont.getLineHeight();
                    }
                    else {
                        assets.defaultFont.draw(batch, k.name() + ": " + Input.Keys.toString(keycode),
                                                COL_P1_X + col_x, COMMON_ROWS_Y - col_y, 1, Align.left, false);
                        if(col_count < 5) {
                            col_count++;
                            col_y -= assets.defaultFont.getLineHeight();
                        }
                        else {
                            col_count = 0;
                            col_x = COL_P2_X - COL_P1_X;
                            col_y = 0;
                        }
                    }

                    assets.defaultFont.setColor(Color.YELLOW);
                    assets.defaultFont.draw(batch, "This settings can be overwritten in input.json", 0, viewport.getWorldHeight() * .25f, viewport.getWorldWidth(), Align.center, true);
                }

                break;
            }

        }

    }

    @Override
    public void show() {
        super.show();
        Parameters.deaths = 0;
    }

    @Override
    protected void update() {
    }

    @Override
    public void dispose() {
        assets.dispose();
    }

    private final IntSet downKeys = new IntSet(20);

    @Override
    public boolean keyDown(int keycode) {
        MenuAction[] act = menuActions.get(Parameters.keyMaps.get(keycode, KeyActions.UNBINDED));

        if(act != null) {
            for(MenuAction m : act) {
                m.onActivation(state);
            }
        }

        if(state == STATE.ENTER_IP) {

            downKeys.add(keycode);

            if(downKeys.size == 2 &&
               (downKeys.contains(Keys.CONTROL_LEFT) ||
                downKeys.contains(Keys.CONTROL_RIGHT)) &&
               downKeys.
                       contains(Keys.V)) {
                try {
                    ipAdress = (String)Toolkit.getDefaultToolkit()
                            .getSystemClipboard().getData(
                                    DataFlavor.stringFlavor);

                }
                catch(UnsupportedFlavorException | IOException ex) {
                    Logger.getLogger(ScreenMainMenu.class
                            .getName()).
                            log(Level.WARNING, null, ex);
                }
            }
        }

        return true;
    }

    @Override
    public boolean keyTyped(char character
    ) {
        if(this.state == STATE.ENTER_IP) {

            if(ipAdress.equals("Invalid IP!")) {
                ipAdress = "";
            }

            if(Character.isLetterOrDigit(character) || character == '.' ||
               character == '@') {
                ipAdress += character;
            }
        }
        else if(this.state == STATE.ENTER_NAME) {
            if(Character.isLetterOrDigit(character)) {
                Parameters.playerName += character;
            }
        }

        return true;
    }

    private void scanForMods() {
        BaseLevel.userLevels.clear();
        FileHandle[] files = Presets.MOD_PATH.list(Presets.MOD_EXTENSION);
        Gdx.app.log(this.getClass().getName(), "Searching for mods...");

        if(files.length > 0) {
            Gdx.app.log(this.getClass().toString(), "Found " + files.length + " files of user content.");
            Json json = new Json();

            int id = Presets.MOD_ID_START;

            for(FileHandle fl : files) {
                String name = fl.nameWithoutExtension();
                String[][] data = json.fromJson(String[][].class,
                                                Presets.MOD_PATH.child(name + Presets.MOD_EXTENSION));
                LevelMod mod = new LevelMod(id++, name, data);
                BaseLevel.userLevels.add(mod);
                Gdx.app.log(this.getClass().toString(), "Level " + name + " added to word list with id: " + id);
            }
        }
    }

    private final MenuAction action_Help = new MenuAction()
    {
        @Override
        public void onActivation(STATE menuState) {
            if(menuState == STATE.ABOUT) {
                state = STATE.SELECTION;
                row = 0;
                snd.stop();
            }
            else if(menuState == STATE.SELECTION) {
                state = STATE.ABOUT;
                snd.loop();
            }
        }
    };

    private final MenuAction action_moveUp = new MenuAction()
    {
        @Override
        public void onActivation(STATE menuState) {
            int maxRow = menuState == STATE.SELECTION ? 3 : 5;

            switch(menuState) {
                case OPTIONS:
                case SELECTION: {
                    row--;
                    if(row < 0) {
                        row = maxRow;
                    }
                    assets.selectionSound.play();
                    break;
                }
            }
        }

    };

    private final MenuAction action_moveDown = new MenuAction()
    {
        @Override
        public void onActivation(STATE menuState) {
            int maxRow = menuState == STATE.SELECTION ? 3 : 5;

            switch(menuState) {
                case OPTIONS:
                case SELECTION: {
                    row++;
                    if(row > maxRow) {
                        row = 0;
                    }
                    assets.selectionSound.play();
                    break;
                }
            }
        }

    };

    private MenuAction action_prev = new MenuAction()
    {
        @Override
        public void onActivation(STATE menuState) {
            switch(menuState) {
                case SELECTION: {

                    break;
                }
                case ENTER_IP: {
                    if(!ipAdress.isEmpty()) {
                        ipAdress = ipAdress.substring(0, ipAdress.
                                                      length() - 1);
                    }
                    break;
                }
                case ENTER_NAME: {
                    if(!Parameters.playerName.isEmpty()) {
                        Parameters.playerName = Parameters.playerName.
                                substring(0,
                                          Parameters.playerName.
                                                  length() - 1);
                    }
                    break;
                }
                case OPTIONS: {
                    if(row == 1 || row == 3 || row == 4) {
                        assets.selectSound.play();
                    }

                    switch(row) {
                        case 1: {
                            Parameters.playerLives--;

                            if(Parameters.playerLives < 1) {
                                Parameters.playerLives = 10;
                            }
                            break;
                        }
                        case 2: {
                            Parameters.friendlyFire = !Parameters.friendlyFire;
                            break;
                        }
                        case 3: {
                            Parameters.restrictJoin = !Parameters.restrictJoin;
                            break;
                        }
                        case 4: {
                            Parameters.startingLevel--;

                            if(Parameters.startingLevel <= LevelEnd.LEVEL_END_ID) {
                                Parameters.startingLevel = (35 + BaseLevel.userLevels.size());
                            }
                            break;
                        }
                    }
                    break;
                }
            }
        }
    };

    private MenuAction action_next = new MenuAction()
    {
        @Override
        public void onActivation(STATE menuStatew) {
            switch(menuStatew) {
                case SELECTION: {
                    switch(row) {
                        case 0:
                        case 2: {

                            if(Server.isServerRunning()) {
                                System.out.println(
                                        "A server is already running on localhost.");
                                break;
                            }

                            try {
                                Server.getInstance().initialize(row == 2);
                                Server.getInstance().getWorld().changeLevel(
                                        com.battlecity.main.levels.BaseLevel.
                                                getLevel(
                                                        Parameters.startingLevel));
                                Client.instance().initialize("localhost",
                                                             Parameters.playerName);
                                ScreenGame screen = new ScreenGame();
                                CoreBC.changeScreen(screen);
                                System.out.println(
                                        "Initializing local server...");

                            }
                            catch(IOException ex) {
                                Logger.getLogger(ScreenMainMenu.class
                                        .
                                        getName()).
                                        log(Level.SEVERE, null, ex);
                                Client.instance().error = ResponseCode.ADDRESS_ALREADY_IN_USE;
                                GameScreen screen = new ScreenMainMenu();
                                CoreBC.changeScreen(screen);
                            }
                            break;
                        }
                        case 1: {
                            state = STATE.ENTER_IP;
                            break;
                        }
                        case 3: {
                            row = 0;
                            state = STATE.OPTIONS;
                            assets.selectionIcon.setScale(.75f);
                            scanForMods();
                            break;
                        }
                    }
                    break;
                }
                case OPTIONS: {
                    assets.selectSound.play();
                    switch(row) {
                        case 1: {
                            Parameters.playerLives++;
                            if(Parameters.playerLives > 10) {
                                Parameters.playerLives = 1;
                            }
                            break;
                        }
                        case 2: {
                            Parameters.friendlyFire = !Parameters.friendlyFire;
                            break;
                        }
                        case 3: {
                            Parameters.restrictJoin = !Parameters.restrictJoin;
                            break;
                        }
                        case 4: {
                            Parameters.startingLevel++;

                            if(Parameters.startingLevel == LevelEnd.LEVEL_END_ID) {
                                Parameters.startingLevel++;
                            }
                            else if(Parameters.startingLevel > (35 + BaseLevel.userLevels.size()))
                                Parameters.startingLevel = 1;
                            break;
                        }
                        case 5: {
                            Parameters.reloadMappings();
                            state = STATE.OPTIONS_KEYMAP;
                            break;
                        }
                    }
                    break;
                }
            }
        }
    };

    private final MenuAction action_accept = new MenuAction()
    {
        @Override
        public void onActivation(STATE menuState) {
            switch(menuState) {
                case ENTER_IP: {
                    Pattern ipValidate = Pattern.compile(
                            "([0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3})");
                    Matcher matcher = ipValidate.matcher(ipAdress);

                    if(matcher.matches()) {
                        String ip = matcher.group(1);
                        Parameters.lastIP = ip;
                        Parameters.save();

                        try {
                            Client.instance().initialize(
                                    ip,
                                    Parameters.playerName);

                        }
                        catch(IOException ex) {
                            Logger.getLogger(ScreenMainMenu.class
                                    .getName()).
                                    log(Level.SEVERE, null, ex);
                        }

                        if(Client.instance().isConnected()) {
                            ScreenGame gameScreen = new ScreenGame();
                            CoreBC.changeScreen(gameScreen);
                        }
                        else {
                            Client.instance().error = ResponseCode.SERVER_UNAVAILABLE;
                            ScreenMainMenu screen = new ScreenMainMenu();
                            CoreBC.changeScreen(screen);
                        }
                    }
                    else {
                        ipAdress = "Invalid IP!";
                    }
                    break;
                }
                case OPTIONS: {
                    if(row == 0) {
                        state = STATE.ENTER_NAME;
                    }
                    break;
                }
                case ENTER_NAME: {
                    state = STATE.OPTIONS;
                    break;
                }
            }
        }

    };

    private final MenuAction action_cancel = new MenuAction()
    {
        @Override
        public void onActivation(STATE menuState) {
            switch(menuState) {
                case SELECTION: {

                    break;
                }
                case ENTER_NAME: {
                    state = STATE.OPTIONS;
                    row = 0;
                    break;
                }
                case ENTER_IP:
                case OPTIONS: {
                    state = STATE.SELECTION;
                    assets.selectionIcon.setScale(1f);
                    row = 0;
                    Parameters.save();
                    break;
                }
                case OPTIONS_KEYMAP: {
                    state = STATE.OPTIONS;
                    row = 0;
                    break;
                }
            }
        }
    };

}
