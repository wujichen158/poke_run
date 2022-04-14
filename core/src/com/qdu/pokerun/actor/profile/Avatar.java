package com.qdu.pokerun.actor.profile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.qdu.pokerun.PokeRun;
import com.qdu.pokerun.util.AvatarUtil;

public class Avatar extends Image {

    public Avatar() {
        super(new Texture(AvatarUtil.roundPixmap(new Pixmap(Gdx.files.internal("img/avatar/fail_avatar.png")))));
    }

}
