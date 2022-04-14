package com.qdu.pokerun.actor.tool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class FunctionalTool extends ImageButton {
    protected String toolName;
    public String getToolName(){
        return this.toolName;
    }

    public FunctionalTool(String toolImgPath){
        super(new TextureRegionDrawable(new Texture(Gdx.files.internal("img/icon/"+toolImgPath+".png"))));
    }
}
