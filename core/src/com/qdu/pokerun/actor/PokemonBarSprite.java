package com.qdu.pokerun.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.qdu.pokerun.util.PokemonUtil;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

/**
 * 左侧宝可梦栏的宝可梦图标类
 */
public class PokemonBarSprite extends Image {

    //左侧宝可梦图标跳跃的高度（y轴修正）
    private final short yFix = 4;

    //向上和向下跳跃的动作
    private final MoveByAction moveUpAction;
    private final MoveByAction moveDownAction;

    //是否被选中
    private boolean isSelected;
    public void setSelected(boolean isSelected){
        this.isSelected=isSelected;
    }
    public boolean isSelected(){
        return this.isSelected;
    }

    //每次上下跳跃的时间间隔
    private final float jumpDelay = 0.15f;

    public PokemonBarSprite(int ndex, boolean isShiny){
        //绑定宝可梦图标，需对图鉴编号和异色进行处理
        super(new Texture(Gdx.files.internal("img/sprite/"+(isShiny?"shiny":"")+"pokemon/"+ PokemonUtil.processRawNDex(ndex)+".png")));

        //初始化跳跃动作
        this.moveUpAction=Actions.moveBy(0, yFix);
        this.moveDownAction=Actions.moveBy(0, -yFix);

//        debug();

        //添加跳跃动作
        addAction(forever(sequence(moveUpAction, Actions.delay(jumpDelay), moveDownAction, Actions.delay(jumpDelay))));

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        //根据是否选中修正y轴跳跃高度
        if (isSelected()){
            this.moveUpAction.setAmountY(2*yFix);
            this.moveDownAction.setAmountY(-2*yFix);
        } else {
            this.moveUpAction.setAmountY(yFix);
            this.moveDownAction.setAmountY(-yFix);
        }

    }
}
