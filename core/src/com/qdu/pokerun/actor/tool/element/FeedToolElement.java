package com.qdu.pokerun.actor.tool.element;

import com.qdu.pokerun.actor.tool.FunctionalTool;
import com.qdu.pokerun.lib.LibPokemonDetails;
import com.qdu.pokerun.util.Pair;

public class FeedToolElement extends DragToolElement {
    public FeedToolElement(String toolName, String emitName, float val) {
        super("feed/"+toolName, "img/icon/"+"feed/"+toolName+".png", emitName, val);
    }
}
