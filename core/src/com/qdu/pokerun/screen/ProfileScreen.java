package com.qdu.pokerun.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.qdu.pokerun.PokeRun;
import com.qdu.pokerun.actor.profile.Avatar;
import com.qdu.pokerun.lib.LibWidgetDetails;
import com.qdu.pokerun.util.AvatarUtil;
import com.qdu.pokerun.util.SoundUtil;

public class ProfileScreen extends MenuScreen {

    private Table mainTable;

    private Avatar avatarImg;

    private Label playernameContent;
    private Label emailContent;
    private Label stepContent;

    private ImageTextButton logoutButton;

    private Image icon;

    public ProfileScreen(Screen previousScreen) {
        super(previousScreen);

        this.mainTable = new Table();
        this.mainTable.setFillParent(true);

        this.mainStage.addActor(this.mainTable);

        this.avatarImg = new Avatar();
        this.mainTable.row();
        this.mainTable.add(this.avatarImg).maxSize(LibWidgetDetails.AVATAR_W, LibWidgetDetails.AVATAR_H);

        this.mainTable.row();
        this.playernameContent = new Label(PokeRun.player.getPlayerName(), skin);
        this.playernameContent.setColor(Color.BLACK);
        this.mainTable.add(this.playernameContent);

        this.mainTable.row();
        this.emailContent = new Label(PokeRun.player.getEmail(), skin);
        this.emailContent.setColor(Color.BLACK);
        this.mainTable.add(this.emailContent);

        this.mainTable.row().padTop(64).padBottom(64);
        this.stepContent = new Label(PokeRun.rb_default.format("label.step.description", PokeRun.getInstance().getGlobalStepCounter().getSteps()), skin);
        this.stepContent.setColor(Color.BLACK);
        this.mainTable.add(this.stepContent);

        this.mainTable.row();
        this.logoutButton = new ImageTextButton(PokeRun.rb_default.format("button.logout"), skin);
        this.logoutButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SoundUtil.playNavigationSound();
                PokeRun.getInstance().setScreen(LoginScreen.getInstance());
                SoundUtil.ensureMusicPlaying();
            }
        });

        this.mainTable.add(this.logoutButton).size(96, 64);
    }

    private void updateProfile() {
        this.avatarImg.setDrawable(new TextureRegionDrawable(new Texture(AvatarUtil.roundPixmap(new Pixmap(Gdx.files.internal("img/avatar/"+ PokeRun.player.getPlayerName() +".png"))))));
        this.playernameContent.setText(PokeRun.player.getPlayerName());
        this.emailContent.setText(PokeRun.player.getEmail());
    }

    @Override
    public void show() {
        super.show();
        this.stepContent.setText(PokeRun.rb_default.format("label.step.description", PokeRun.getInstance().getGlobalStepCounter().getSteps()));
        this.logoutButton.setText(PokeRun.rb_default.format("button.logout"));
        updateProfile();
    }
}
