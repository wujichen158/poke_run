package com.qdu.pokerun.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.qdu.pokerun.PokeRun;
import com.qdu.pokerun.entity.Player;
import com.qdu.pokerun.lib.LibMisc;
import com.qdu.pokerun.util.NetUtil;
import com.qdu.pokerun.util.SoundUtil;
import com.qdu.pokerun.util.viewport.FitScreenViewport;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginScreen implements Screen {

    private Stage mainStage;
    private Camera camera;

    private Table mainTable;
    private Table formTable;
    private Table buttonTable;

    private TextField playerNameTF;
    private TextField pwdTF;

    private CheckBox remmeCB;

    private ImageTextButton loginButton;
    private ImageTextButton registerButton;
    private ImageTextButton exitButton;

    private ImageButton settingButton;

    //整体样式
    Skin skin;
    private Image icon;

    private static LoginScreen instance;

    /**
     * 初始化按钮样式
     */
    private void initGlobalSkin() {
        //使用外部文件中的样式
        skin = new Skin(Gdx.files.internal("style/uiskin.json"));
    }

    public LoginScreen() {
        instance = this;

        initGlobalSkin();

    }

    public static LoginScreen getInstance() {
        if (instance == null) {
            instance = new LoginScreen();
        }
        return instance;
    }

    @Override
    public void show() {
        this.mainStage = new Stage(new FitScreenViewport());
        this.mainStage.getCamera().project(new Vector3(LibMisc.SCREEN_W, LibMisc.SCREEN_H, 0));
        Gdx.input.setInputProcessor(this.mainStage);

        Label tmp;
        this.icon = new Image(new Texture(Gdx.files.internal("img/icon/Login_icon.png")));
        this.icon.setPosition(80, -50);
        this.mainStage.addActor(icon);
        this.mainTable = new Table();
        this.mainTable.setFillParent(true);
        this.camera = this.mainStage.getCamera();

        this.mainStage.addActor(this.mainTable);

        this.mainTable.padLeft(32).padRight(32);
        this.mainTable.row().colspan(2);
        //this.mainTable.add(new Image(new Texture(Gdx.files.internal("img/ui/title/pokeball.png")))).maxSize(96, 96);
        this.mainTable.add(new Image(new Texture(Gdx.files.internal("img/ui/title/title.png")))).maxSize(96 * 3, 96);


        this.mainTable.row().colspan(2);
        this.formTable = new Table();
        this.formTable.row().pad(16);
        tmp = new Label(PokeRun.rb_default.format("label.playername"), skin);
        tmp.setColor(Color.BLACK);
        this.formTable.add(tmp);
        this.playerNameTF = new TextField("", skin);
        this.formTable.add(this.playerNameTF);

        this.formTable.row().pad(16);
        tmp = new Label(PokeRun.rb_default.format("label.pwd"), skin);
        tmp.setColor(Color.BLACK);
        this.formTable.add(tmp);
        this.pwdTF = new TextField("", skin);
        this.pwdTF.setPasswordMode(true);
        this.pwdTF.setPasswordCharacter('*');
        this.formTable.add(this.pwdTF);

        this.formTable.row().padTop(64).colspan(2);
        this.remmeCB = new CheckBox(PokeRun.rb_default.format("label.remme"), skin);
        remmeCB.getLabel().setColor(Color.BLACK);
        //this.formTable.add(this.remmeCB);

        this.mainTable.add(this.formTable);


        this.mainTable.row().colspan(2);
        this.buttonTable = new Table();
        this.buttonTable.row().pad(16);

        this.loginButton = new ImageTextButton(PokeRun.rb_default.format("button.login"), skin);
        this.loginButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SoundUtil.playNavigationSound();
                if (playerNameTF.getText().equals("") || pwdTF.getText().equals("")) {
                    new Dialog(PokeRun.rb_default.format("dialog.missinginfo.title"), skin)
                            .text(PokeRun.rb_default.format("dialog.missinginfo"))
                            .button(PokeRun.rb_default.format("dialog.confirm"), true)
                            .key(Input.Keys.ENTER, true)
                            .key(Input.Keys.ESCAPE, true)
                            .show(mainStage);
                    return;
                }
                //Http request
                login(playerNameTF.getText(), pwdTF.getText());
            }
        });

        this.registerButton = new ImageTextButton(PokeRun.rb_default.format("button.register"), skin);
        this.registerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SoundUtil.playNavigationSound();
                new Dialog(PokeRun.rb_default.format("dialog.registerinfo.title"), skin)
                        .text(PokeRun.rb_default.format("dialog.registerinfo"))
                        .button(PokeRun.rb_default.format("dialog.confirm"), true)
                        .key(Input.Keys.ENTER, true)
                        .key(Input.Keys.ESCAPE, true)
                        .show(mainStage);
            }
        });

        this.exitButton = new ImageTextButton(PokeRun.rb_default.format("button.exit"), skin);
        this.exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SoundUtil.playNavigationSound();
                dispose();
                Gdx.app.exit();
            }
        });

        this.buttonTable.add(this.loginButton).minSize(96, 64);
        this.buttonTable.add(this.registerButton).minSize(96, 64);
        this.buttonTable.add(this.exitButton).minSize(96, 64);
        this.mainTable.add(this.buttonTable);


        this.mainTable.row().colspan(2);
        this.settingButton = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("img/ui/profile/setting.png"))));
        this.settingButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SoundUtil.playNavigationSound();
                PokeRun.getInstance().setScreen(new SettingScreen(LoginScreen.getInstance()));
                SoundUtil.ensureMusicPlaying();
//                dispose();
            }
        });
        //this.mainTable.add(this.settingButton).maxSize(48, 48);
        this.settingButton.setSize(48, 48);
        this.settingButton.setPosition(LibMisc.SCREEN_W - 60, LibMisc.SCREEN_H - 60);
        this.mainStage.addActor(this.settingButton);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);
        this.mainStage.act(Gdx.graphics.getDeltaTime());
        this.mainStage.draw();
    }

    @Override
    public void resize(int width, int height) {
//        this.mainStage.getViewport().update(LibMisc.SCREEN_W, LibMisc.SCREEN_H, true);
        this.mainStage.getViewport().update(width, height, true);
//        ((ScreenViewport)this.mainStage.getViewport()).setUnitsPerPixel(1 / Gdx.graphics.getDensity());
//        this.mainStage.getCamera().project(new Vector3(LibMisc.SCREEN_W, LibMisc.SCREEN_H, 0));
//        this.mainStage.getCamera().update();
//        if (width == 480 && height == 320) {
//            camera = new OrthographicCamera(466, 700);
//        } else if (width == 240 && height == 320) {
//            camera = new OrthographicCamera(525, 700);
//        } else if (width == 240 && height == 400) {
//            camera = new OrthographicCamera(480, 800);
//        } else if (width == 240 && height == 432) {
//            camera = new OrthographicCamera(389, 700);
//        } else if (width == 640 && height == 960) {
//            camera = new OrthographicCamera(533, 800);
//        }  else if (width == 768 && height == 1366) {
//            camera = new OrthographicCamera(720, 1280);
//        } else if (width == 720 && height == 1366) {
//            camera = new OrthographicCamera(675, 1280);
//        } else if (width == 1152 && height == 1536) {
//            camera = new OrthographicCamera(1024, 1366);
//        } else if (width == 1152 && height == 1920) {
//            camera = new OrthographicCamera(854, 1366);
//        } else if (width == 1200 && height == 1920) {
//            camera = new OrthographicCamera(800, 1366);
//        } else if (height > 1280) {
//            camera = new OrthographicCamera(768, 1280);
//        } else if (height < 800) {
//            camera = new OrthographicCamera(480, 800);
//        } else {
//            camera = new OrthographicCamera(width, height);
//        }
//        camera.position.x = 240;
//        camera.position.y = 400;
//        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        this.mainStage.dispose();
    }

    /**
     * 发送登录请求给后端并继续执行界面显示逻辑
     * 由于libgdx所提供的网络API耦合性异常之高因此只能在界面类中执行此逻辑，不好进一步抽象
     *
     * @param loginStr 登录标识。会自动识别邮箱和用户名
     * @param pwd      密码
     */
    private void login(String loginStr, String pwd) {
        boolean viaEmail = NetUtil.isEmail(loginStr);
        Net.HttpRequest httpRequest = new HttpRequestBuilder()
                .newRequest()
                .method(Net.HttpMethods.POST)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .url(NetUtil.URL + "api/login/" + (viaEmail ? "email" : "playerName"))
                .content((viaEmail ? "email" : "playerName") + "=" + loginStr + "&pwd=" + pwd)
                .build();
        Gdx.net.sendHttpRequest(httpRequest, new LoginScreen.LoginRequestReceiver());
    }

    private class LoginRequestReceiver implements Net.HttpResponseListener {
        @Override
        public void handleHttpResponse(Net.HttpResponse httpResponse) {
            if (httpResponse.getStatus().getStatusCode() >= 200 && httpResponse.getStatus().getStatusCode() < 300) {
                Json json = new Json();
                //只能在外部将response记录下来，因为渲染线程看不到response的内容
                Player player = json.fromJson(Player.class, httpResponse.getResultAsString());
                Gdx.app.postRunnable(() -> {
                    PokeRun.player = player;
                    PokeRun.getInstance().setScreen(new MainScreen());
                    SoundUtil.ensureMusicPlaying();
                    LoginScreen.getInstance().dispose();
                });

            } else {
                new Dialog(PokeRun.rb_default.format("dialog.wronginfo.title"), skin)
                        .text(PokeRun.rb_default.format("dialog.wronginfo"))
                        .button(PokeRun.rb_default.format("dialog.confirm"), true)
                        .key(Input.Keys.ENTER, true)
                        .key(Input.Keys.ESCAPE, true)
                        .show(mainStage);
            }
        }

        @Override
        public void failed(Throwable t) {

        }

        @Override
        public void cancelled() {

        }
    }
}
