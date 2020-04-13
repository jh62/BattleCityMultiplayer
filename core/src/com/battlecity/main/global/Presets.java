package com.battlecity.main.global;

import com.badlogic.gdx.*;
import com.badlogic.gdx.files.*;
import java.awt.*;



/**
 *
 * @author Pablis
 */
public abstract class Presets
{
    public static enum KeyActions
    {
        UNBINDED,
        MOVE_UP,
        MOVE_LEFT,
        MOVE_RIGHT,
        MOVE_DOWN,
        ACTION,
        P2_MOVE_UP,
        P2_MOVE_LEFT,
        P2_MOVE_RIGHT,
        P2_MOVE_DOWN,
        P2_ACTION,
        CANCEL,
        ACCEPT,
        PREVIOUS_OPTION,
        CHAT,
        SHOW_INFO,
        VOLUME_UP,
        VOLUME_DOWN,
        PAUSE,
        HELP,
        JOY_Y,
        JOY_X;
    }

    public static boolean debugOn = false;

    public static final FileHandle MOD_PATH = Gdx.files.local("mod/");
    public static final String MOD_EXTENSION = ".bcm";
    public static final int MOD_ID_START = 36;

    public static final String TEXTURE_ENTITIES = "data/entity/";
    public static final String TEXTURE_UI = "data/ui/";
    public static final String SOUNDS = "data/sfx/";

    public static final int TILE_REGIONS = 4;
    public static final int TILE_FULL_SIZE = 16;
    public static final int TILE_REGION_SIZE = 8;

    public static final int ENTITY_WIDTH = 13;
    public static final int ENTITY_HEIGHT = 13;

    public static final float ENTITY_VELOCITY_NORMAL = 0.7f;
    public static final float ENTITY_VELOCITY_SLOW = 0.5f;
    public static final float ENTITY_VELOCITY_FAST = 0.9f;

    public static final float BULLET_NORMAL = 3.0f;
    public static final float BULLET_SLOW = 2.0f;
    public static final float BULLET_FAST = 4.0f;

    public static final float BULLET_WIDTH = TILE_REGION_SIZE;
    public static final float BULLET_HEIGHT = TILE_REGION_SIZE;

    public static final int POWERUP_DURATION = 20000;
    public static final int PLAYER_ATTACK_DELAY_MS = 175; // not working currently, look in PlayerTank.isShooting();
    public static final float PLAYER_ATTACK_DAMAGE = .33f;

    public static final int DEATHMATCH_SHOW_HEALTH_TIME = 1000;
    public static final int DEATHMATCH_POWERUP_DELAY = 20000; //default: 20000
    
    public static final int BOUNCE_FIELD_HITPOINTS = 3;

    public static final float PUSH_FORCE = .035f;

    public static final int NEW_LIFE_SCORE = 20000;

    public static final int MAX_TILE_HORIZONTAL = 13;
    public static final int MAX_TILE_VERTICAL = 13;

    public static final int SIDEBAR_WIDTH = 32;
    public static final int SIDEBAR_HEIGHT = 240;

    public static final int WORLD_WIDTH = MAX_TILE_HORIZONTAL * TILE_FULL_SIZE;
    public static final int WORLD_HEIGHT = MAX_TILE_VERTICAL * TILE_FULL_SIZE;

    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 699;

    public static final int FRIENDLY_FIRE_FREEZE_TIME = 2500;

    public static boolean EAGLE_WALLS_PROTECTED = false;
    public static boolean EAGLE_WALLS_PROTECTION_FADE = false;

}
