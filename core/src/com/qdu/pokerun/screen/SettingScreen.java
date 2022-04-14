package com.qdu.pokerun.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.I18NBundle;
import com.qdu.pokerun.PokeRun;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SettingScreen extends MenuScreen {

    private Table settingsTable;
    //使用一个map存储语言名称和其对应的地区
    private ArrayMap<String, Locale> langMap;

    private Label settingLabel;
    private Label musicLabel;
    private Label effectLabel;
    private Label languageLabel;

    final SelectBox<String> languageSelect;

    public SettingScreen(Screen previousScreen) {
        super(previousScreen);

        settingsTable = new Table(skin);
//        settingsTable.debug();
        settingsTable.columnDefaults(0).padRight(40).left();
        settingsTable.columnDefaults(1).growX().fillY();

        settingsTable.setFillParent(true);
        settingsTable.pad(30).padTop(96);
//        this.settingLabel=new Label(PokeRun.rb_default.format("label.settings"), skin);
//        this.settingLabel.setColor(Color.BLACK);
//        settingsTable.add(this.settingLabel).padBottom(30).growX().expandY().top().colspan(3).row();

        this.musicLabel=new Label(PokeRun.rb_default.format("slider.music"), skin);
        this.musicLabel.setColor(Color.BLACK);
        settingsTable.add(this.musicLabel).padBottom(10);
        final Slider musicSlider = new Slider(0, 100, 1, false, skin);
        musicSlider.setVisualPercent(PokeRun.prefs.getMusicVolume());
        final Label musicPercent = new Label(((int) musicSlider.getValue()) + "%", skin);
        musicPercent.setColor(Color.BLACK);
        musicSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                PokeRun.prefs.setMusicVolume(musicSlider.getVisualPercent());
                musicPercent.setText((int) musicSlider.getValue() + "%");
                PokeRun.getInstance().titleMusic.setVolume(musicSlider.getVisualPercent());
            }
        });
        settingsTable.add(musicSlider).padBottom(10).padRight(20).left();
        settingsTable.add(musicPercent).padBottom(10).minWidth(50).row();

        this.effectLabel=new Label(PokeRun.rb_default.format("slider.affect"), skin);
        this.effectLabel.setColor(Color.BLACK);
        settingsTable.add(this.effectLabel).padBottom(10);
        final Slider affectsSlider = new Slider(0, 100, 1, false, skin);
        affectsSlider.setVisualPercent(PokeRun.prefs.getEffectsVolume());
        final Label affectPercent = new Label(((int) affectsSlider.getValue()) + "%", skin);
        affectPercent.setColor(Color.BLACK);
        affectsSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                PokeRun.prefs.setEffectsVolume(affectsSlider.getVisualPercent());
                affectPercent.setText((int) affectsSlider.getValue() + "%");
            }
        });
        settingsTable.add(affectsSlider).padBottom(10).padRight(20).left();
        settingsTable.add(affectPercent).padBottom(10).minWidth(50).row();

        initLangMap();
        this.languageLabel=new Label(PokeRun.rb_default.format("label.language"), skin);
        this.languageLabel.setColor(Color.BLACK);
        settingsTable.add(this.languageLabel).padBottom(10);
        languageSelect = new SelectBox<>(skin);
        languageSelect.setAlignment(Align.right);
        languageSelect.getList().setAlignment(Align.right);
        languageSelect.getStyle().listStyle.selection.setRightWidth(10);
        languageSelect.getStyle().listStyle.selection.setLeftWidth(20);
        languageSelect.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                PokeRun.rb_default=I18NBundle.createBundle(Gdx.files.internal("i18n/ui"), langMap.get(languageSelect.getSelected()));
                updateLanguage();
            }
        });
        languageSelect.setItems(this.langMap.keys().toArray());
        languageSelect.setSelected(langMap.getKey(PokeRun.rb_default.getLocale(), false));
        settingsTable.add(languageSelect).padBottom(10).minWidth(50).row();

        settingsTable.add().padBottom(300).row();

        mainStage.addActor(settingsTable);
    }

    //刷新语言
    private void updateLanguage(){
//        this.settingLabel.setText(PokeRun.rb_default.format("label.settings"));
        this.musicLabel.setText(PokeRun.rb_default.format("slider.music"));
        this.effectLabel.setText(PokeRun.rb_default.format("slider.affect"));
        this.languageLabel.setText(PokeRun.rb_default.format("label.language"));
        this.backButton.setText(PokeRun.rb_default.format("button.back"));
    }

    @Override
    public void show() {
        super.show();
        //在每次打开该界面时刷新语言
        updateLanguage();
    }

    //初始化所有语言
    private void initLangMap(){
        this.langMap=new ArrayMap<>();
        this.langMap.put("Chinese", Locale.CHINA);
        this.langMap.put("English", Locale.US);
        this.langMap.put("Japanese", Locale.JAPAN);
    }

}
