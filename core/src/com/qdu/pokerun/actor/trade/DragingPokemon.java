package com.qdu.pokerun.actor.trade;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.qdu.pokerun.actor.PokemonBarElement;
import com.qdu.pokerun.entity.Pokemon;

public class DragingPokemon extends Container<Image> {
    Array<PokemonBarElement> elemArr;
    Image ball;
    public DragingPokemon(){
        this.ball = new Image(new Texture(Gdx.files.internal("img/ui/pokeball/poke.png")));
        this.ball.setSize(100,100);
        setActor(this.ball);
        setSize(100,100);
        setPosition(100,100);
        setVisible(false);
    }

    public void PokemonDisplay(int id){
        setVisible(id > 0);
    }


}
