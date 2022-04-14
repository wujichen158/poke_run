package com.qdu.pokerun.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.qdu.pokerun.util.PokemonUtil;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

/**
 * 中间宝可梦大图的宝可梦图标
 */
public class PokemonMainSprite extends Image {

    //前往目的地的动作和从目的地回来的动作
    private MoveByAction moveAction;
    private MoveByAction backAction;

    //前往目的地后的停留动作和返回起点后的停留动作
    private DelayAction toDelayAction;
    private DelayAction backDelayAction;

    //动作循环队列
    private SequenceAction sequenceAction;

    //目的地相对坐标
    private final float moveX, moveY;

    //停留动作的时间
    private final float stayDelay = 5f;

    public PokemonMainSprite(int ndex, boolean isShiny) {
        super(new Texture(Gdx.files.internal("img/sprite/"+(isShiny?"shiny":"")+"pokemon/"+ PokemonUtil.processRawNDex(ndex)+".png")));

        moveX=getPrefWidth()/4f;
        moveY=getPrefHeight()/2f;

        //随机添加20种移动方式
        for(int i=0; i<20; i++){
            sequenceAction = sequence();

            float moveXTmp=MathUtils.random(-moveX, moveX), moveYTmp=MathUtils.random(-moveY, moveY);
            this.moveAction=Actions.moveBy(moveXTmp, moveYTmp, 2f);
            this.backAction=Actions.moveBy(-moveXTmp, -moveYTmp, 2f);

            this.toDelayAction=Actions.delay(MathUtils.random(0f, stayDelay));
            this.backDelayAction=Actions.delay(MathUtils.random(0f, stayDelay));

            sequenceAction.addAction(moveAction);
            sequenceAction.addAction(toDelayAction);
            sequenceAction.addAction(backAction);
            sequenceAction.addAction(backDelayAction);
            addAction(forever(sequenceAction));
        }
    }
}
