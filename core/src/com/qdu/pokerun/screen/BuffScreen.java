package com.qdu.pokerun.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.qdu.pokerun.PokeRun;
import com.qdu.pokerun.actor.item.BuffItemList;
import com.qdu.pokerun.entity.Item;
import com.qdu.pokerun.util.SoundUtil;

public class BuffScreen extends MenuScreen {

    private Table mainTable;
//    private Table buffTable;

//    private Table operationTable;

    private Label itemTitleLabel;
    private Label descriptionLabel;
    private ImageTextButton useButton;
    private Item[] buffItemList;

    private int rowNum;
    private int colNum;

    private String itemTitleKey;
    private String descriptionKey;

    public BuffScreen(Screen previousScreen) {
        super(previousScreen);

        this.mainTable = new Table();
        this.mainTable.setFillParent(true);
//        this.mainTable.debug();

//        this.mainTable.row();
//        this.buffTable=new Table();
//        this.buffTable.debug();

        buffItemList = BuffItemList.getBuffItemList();

        this.rowNum = 4;
        this.colNum = 4;

        this.itemTitleKey = "";
        this.descriptionKey = "";

        for (int i = 0; i < this.rowNum; i++) {
            this.mainTable.row().padTop(16).padBottom(16);
            for (int j = 0; j < this.colNum; j++) {
                ImageButton imgButton;
                int index = j + i * colNum;
                if (index < buffItemList.length) {
                    imgButton = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("img/icon/buff/" + buffItemList[index].getItemID() + ".png"))));
                    imgButton.addListener(new ChangeListener() {
                        @Override
                        public void changed(ChangeEvent event, Actor actor) {
                            SoundUtil.playToolClickSound();
                            itemTitleKey = "buff." + buffItemList[index].getItemID() + ".name";
                            descriptionKey = "buff." + buffItemList[index].getItemID() + ".description";
                            itemTitleLabel.setText(PokeRun.rb_default.format(itemTitleKey));
                            descriptionLabel.setText(PokeRun.rb_default.format(descriptionKey));
                            useButton.setDisabled(false);
                        }
                    });
                } else {
                    imgButton = new ImageButton(skin);
                }
                this.mainTable.add(imgButton).size(64, 64);
            }
        }

        this.mainTable.row().colspan(4);
        this.itemTitleLabel = new Label("", skin);
        this.itemTitleLabel.setColor(Color.BLACK);
        this.itemTitleLabel.setAlignment(Align.center);
        this.mainTable.add(this.itemTitleLabel).size(384, 32);

        this.mainTable.row().colspan(4);
        this.descriptionLabel = new Label("", skin);
        this.descriptionLabel.setColor(Color.BLACK);
        this.descriptionLabel.setWrap(true);
        this.mainTable.add(this.descriptionLabel).size(384, 128);

        this.mainTable.row().colspan(4);
        this.useButton = new ImageTextButton(PokeRun.rb_default.format("button.usebuff"), skin);
        this.useButton.setDisabled(true);
        this.mainTable.add(this.useButton).size(128, 64);

//        this.mainTable.addActor(this.operationTable);
        this.mainStage.addActor(this.mainTable);
    }

    @Override
    public void show() {
        super.show();
        if (!this.itemTitleKey.equals("") && !this.descriptionKey.equals("")) {
            this.itemTitleLabel.setText(PokeRun.rb_default.format(this.itemTitleKey));
            this.descriptionLabel.setText(PokeRun.rb_default.format(this.descriptionKey));
        }
        this.useButton.setText(PokeRun.rb_default.format("button.usebuff"));
    }
}
