package com.battlecity.main.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.battlecity.main.global.Presets;
import com.battlecity.main.sound.SFXS;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Pablis
 */
public class AssetsGame extends Assets {

    private static AssetsGame instance;

    public final float SIDEBAR_WIDTH, SIDEBAR_HEIGHT;

    public final HashMap<String, Sprite> textures;
    public final HashMap<String, Animation> animations;

    private AssetManager manager;

    private final AssetDescriptor ASSET_ATLAS;

    private final TextureAtlas ATLAS;

    public final BitmapFont defaultFont, chatFont;

    public final SFXS soundManager;

    private static final String DEFAULT_FONT = "emulogic.fnt";
    private static final String CHAT_FONT = "consolas.fnt";

    private AssetsGame() {
        this.ASSET_ATLAS = new AssetDescriptor(
                Presets.TEXTURE_ENTITIES + "sprites.pack", TextureAtlas.class);
        this.animations = new HashMap<>();
        this.textures = new HashMap<>();

        manager = new AssetManager();
        manager.load(ASSET_ATLAS);
        manager.load(Presets.TEXTURE_UI + DEFAULT_FONT, BitmapFont.class);
        manager.load(Presets.TEXTURE_UI + CHAT_FONT, BitmapFont.class);

        manager.finishLoading();

        ATLAS = (TextureAtlas) manager.get(ASSET_ATLAS);

        for (TextureRegion r : ATLAS.getRegions()) {

            if (textures.containsKey(r.toString()) || animations.containsKey(r.
                    toString())) {
                continue;
            }

            int idx = 0;
            AtlasRegion region;
            ArrayList<TextureRegion> frames = new ArrayList<>();

            while ((region = ATLAS.findRegion(r.toString(), idx)) != null) {
                frames.add(region);
                idx++;
            }

            if (frames.size() > 1) {
                TextureRegion[] animFrames = new TextureRegion[idx];
                
                float duration = .1f;
                
                if(r.toString().contains("water")){
                    duration = 2.0f; // 90f
                } else if(r.toString().equalsIgnoreCase("bouncing_field")){
                    duration = .2f;
                }
                
                Animation anim = new Animation(duration, frames.toArray(animFrames));
                animations.put(animFrames[0].toString(), anim);
            } else {
                TextureRegion rg = frames.get(0);
                textures.put(rg.toString(), new Sprite(rg));
            }
        }

        defaultFont = manager.get(Presets.TEXTURE_UI + DEFAULT_FONT);
        chatFont = manager.get(Presets.TEXTURE_UI + CHAT_FONT);

        SIDEBAR_WIDTH = textures.get("sidebar").getWidth();
        SIDEBAR_HEIGHT = textures.get("sidebar").getHeight();

        soundManager = new SFXS();
        soundManager.addSound("tankIdle", "idle.ogg");
        soundManager.addSound("tankMoving", "moving.ogg");
        soundManager.addSound("tankDead", "explosion.ogg");
        soundManager.addSound("bulletShoot", "shoot.ogg");
        soundManager.addSound("enemyHit", "enemy_hit.ogg");
        soundManager.addSound("wallHit", "wall_hit.ogg");
        soundManager.addSound("bulletDead", "bullet_dead.ogg");
        soundManager.addSound("eagleDead", "eagle_down.ogg");
        soundManager.addSound("powerup", "power_up.ogg");
        soundManager.addSound("powerShow", "powerup_show.ogg");
        soundManager.addSound("stageStart", "stage_start.ogg");
        soundManager.addSound("gameOver", "gameover.ogg");
        soundManager.addSound("pause", "pause.ogg");
        soundManager.addSound("unPause", "unpause.ogg");
        soundManager.addSound("playerLife", "life.ogg");
        soundManager.addSound("bounceField", "sfx3.ogg");

        postLoading();
    }

    public static AssetsGame manager() {
        if (instance == null) {
            instance = new AssetsGame();
        }
        return instance;
    }

    @Override
    protected void postLoading() {
        animations.get("explosion").setPlayMode(Animation.PlayMode.NORMAL);
        animations.get("respawn").setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        animations.get("bullet_hit").setPlayMode(Animation.PlayMode.NORMAL);
        Sprite gameOverSprite = textures.get("game_over");
        gameOverSprite.setPosition(Presets.WORLD_WIDTH * .5f - (gameOverSprite.getWidth() * .5f), -1);
        chatFont.getData().setScale(0.20f);
    }

    @Override
    public void dispose() {
        instance = null;
        textures.clear();
        animations.clear();
        manager.dispose();
        soundManager.dispose();
    }
}
