package com.qdu.pokerun.actor.trade;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.qdu.pokerun.lib.LibMisc;
import com.qdu.pokerun.util.BackgroundUtil;

public class TradeBackgroundSprite extends Container<Image> {
    private Image trade, mc;
    boolean BgType;

    public TradeBackgroundSprite() {
        this.trade = new Image(new Texture(Gdx.files.internal("img/bg/tradeBg.jpg")));
        this.mc = new Image(new Texture(Gdx.files.internal("img/bg/mcBg.jpg")));
        setSize(Gdx.graphics.getWidth() * BackgroundUtil.screenRatio,
                Gdx.graphics.getHeight() * BackgroundUtil.screenRatio);
//        setScale(BackgroundUtil.screenScale.getFirst(), BackgroundUtil.screenScale.getSecond());
        setActor(this.trade);
        fill();
        this.BgType = true;
    }

    public void switchBackground() {
        setActor(this.BgType ? this.mc : this.trade);
        this.BgType = !this.BgType;
    }
}
