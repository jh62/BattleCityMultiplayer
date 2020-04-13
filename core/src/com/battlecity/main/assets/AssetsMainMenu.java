package com.battlecity.main.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.battlecity.main.global.Presets;

/**
 *
 * @author Pablis
 */
public final class AssetsMainMenu extends Assets {

    private AssetManager manager;

    public final Texture mainMenuTexture;
    public final Sprite selectionIcon;
    public final Sound selectionSound, selectSound;
    public final BitmapFont defaultFont;

    private static final String DEFAULT_FONT = "emulogic.fnt";

    public AssetsMainMenu() {
        manager = new AssetManager();
        manager.load(Presets.TEXTURE_UI + "background.png", Texture.class);
        manager.load(Presets.TEXTURE_UI + "icon.png", Texture.class);     
        manager.load(Presets.SOUNDS + "wall_hit.ogg", Sound.class);
        manager.load(Presets.SOUNDS + "bullet_dead.ogg", Sound.class);
        manager.load(Presets.TEXTURE_UI + DEFAULT_FONT, BitmapFont.class);
        manager.finishLoading();

        mainMenuTexture = manager.get(Presets.TEXTURE_UI + "background.png");
        selectionIcon = new Sprite((Texture) manager.get(Presets.TEXTURE_UI
                + "icon.png"));     

        defaultFont = manager.get(Presets.TEXTURE_UI + DEFAULT_FONT);
        selectionSound = manager.get(Presets.SOUNDS + "bullet_dead.ogg");
        selectSound = manager.get(Presets.SOUNDS + "wall_hit.ogg");
        postLoading();
    }

    @Override
    protected void postLoading() {
        selectionIcon.setScale(1.1f);
    }

    @Override
    public void dispose() {
        manager.dispose();
        manager = null;
    }
}
