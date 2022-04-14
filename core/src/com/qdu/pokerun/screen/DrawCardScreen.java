package com.qdu.pokerun.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.qdu.pokerun.actor.trade.AnimFun;
import com.qdu.pokerun.actor.trade.Animator;
import com.qdu.pokerun.actor.button.BackButton;
import com.qdu.pokerun.actor.trade.DragingPokemon;
import com.qdu.pokerun.actor.trade.TradePokemon;
import com.qdu.pokerun.entity.Pokemon;
import com.qdu.pokerun.lib.LibMisc;
import com.qdu.pokerun.lib.LibWidgetDetails;
import com.qdu.pokerun.util.BackgroundUtil;
import com.qdu.pokerun.util.PokemonUtil;
import com.qdu.pokerun.util.SoundUtil;
import com.qdu.pokerun.util.viewport.FitScreenViewport;

import java.awt.Menu;

public class DrawCardScreen extends MenuScreen {
    private Image poster;
    ImageTextButton one, ten, con;
    DragingPokemon dragingPokemon;
    private int animStatus = 0;
    private boolean isOne = false;
    private boolean isTen = false;
    private boolean isCon = false;
    private Animator animator;
    private Pokemon rollPokemon;
    private TradePokemon tradePokemon;
    private boolean isDataInit = false;
    private Image bg;

    public DrawCardScreen(Screen previousScreen) {
        super(previousScreen, false);

        Image gashapon = new Image(new Texture(Gdx.files.internal("img/bg/Gashapon.png")));
        Image poster = new Image(new Texture(Gdx.files.internal("img/bg/poster.png")));
//        gashapon.setSize(Gdx.graphics.getWidth()-50,Gdx.graphics.getHeight()-50);
//        poster.setSize(Gdx.graphics.getWidth()-115,240);
        gashapon.setSize(LibMisc.SCREEN_W - 50, LibMisc.SCREEN_H - 50);
        poster.setSize(LibMisc.SCREEN_W - 115, 240);
        gashapon.setX(25);
        poster.setPosition(45, 415);
        this.mainStage.addActor(gashapon);
        this.mainStage.addActor(poster);

        this.one = new ImageTextButton("抽一次", skin);
        this.ten = new ImageTextButton("抽十次", skin);
        this.con = new ImageTextButton("继 续", skin);
        this.bg = new Image(new Texture(Gdx.files.internal("img/ui/white.png")));
        one.setPosition(LibMisc.SCREEN_W - 200, 300);
        ten.setPosition(LibMisc.SCREEN_W - 200, 230);
        con.setPosition(LibMisc.SCREEN_W - 200, 300);
        bg.setPosition(0, 280);
        bg.setColor(Color.WHITE);
        one.setSize(100, 60);
        ten.setSize(100, 60);
        con.setSize(100, 60);
        bg.setSize(LibMisc.SCREEN_W, 0);

        one.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SoundUtil.playNavigationSound();
                isOne = true;
                SoundUtil.ensureMusicPlaying();
                rollPokemon = PokemonUtil.generateRandomPokemon(1).get(0);
                dragingPokemon.setVisible(true);
            }
        });
        ten.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SoundUtil.playNavigationSound();
                isTen = true;
                SoundUtil.ensureMusicPlaying();
            }
        });
        con.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SoundUtil.playNavigationSound();
                isCon = true;
                SoundUtil.ensureMusicPlaying();
                animStatus = 0;
            }
        });
        con.setVisible(false);
        this.mainStage.addActor(one);
        this.mainStage.addActor(ten);
        this.mainStage.addActor(bg);
        this.mainStage.addActor(con);

        this.dragingPokemon = new DragingPokemon();
        this.mainStage.addActor(dragingPokemon);
        dragingPokemon.setVisible(false);

        animator = new Animator(5, 5, "img/anim/Cure4.png");
        this.animator.create();

        createBackButton();
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(this.mainStage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);
        this.mainStage.act(Gdx.graphics.getDeltaTime());
        this.mainStage.draw();

        if (isOne) {
            if (!isDataInit) {
                tradePokemon = new TradePokemon(rollPokemon);
                tradePokemon.setPosition((float) LibMisc.SCREEN_W / 2f - 30, 500);
                tradePokemon.getColor().a = 0;
                this.mainStage.addActor(tradePokemon);
                isDataInit = true;
            }
            if (animStatus == 0 && !isCon) {
                boolean a = AnimFun.move(dragingPokemon, (float) LibMisc.SCREEN_W / 2f - (float) LibWidgetDetails.DRAW_POKEBALL_W / 2f - 20, 400, 3, 6);
                boolean b = AnimFun.setSize(dragingPokemon.getActor(), LibWidgetDetails.DRAW_POKEBALL_W, LibWidgetDetails.DRAW_POKEBALL_H, 5, 5);
                if (a && b) {
//                    this.animator.setPos(dragingPokemon.getX()-dragingPokemon.getWidth()-60,dragingPokemon.getY()-120);
                    this.animator.setPos((dragingPokemon.getX() + dragingPokemon.getWidth() / 2 - LibWidgetDetails.ANIMATOR_W / 2f) / BackgroundUtil.wRatio, dragingPokemon.getY() - 120);
                    animStatus = 1;
                }
            }
            if (animStatus == 1) {
                AnimFun.fadeOut(tradePokemon, 1);
                AnimFun.fadeOut(dragingPokemon, 0);
                AnimFun.setHeight(bg, 400, 5);
                if (!this.animator.isAnimationFinished())
                    animator.render();
                else {
                    this.dragingPokemon.setVisible(false);
                    animator.rePlay();
                    this.animStatus = 2;
                    this.con.setVisible(true);
                }
            }
            if (isCon) {
                boolean a = AnimFun.moveX(tradePokemon, LibMisc.SCREEN_W + tradePokemon.getWidth() + 150, 6);
                boolean b = AnimFun.setHeight(bg, 0, 5);
                if (a && b) {
                    this.mainStage.getRoot().removeActor(tradePokemon);
                    this.dragingPokemon.setVisible(false);
                    this.dragingPokemon.getColor().a = 1;
                    this.dragingPokemon.getActor().setSize(100, 100);
                    this.dragingPokemon.setPosition(100, 100);
                    this.con.setVisible(false);
                    this.isCon = false;
                    this.isOne = false;
                    this.isDataInit = false;
                }


            }

        }


    }

    @Override
    public void resize(int width, int height) {

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
