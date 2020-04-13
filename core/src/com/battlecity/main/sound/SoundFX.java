package com.battlecity.main.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.battlecity.main.global.*;

/**
 *
 * @author Pablis
 */
public class SoundFX {

    private final Sound sfx;
    private long id = -1;
    private boolean isPlaying = false;
    private boolean isLooping = false;

    public SoundFX(Sound sound) {
        sfx = sound;
    }

    public SoundFX(String filename) {
        sfx = Gdx.audio.newSound(Gdx.files.internal(Presets.SOUNDS + filename));
    }

    /**
     *
     * @return this sound for chaining.
     */
    public SoundFX play() {
        return this.play(false, Parameters.volume);
    }

    /**
     *
     * @param loop
     *
     * @return
     */
    public SoundFX play(boolean loop) {
        return this.play(loop, Parameters.volume);
    }

    /**
     *
     * @param loop
     * @param volume
     *
     * @return this sound for chaining.
     */
    public SoundFX play(boolean loop, float volume) {
        if (isPlaying) {
            if (!loop) {
                isPlaying = false;
                isLooping = false;
            } else {
                return this;
            }
        }

        if (loop) {
            id = sfx.loop(volume);
            isLooping = true;
        } else {
            id = sfx.play(volume);
        }
        SFXS.lastPlayerID = id;
        isPlaying = true;
        return this;
    }

    /**
     *
     * @return this sound for chaining.
     */
    public SoundFX stop() {
        if (isPlaying) {
            sfx.stop();
            isPlaying = false;
            isLooping = false;
        }
        return this;
    }

    /**
     * Stops the last sound played.
     *
     * @return this sound for chaining.
     */
    public SoundFX stopCurrentlyPlaying() {
        sfx.stop(SFXS.lastPlayerID);
        return this;
    }

    public boolean isPlaying() {
        return isPlaying || isLooping;
    }

    public boolean isLooping() {
        return isLooping;
    }

    public void dispose() {
        sfx.dispose();
    }

    public void setVolume(float volume) {
        sfx.setVolume(id, volume);
    }
}
