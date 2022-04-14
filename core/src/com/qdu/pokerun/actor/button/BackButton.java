package com.qdu.pokerun.actor.button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.qdu.pokerun.PokeRun;
import com.qdu.pokerun.lib.LibMisc;
import com.qdu.pokerun.util.SoundUtil;

public class BackButton extends ImageTextButton {
    public BackButton(Skin skin, Screen previousScreen) {
        super("返  回", skin);
        getLabel().setSize(15,15);
        setSize(100,60);
        setPosition(32, LibMisc.SCREEN_H-getHeight()-20);

        addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SoundUtil.playNavigationSound();
                PokeRun.getInstance().setScreen(previousScreen);
            }
        });
    }
}
