package com.qdu.pokerun.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.qdu.pokerun.entity.Pokemon;
import com.qdu.pokerun.lib.LibPokemonDetails;
import com.qdu.pokerun.util.PokemonUtil;

/**
 * 左侧宝可梦栏的盒子类，用来装宝可梦图标和显示精灵球
 */
public class PokemonBarElement extends Container<PokemonBarSprite> {

    //宝可梦的捕获球种
    private final TextureRegion caughtBall;

    //被选中时的最外层圆圈贴图
    private final TextureRegion ballSelected;

    //返回其是否被选中，与子类共享isSelected成员
    public void setSelected(boolean isSelected) {
        this.getActor().setSelected(isSelected);
    }
    public boolean isSelected() {
        return this.getActor().isSelected();
    }

    public PokemonBarElement(Pokemon pokemon) {
        this(pokemon.getNdex(), pokemon.getCaughtBall(), pokemon.isShiny());
    }

    public PokemonBarElement(int ndex, int caughtBall, boolean isShiny) {
        super(new PokemonBarSprite(ndex, isShiny));
        this.caughtBall = new TextureRegion(new Texture(Gdx.files.internal("img/ui/pokeball/" + PokemonUtil.convertBallType(caughtBall) + ".png")));
        this.ballSelected = new TextureRegion(new Texture(Gdx.files.internal("img/ui/pokeball/selected_outline.png")));

        minWidth(LibPokemonDetails.POKEBALL_UI_W);
        minHeight(LibPokemonDetails.POKEBALL_UI_H);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        //画被选中时的圈
        //不先画圈的话球和精灵会被挡住
        if (this.isSelected()) {
            batch.draw(ballSelected, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        }

        //画精灵球背景
        batch.draw(caughtBall, this.getX(), this.getY(), this.getWidth(), this.getHeight());

        super.draw(batch, parentAlpha);

    }
}
