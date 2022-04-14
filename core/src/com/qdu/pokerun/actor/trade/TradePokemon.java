package com.qdu.pokerun.actor.trade;

import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.utils.Array;
import com.qdu.pokerun.actor.PokemonMainSprite;
import com.qdu.pokerun.entity.Pokemon;
import com.qdu.pokerun.lib.LibPokemonDetails;

public class TradePokemon extends Container<PokemonMainSprite> {
    Array<PokemonMainSprite> spriteArray;
    public TradePokemon(Array<Pokemon> pokemonList){
        super();
        spriteArray = new Array<>();
        for (Pokemon p: pokemonList){
            PokemonMainSprite tmp = new PokemonMainSprite(p.getNdex(),p.isShiny());
            spriteArray.add(tmp);
        }
        minWidth(LibPokemonDetails.POKEMON_MAIN_W);
        minHeight(LibPokemonDetails.POKEMON_MAIN_H);
    }

    public TradePokemon(Pokemon pokemon){
        super();
        PokemonMainSprite tmp = new PokemonMainSprite(pokemon.getNdex(),pokemon.isShiny());
        minWidth(LibPokemonDetails.POKEMON_MAIN_W);
        minHeight(LibPokemonDetails.POKEMON_MAIN_H);
        setActor(tmp);
    }

    public void setPokemon(int index){
        setActor(spriteArray.get(index));
    }

    public void removePokemon(int index){
        spriteArray.removeIndex(index);
    }

}
