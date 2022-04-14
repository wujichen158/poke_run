package com.qdu.pokerun.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.qdu.pokerun.PokeRun;
import com.qdu.pokerun.actor.trade.AnimFun;
import com.qdu.pokerun.actor.trade.Animator;
import com.qdu.pokerun.actor.trade.DragingPokemon;
import com.qdu.pokerun.actor.trade.PokemonButtonGroup;
import com.qdu.pokerun.actor.button.SwitchButton;
import com.qdu.pokerun.actor.trade.TradeBackgroundSprite;
import com.qdu.pokerun.actor.trade.TradePokemon;
import com.qdu.pokerun.entity.Pokemon;
import com.qdu.pokerun.lib.LibMisc;
import com.qdu.pokerun.lib.LibWidgetDetails;
import com.qdu.pokerun.util.BackgroundUtil;
import com.qdu.pokerun.util.PokemonUtil;
import com.qdu.pokerun.util.SoundUtil;

public class TradeScreen extends MenuScreen {
    private Table mainTable;

    //背景
    private TradeBackgroundSprite bg;

    //按钮们
    private SwitchButton switchButton;

    //宝可梦按钮
    private PokemonButtonGroup pokemonButtonGroup;
    private ScrollPane scrollPane;

    //宝可梦列表
    private Array<Pokemon> pokemonList;

    //精灵球动画变量
    public int selectPokemon =0;
    private int selectCopy =0;
    private boolean isDrag = false;
    private short AnimStatus = 0;
    private int v =0;
    final static short G = 1;
    private int counter = 0;
    private Animator animator;
    private Animator animator2;
    private int dragStatus = 0;

    //动画开关变量
    private boolean fadeOut=false;
    private boolean isPutInPlay = false;
    private boolean isPutOutPlay = false;

    Texture walkSheet;
    SpriteBatch spriteBatch;

    //拖动的精灵球
    private DragingPokemon dragingPokemon;

    //召唤出来的宝可梦
    private TradePokemon tradePokemon;

    private ImageTextButton putIn;
    private ImageTextButton putout;

    public TradeScreen(Screen previousScreen){
        super(previousScreen, false);

        this.bg = new TradeBackgroundSprite();
        this.mainStage.addActor(this.bg);

        this.mainTable = new Table();
        this.mainTable.setSize(LibMisc.SCREEN_W, LibMisc.SCREEN_H);
        this.mainStage.addActor(this.mainTable);
        this.mainTable.setFillParent(true);

        createBackButton();
        this.switchButton = new SwitchButton(this.skin,this.bg);
        this.mainStage.addActor(this.switchButton);

        this.pokemonList = PokemonUtil.generateRandomPokemon(8);
        this.pokemonButtonGroup = new PokemonButtonGroup(pokemonList,this);
        this.scrollPane = new ScrollPane(pokemonButtonGroup);
        this.scrollPane.setSize(LibMisc.SCREEN_W,100);
        this.scrollPane.setPosition(0,50);
        this.mainStage.addActor(scrollPane);

        this.dragingPokemon = new DragingPokemon();
        this.mainStage.addActor(dragingPokemon);

        this.animator = new Animator(5,5,"img/anim/Revival1.png");
        this.animator2 = new Animator(5,5,"img/anim/StateDown1.png");
        this.animator.create();
        this.animator2.create();

        this.tradePokemon = new TradePokemon(pokemonList);
        this.mainStage.addActor(tradePokemon);

        this.putIn = new ImageTextButton(PokeRun.rb_default.format("button.put.in"),skin);
        this.putout = new ImageTextButton(PokeRun.rb_default.format("button.put.out"),skin);
        putIn.setPosition(LibMisc.SCREEN_W,450);
        putout.setPosition(LibMisc.SCREEN_W,360);
        putIn.setSize(100,60);
        putout.setSize(100,60);
        this.mainStage.addActor(putIn);
        this.mainStage.addActor(putout);
        this.putIn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SoundUtil.playNavigationSound();
                isPutInPlay=true;
            }
        });
        this.putout.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SoundUtil.playNavigationSound();
                isPutOutPlay=true;
                dragingPokemon.PokemonDisplay(1);
//                dragingPokemon.setPosition(tradePokemon.getX()-tradePokemon.getWidth(), LibMisc.SCREEN_H);
                dragingPokemon.setY(LibMisc.SCREEN_H);
            }
        });
    }

    public void setSelectPokemon(int input){
        this.selectPokemon=input;
    }

    @Override
    public void render(float delta) {
//        System.out.println("dragingPokemon: "+dragingPokemon.getX()+", "+dragingPokemon.getY());
        //精灵球开始拖动时执行一次
        if(this.dragStatus==0&&this.selectPokemon!=0){
            this.dragStatus = 1;
            this.dragingPokemon.PokemonDisplay(1);
            dragingPokemon.setPosition(
                    Gdx.input.getX() * BackgroundUtil.wRatio * BackgroundUtil.screenScale.getFirst() -dragingPokemon.getWidth()/2,
                    (LibMisc.SCREEN_H-Gdx.input.getY() * BackgroundUtil.hRatio) * BackgroundUtil.screenScale.getSecond() - dragingPokemon.getHeight()/2
            );
            this.fadeOut=true;
        }
        if(this.fadeOut){
            if(AnimFun.moveYOut(scrollPane,6))
                this.fadeOut = false;
        }
        if(this.isPutInPlay){
            boolean a=AnimFun.moveX(tradePokemon, LibMisc.SCREEN_H+tradePokemon.getWidth()+150,6);
            boolean b=AnimFun.moveXOut(putIn,5);
            boolean c=AnimFun.moveXOut(putout,5);
            boolean d=AnimFun.moveY(scrollPane,50,6);
            if(a&&b&&c&&d) {
                isPutInPlay=false;
                this.dragStatus=0;
                this.pokemonList.removeIndex(this.selectCopy-1);
                this.pokemonButtonGroup.refresh(pokemonList,this);
                this.tradePokemon.removePokemon(this.selectCopy-1);
            }
        }
        if(this.isPutOutPlay){
            boolean a=AnimFun.moveY(dragingPokemon,50,20);
            boolean b=AnimFun.moveXOut(putIn,5);
            boolean c=AnimFun.moveXOut(putout,5);
            if(a&&b&&c) {
                isPutOutPlay=false;
                this.dragStatus=0;
                this.AnimStatus=5;
                this.animator2.setPos((dragingPokemon.getX() + dragingPokemon.getWidth()/2 - LibWidgetDetails.ANIMATOR_W / 2f) / BackgroundUtil.wRatio, dragingPokemon.getY()-LibWidgetDetails.ANIMATOR_H / 16f / BackgroundUtil.hRatio);
            }
        }
        if(AnimStatus == 6){
            if(AnimFun.moveY(scrollPane,50,6)){
                AnimStatus = 0;
                dragStatus = 0;
            }
        }

        //精灵球拖动中
        if(Gdx.input.isTouched()&&this.dragStatus==1){
            dragingPokemon.setPosition(
                    Gdx.input.getX() * BackgroundUtil.wRatio * BackgroundUtil.screenScale.getFirst()-dragingPokemon.getWidth()/2,
                    (LibMisc.SCREEN_H-Gdx.input.getY()*BackgroundUtil.hRatio)*BackgroundUtil.screenScale.getSecond()-dragingPokemon.getHeight()/2
            );
        }
        //精灵球停止拖动时执行一次
        if(!Gdx.input.isTouched()&&this.dragStatus==1){
            this.AnimStatus = 1;
            this.dragStatus = 2;
        }
        if(this.AnimStatus == 1){
            if(dragingPokemon.getY()>100){
                counter+=1;
                if (counter==2){
                    this.v+=this.G;
                    counter=0;
                }
                dragingPokemon.setY(dragingPokemon.getY()-this.v);

            }else{
                //计算位置。x坐标计算方法如同“串”字
                this.animator.setPos((dragingPokemon.getX() + dragingPokemon.getWidth()/2 - LibWidgetDetails.ANIMATOR_W / 2f) / BackgroundUtil.wRatio, dragingPokemon.getY()-LibWidgetDetails.ANIMATOR_H / 16f / BackgroundUtil.hRatio);
                tradePokemon.setPokemon(selectPokemon-1);
                this.tradePokemon.setPosition(
                        dragingPokemon.getX()+dragingPokemon.getWidth()/2-tradePokemon.getWidth()/2,
                        dragingPokemon.getY()+this.tradePokemon.getHeight()+200
                );
                this.dragingPokemon.PokemonDisplay(0);
                this.scrollPane.setVisible(true);
                this.AnimStatus = 2;
                this.v=0;
                this.counter = 0;
                this.selectCopy = this.selectPokemon;
                this.selectPokemon = 0;
            }
        }
        ScreenUtils.clear(Color.CHARTREUSE);
        this.mainStage.act(Gdx.graphics.getDeltaTime());
        this.mainStage.draw();
        if (this.AnimStatus == 2){
            if(!this.animator.isAnimationFinished())
                animator.render();
            else{
                animator.rePlay();
                this.AnimStatus = 3;
            }
        }
        if(this.AnimStatus == 3){
            boolean a=AnimFun.moveX(putIn,LibMisc.SCREEN_W-130,6);
            boolean b=AnimFun.moveX(putout,LibMisc.SCREEN_W-130,6);
            if(a&&b)
                this.AnimStatus =0;
        }
        if(AnimStatus==5){
            if(!this.animator2.isAnimationFinished())
                animator2.render();
            else{
                animator2.rePlay();
                this.AnimStatus = 6;
                this.tradePokemon.setX(LibMisc.SCREEN_W+200);
                this.dragingPokemon.PokemonDisplay(0);
            }
        }
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        walkSheet.dispose();
        super.dispose();
    }
}
