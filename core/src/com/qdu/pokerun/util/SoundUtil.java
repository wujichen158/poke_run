package com.qdu.pokerun.util;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.qdu.pokerun.PokeRun;

public class SoundUtil {

    public static void playSoundEffect(Sound sound) {
        playSoundEffect(sound, 1f);
    }

    public static void playSoundEffect(Sound sound, float volume) {
        sound.play(volume * PokeRun.prefs.getEffectsVolume());
    }

    public static void playNavigationSound() {
        playSoundEffect(PokeRun.getInstance().navigateSound);
    }

    public static void playPokemonClickSound(){
        playSoundEffect(PokeRun.getInstance().pokemonClickSound);
    }

    public static void playToolClickSound() {
        playSoundEffect(PokeRun.getInstance().toolClickSound);
    }

    /**
     * 保证bgm实时播放，在HTML端也可播放
     */
    public static void ensureMusicPlaying() {
        if (Gdx.app.getType() != Application.ApplicationType.WebGL || PokeRun.getInstance().titleMusic.isPlaying())
            return;
        PokeRun.getInstance().titleMusic.play();
    }
}
