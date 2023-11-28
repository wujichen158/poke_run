package com.qdu.pokerun.actor.tool.element;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.qdu.pokerun.EffectMgr;
import com.qdu.pokerun.actor.tool.FunctionalTool;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import com.qdu.pokerun.util.Pair;
public abstract class DragToolElement extends FunctionalTool {
    private Texture dragTexture;
    protected float dragX;
    protected float dragY;
    protected boolean dragged;

    public DragToolElement(String toolName, String toolImgPath, String emitName, float emitVal) {
        super(toolName);
        super.toolName=toolName;
        this.dragTexture = new Texture(Gdx.files.internal(toolImgPath));
        this.addListener(new ElemDragListener(this));
        this.emitName = emitName;
        this.emitVal = emitVal;
    }

    public Optional<Function<UnaryOperator<Pair<Float, Float>>, Pair<Texture, Pair<Float, Float>>>> getDraggableTexture() {
        if (!dragged) {
            return Optional.empty();
        }
        return Optional.of(
                (projectionFunc) -> {
                    Pair<Float, Float> point = projectionFunc.apply(new Pair<>(dragX, dragY));
                    return new Pair<>(dragTexture,
                            new Pair<>(point.first - dragTexture.getHeight() / 2
                                    , point.second - dragTexture.getWidth() / 2));
                }
        );
    }

    public void runEffect() {
        EffectMgr.effectMgr.emit(emitName, emitVal);
    }
    protected String emitName;
    protected float emitVal;
}
