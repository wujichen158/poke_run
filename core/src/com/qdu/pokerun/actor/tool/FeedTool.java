package com.qdu.pokerun.actor.tool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.qdu.pokerun.lib.LibPokemonDetails;

import java.awt.Image;

public class FeedTool extends ImageButton {

    public FeedTool(){
        super(new TextureRegionDrawable(new Texture(Gdx.files.internal("img/icon/feed/poffin.png"))));

//        this.setSize(LibPokemonDetails.FEED_ITEM_W, LibPokemonDetails.FEED_ITEM_H);
    }
}
