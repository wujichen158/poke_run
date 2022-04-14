package com.qdu.pokerun.actor.trade;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.Array;
import com.qdu.pokerun.actor.PokemonBarElement;
import com.qdu.pokerun.entity.Pokemon;
import com.qdu.pokerun.util.PokemonUtil;

public class PokemonButtonGroup extends HorizontalGroup {
    //该玩家持有的宝可梦个数
    private int pokemonCnt;
    public PokemonButtonGroup(Array<Pokemon> pokemonList, com.qdu.pokerun.screen.TradeScreen mscreen){
        pokemonCnt = 0;

        //使用scene2D中的小部件组进行左侧宝可梦UI的描绘
        for (Pokemon p : pokemonList) {
            PokemonBarElement elem = new PokemonBarElement(p);
//            elem.setX(0);
//            elem.setY(i * (LibPokemonDetails.POKEBALL_UI_H + gapY));
            pokemonCnt++;
            addActor(elem);
            elem.addListener(new InputListener(){
                int cnt = pokemonCnt;
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    mscreen.setSelectPokemon(cnt);
                    return super.touchDown(event, x, y, pointer, button);
                }
            });
        }
        for(int i=pokemonCnt; i<6; i++){
            PokemonBarElement none = new PokemonBarElement(0, -1, false);
            addActor(none);
        }
        left();
        space(10);
    }

    public void refresh(Array<Pokemon> pokemonList, com.qdu.pokerun.screen.TradeScreen mscreen){
        clear();
        pokemonCnt = 0;

        //使用scene2D中的小部件组进行左侧宝可梦UI的描绘
        for (Pokemon p : pokemonList) {
            PokemonBarElement elem = new PokemonBarElement(p);
//            elem.setX(0);
//            elem.setY(i * (LibPokemonDetails.POKEBALL_UI_H + gapY));
            pokemonCnt++;
            addActor(elem);
            elem.addListener(new InputListener(){
                int cnt = pokemonCnt;
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    mscreen.setSelectPokemon(cnt);
                    return super.touchDown(event, x, y, pointer, button);
                }
            });
        }
        for(int i=pokemonCnt; i<6; i++){
            PokemonBarElement none = new PokemonBarElement(0, -1, false);
            addActor(none);
        }
        left();
        space(10);
    }
}
