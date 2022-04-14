package com.qdu.pokerun.actor.tool.element;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.qdu.pokerun.lib.LibPokemonDetails;
import com.qdu.pokerun.util.Pair;

public class ElemDragListener extends DragListener {

    DragToolElement element;

    protected ElemDragListener(DragToolElement element) {
        super();
        this.element = element;
    }

    @Override
    public void drag(InputEvent event, float x, float y, int pointer) {
        element.dragged = true;
        element.dragX = Gdx.input.getX(pointer);
        element.dragY = Gdx.input.getY(pointer);
        //System.out.println(x + " " + y);
    }

    @Override
    public void dragStop(InputEvent event, float x, float y, int pointer) {
        element.dragged = false;
        element.runEffect();
    }
}
