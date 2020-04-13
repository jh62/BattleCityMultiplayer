package com.battlecity.main.sound;

import com.badlogic.gdx.audio.Sound;
import com.battlecity.main.global.*;
import java.util.HashMap;

/**
 *
 * @author Pablis
 */
public final class SFXS {

    private final HashMap<String, SoundFX> SOUNDPOOL
            = new HashMap<>();

    protected static long lastPlayerID;

    public SFXS() {

    }

//    private static SoundFX lastPlayed = null;
    /**
     * Adds a new sound to the pool if absent.
     *
     * @param soundName
     * @param sound
     *
     * @return the sound effect added.
     */
    public SoundFX addSound(String soundName, Sound sound) {
        SoundFX sfx = new SoundFX(sound);
        SOUNDPOOL.putIfAbsent(soundName, sfx);
        return sfx;
    }

    public SoundFX addSound(String soundName, String filename) {
        SoundFX sfx = new SoundFX(filename);
        SOUNDPOOL.putIfAbsent(soundName, sfx);
        return sfx;
    }

    /**
     * Get a sound effect by its name.
     *
     * @param soundName
     *
     * @return
     */
    public SoundFX getSound(String soundName) {
        return SOUNDPOOL.get(soundName);
    }

    public void updateVolume() {
        for (SoundFX sfx : SOUNDPOOL.values()) {
            sfx.setVolume(Parameters.volume);
        }
    }

//    public void stopSounds() {
//        SoundFX.s
//    }
    /**
     * Disposes all the sounds in the pool. The sound pool will be empty after
     * this call.
     */
    public void dispose() {
        SOUNDPOOL.values().stream().forEach((sfx) -> {
            sfx.dispose();
        });
        SOUNDPOOL.clear();
    }

}
