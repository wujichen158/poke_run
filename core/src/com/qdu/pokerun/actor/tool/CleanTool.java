package com.qdu.pokerun.actor.tool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.qdu.pokerun.lib.LibPokemonDetails;

public class CleanTool extends ImageButton {

    public CleanTool(){
        super(new TextureRegionDrawable(new Texture(Gdx.files.internal("img/icon/clean/blower_icon.png"))));

//        this.setSize(LibPokemonDetails.CLEAN_ITEM_W, LibPokemonDetails.CLEAN_ITEM_H);
    }
}
