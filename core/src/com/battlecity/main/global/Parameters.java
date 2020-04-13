package com.battlecity.main.global;

import com.badlogic.gdx.*;
import com.badlogic.gdx.files.*;
import com.badlogic.gdx.utils.*;
import com.battlecity.main.assets.*;
import com.battlecity.main.global.Presets.KeyActions;
import com.battlecity.main.levels.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;



/**
 *
 * @author JH62
 */
public final class Parameters
{

    private static final Preferences PREFS = Gdx.app.getPreferences(
            "BattleCityPrefs");

    public static final int MAX_LEVELS = 35;
    public static final int JOYSTICK_CONSTANT = 255;

    public static String playerName = "Tank" + new Random().nextInt(100);
    public static int playerLives = 2;
    public static int startingLevel = 1;
    public static boolean friendlyFire = false;
    public static float volume = 0.5f;
    public static boolean randomEnemies = false;
    public static int totalEnemiesCount = 20;
    public static int playerScore = 0;
    public static int lastLevel = 1;
    public static int[] enemiesKilledByPlayer = new int[4];
    public static int deaths = 0;
    public static boolean restrictJoin = true;
    public static String lastIP = "127.0.0.1";
    public static BaseLevel.Mode mode = BaseLevel.Mode.ARCADE;
    public static final IntMap<KeyActions> keyMaps = new IntMap();
    public static final IntMap<KeyActions> joyMaps = new IntMap();

    public static void load() {
        playerName = PREFS.getString("playerName", playerName);
//        startingLevel = PREFS.getInteger("startingLevel", 1);
        playerLives = PREFS.getInteger("playerLives", 2);
        friendlyFire = PREFS.getBoolean("friendlyFire", false);
        randomEnemies = PREFS.getBoolean("randomEnemies", false);
        restrictJoin = PREFS.getBoolean("restrictJoin", true);
        lastIP = PREFS.getString("lastIP", lastIP);
        Parameters.reloadMappings();
        System.out.println("Preferences loaded!");
    }
 
    public static void save() {
        PREFS.putString("playerName", playerName);
        PREFS.putString("lastIP", lastIP);
//        PREFS.putInteger("startingLevel", startingLevel);
        PREFS.putInteger("playerLives", playerLives);
        PREFS.putBoolean("friendlyFire", friendlyFire);
        PREFS.putBoolean("randomEnemies", randomEnemies);
        PREFS.putBoolean("restrictJoin", restrictJoin);

        PREFS.flush();
        Parameters.reloadMappings();
        System.out.println("Preferences saved!");
    }

    public static void setVolume(boolean increase) {
        volume = increase ? volume + 0.1f : volume - 0.1f;

        if(volume > 1.0f) {
            volume = 1.0f;
        }
        else if(volume < 0.0f) {
            volume = 0.0f;
        }

        AssetsGame.manager().soundManager.updateVolume();
    }

    public static JsonValue getMappingsFile() {
        FileHandle fh = Gdx.files.local("input.json");

        if(!fh.exists()) {
            fh = Gdx.files.internal("data/input.json");
            if(Presets.debugOn)
                System.out.println("Loading internal input.json");
        }

        JsonValue file = new Json().fromJson(null, fh);
        return file;
    }

    public static void reloadMappings() {
        JsonValue file = getMappingsFile();

        Parameters.keyMaps.clear();
        Parameters.joyMaps.clear();
        
        for(JsonValue js : file) {
            try {
                if(js.name.startsWith("JOY")) {
                    Parameters.joyMaps.put(js.getInt("value"), KeyActions.valueOf(js.name.substring(4)));
                }
                else {
                    Parameters.keyMaps.put(js.getInt("value"), KeyActions.valueOf(js.name));
                }

            }
            catch(IllegalArgumentException e) {
                System.out.println("input.json has an error: " + e.getMessage());
            }
        }

        if(Presets.debugOn)
            System.out.println("Mappings reloaded!");
    }

}
