package com.qdu.pokerun.actor.tool.element;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.qdu.pokerun.EffectMgr;
import com.qdu.pokerun.actor.tool.FunctionalTool;
import com.qdu.pokerun.lib.LibPokemonDetails;
import com.qdu.pokerun.util.Pair;

import javax.swing.text.html.Option;
import java.util.Optional;

public class CleanToolElement extends DragToolElement {
    public CleanToolElement(String toolName, String emitName, float val) {
        super("clean/"+toolName+"_icon", "img/icon/"+"clean/"+toolName+"_icon"+".png", emitName, val);
    }

}
