package com.qdu.pokerun;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.I18NBundle;
import com.qdu.pokerun.api.EmptyStepCounter;
import com.qdu.pokerun.api.StepCounter;
import com.qdu.pokerun.entity.Player;
import com.qdu.pokerun.preference.PokeRunPreference;
import com.qdu.pokerun.screen.LoginScreen;
import com.qdu.pokerun.screen.MainScreen;

import java.util.Optional;

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

    private StepCounter globalStepCounter;

    /**
     * 初始化所有字段
     * @param stepCounter
     */
    public PokeRun(StepCounter stepCounter) {
        this();
        this.globalStepCounter = stepCounter;
    }

    public PokeRun() {
        super();
        instance = this;
    }

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

    public StepCounter getGlobalStepCounter() {
        return this.globalStepCounter;
    }

    /**
     * 创建游戏资源或设置游戏状态
     */
    @Override
    public void create() {
//		player = new Player("muyoo", "1169083089@qq.com");
        if (!Optional.ofNullable(globalStepCounter).isPresent()) {
            globalStepCounter = new EmptyStepCounter();
        }

        initI18NMessage();
        prefs = new PokeRunPreference();

        titleMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/vs_marine.mp3"));
        titleMusic.setVolume(prefs.getMusicVolume());
        titleMusic.setLooping(true);
        titleMusic.play();

        navigateSound = Gdx.audio.newSound(Gdx.files.internal("sounds/btn_click.mp3"));
        pokemonClickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/pokemon_click.mp3"));
        toolClickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/expand_box.mp3"));

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
