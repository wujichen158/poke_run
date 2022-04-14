package com.qdu.pokerun.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.qdu.pokerun.entity.Pokemon;
import com.qdu.pokerun.lib.LibPokemonDetails;
import com.qdu.pokerun.util.PokemonUtil;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

/**
 * 中间宝可梦大图的箱子，用来装宝可梦图标和显示场地
 */
public class PokemonMain extends Container<PokemonMainSprite> {

    //背景地形的贴图
    private TextureRegion bgTerrain;

    public PokemonMain(Pokemon pokemon, String bgName) {
        this(pokemon.getNdex(), pokemon.isShiny(), bgName);
    }

    public PokemonMain(int ndex, boolean isShiny, String bgName) {
        super(new PokemonMainSprite(ndex, isShiny));
        this.bgTerrain=new TextureRegion(new Texture(Gdx.files.internal("img/ui/terrain/terrain_" + bgName + ".png")));

        minWidth(LibPokemonDetails.POKEMON_MAIN_W);
        minHeight(LibPokemonDetails.POKEMON_MAIN_H);
//        debug();
    }

    public void setBgTerrain(TextureRegion bgTerrain) {
        this.bgTerrain=bgTerrain;
    }

    public TextureRegion getBgTerrain(){
        return this.bgTerrain;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        validate();

        //画背景场地
        batch.draw(bgTerrain,
                this.getX(), this.getY()-16,
                LibPokemonDetails.POKEMON_MAIN_W, LibPokemonDetails.POKEMON_MAIN_H);

        super.draw(batch, parentAlpha);
    }
}
