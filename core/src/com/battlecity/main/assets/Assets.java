package com.battlecity.main.assets;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;

/**
 *
 * @author Pablis
 */
public abstract class Assets {

    public abstract void dispose();

//    public abstract boolean isLoading();

    protected abstract void postLoading();

    public static Animation createAnimation(TextureAtlas atlas,
            String regionName) {
        return createAnimation(atlas, regionName, 0.1f,
                Animation.PlayMode.NORMAL);
    }

    public static Animation createAnimation(TextureAtlas atlas,
            String regionName, float frameDuration) {
        return createAnimation(atlas, regionName, frameDuration,
                Animation.PlayMode.NORMAL);
    }

    public static Animation createAnimation(TextureAtlas atlas,
            String regionName, float frameDuration, Animation.PlayMode mode) {
        ArrayList<TextureRegion> frames = new ArrayList<>();

        int idx = 0;
        TextureAtlas.AtlasRegion region;

        while ((region = atlas.findRegion(regionName, idx)) != null) {
            frames.add(region);
            idx++;
        }

        TextureRegion[] regions = new TextureRegion[idx];
        Animation anim = new Animation(frameDuration, frames.toArray(regions));
        anim.setPlayMode(mode);        
        return anim;
    }
}
