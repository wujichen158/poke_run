package com.qdu.pokerun;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.I18NBundle;
import com.qdu.pokerun.entity.Player;
import com.qdu.pokerun.preference.PokeRunPreference;
import com.qdu.pokerun.screen.LoginScreen;
import com.qdu.pokerun.screen.MainScreen;

public class PokeRun extends Game {
    public SpriteBatch batch;

    private static PokeRun instance;

    public static PokeRunPreference prefs;

    public static Player player;

    public Music titleMusic;
    public Sound navigateSound;
    public Sound pokemonClickSound;
    public Sound toolClickSound;

    public static I18NBundle rb_default;

    /**
     * 初始化国际化文本
     */
    private void initI18NMessage() {
        FileHandle bfh = Gdx.files.internal("i18n/ui");
        rb_default = I18NBundle.createBundle(bfh);
    }

    public static PokeRun getInstance() {
        if (instance == null) {
            instance = new PokeRun();
        }
        return instance;
    }

    @Override
    public void create() {

        initI18NMessage();
        prefs = new PokeRunPreference();

//		player = new Player("muyoo", "1169083089@qq.com");

        titleMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/vs_marine.mp3"));
        titleMusic.setVolume(prefs.getMusicVolume());
        titleMusic.setLooping(true);
        titleMusic.play();

        navigateSound = Gdx.audio.newSound(Gdx.files.internal("sounds/btn_click.mp3"));
        pokemonClickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/pokemon_click.mp3"));
        toolClickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/expand_box.mp3"));

        instance = this;
        batch = new SpriteBatch();
        this.setScreen(new LoginScreen());
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        titleMusic.dispose();
        navigateSound.dispose();
    }
}
