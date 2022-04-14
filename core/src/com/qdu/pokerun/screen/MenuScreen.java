package com.qdu.pokerun.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.qdu.pokerun.PokeRun;
import com.qdu.pokerun.actor.button.BackButton;
import com.qdu.pokerun.lib.LibMisc;
import com.qdu.pokerun.util.viewport.FitScreenViewport;

public class MenuScreen implements Screen {

    private final Screen previousScreen;
    protected final Stage mainStage;

    protected BackButton backButton;

    //按钮样式
    Skin skin;

    public MenuScreen(Screen previousScreen) {
        this.skin = new Skin(Gdx.files.internal("style/uiskin.json"));
        this.mainStage = new Stage(new FitScreenViewport());
        this.previousScreen = previousScreen;
        createBackButton();
    }

    public MenuScreen(Screen previousScreen, boolean createBtn) {
        this.skin = new Skin(Gdx.files.internal("style/uiskin.json"));
        this.mainStage = new Stage(new FitScreenViewport());
        this.previousScreen = previousScreen;
        if (createBtn) {
            createBackButton();
        }
    }

    protected void createBackButton() {
        this.backButton = new BackButton(skin, previousScreen);
//        backButton.setBounds(20, mainStage.getViewport().getWorldHeight() - 120, 100, 100);
//        this.backButton.setPosition(32, LibMisc.SCREEN_H-70);
        mainStage.addActor(backButton);
    }

//    protected void showPreviousScreen() {
//        PokeRun.getInstance().setScreen(previousScreen);
//    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this.mainStage);
        this.backButton.setText(PokeRun.rb_default.format("button.back"));
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(230f / 255f, 230f / 255f, 230f / 255f, 1f);
        mainStage.act();
        mainStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        this.mainStage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        this.mainStage.dispose();
    }
}
