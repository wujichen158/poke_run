package com.qdu.pokerun.actor.button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.qdu.pokerun.actor.trade.TradeBackgroundSprite;
import com.qdu.pokerun.lib.LibMisc;

public class SwitchButton extends ImageTextButton {
    public SwitchButton(Skin skin , TradeBackgroundSprite bg) {
        super("切换", skin);
        getLabel().setSize(15,15);
        setSize(100,60);
        setPosition(LibMisc.SCREEN_W-getWidth()-30, LibMisc.SCREEN_H-getHeight()-20);

        addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                bg.switchBackground();
            }
        });
    }
}
