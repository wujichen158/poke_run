package com.qdu.pokerun.screen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.qdu.pokerun.PokeRun;
import com.qdu.pokerun.actor.tool.ToolElementList;
import com.qdu.pokerun.actor.tool.CleanTool;
import com.qdu.pokerun.actor.tool.element.DragToolElement;
import com.qdu.pokerun.actor.tool.FeedTool;
import com.qdu.pokerun.actor.PokemonBarElement;
import com.qdu.pokerun.actor.PokemonMain;
import com.qdu.pokerun.actor.PokemonBarSprite;
import com.qdu.pokerun.entity.Background;
import com.qdu.pokerun.entity.Pokemon;
import com.qdu.pokerun.lib.LibMisc;
import com.qdu.pokerun.lib.LibPokemonDetails;
import com.qdu.pokerun.lib.LibWidgetDetails;
import com.qdu.pokerun.util.BackgroundUtil;
import com.qdu.pokerun.util.Pair;
import com.qdu.pokerun.util.PokemonUtil;
import com.qdu.pokerun.util.SoundUtil;
import com.qdu.pokerun.util.viewport.FitScreenViewport;
import com.ray3k.stripe.PopTable;
import com.ray3k.stripe.PopTableClickListener;

import java.util.Locale;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import javax.swing.GrayFilter;

import com.qdu.pokerun.EffectMgr;

public class MainScreen implements Screen {

    //    final PokeRun game;
    private final Stage mainStage;
    private Table mainTable;

    //标题
    private Label titleLabel;

    //玩家信息按钮
    private ImageButton userButton;
    //扭蛋按钮
    private ImageButton gashButton;

    //当前用户背包中持有的宝可梦列表
    private Array<Pokemon> pokemonList;

    //左侧UI栏的整个组
    private VerticalGroup pokemonSpritesGroup;

    //中间大宝可梦贴图
    private PokemonMain pokemonMain;

    //按比例计算两个精灵的间隔
    static int gapX = LibMisc.SCREEN_W / 64;
    static int gapY = LibMisc.SCREEN_H / 64;

    //右侧工具栏的整个组
    private VerticalGroup toolGroup;

    //工具栏ui（清洁用和喂食用）
    private CleanTool cleanTool;
    private FeedTool feedTool;

    //工具栏点击后展开的列表
    private PopTable cleanPopTable;
    private PopTable feedPopTable;

    //工具栏点击后展开的列表内容
    private VerticalGroup cleanToolGroup;
    private VerticalGroup feedToolGroup;

    //所处场景对象
    private Background bgMain;
    private Image bgImg;

    //清洁物品和食物图标被点击后扩大的大小

    //被选中宝可梦所在背包的位置。自上到下依次是0到5
    private int selectedNum;

    //该玩家持有的宝可梦个数
    private int pokemonCnt;

    //整体样式
    Skin skin;

    //等级文本
    private Label levelLabel;
    private Label levelTitleLabel;
    //经验条、友情值条
    private Label friendshipLabel;
    private Label expLabel;
    private ProgressBar friendshipBar;
    private ProgressBar expBar;

    //中间整个表格
    private Table centerTable;
    //下方条的表格
    private Table barTable;

    //下方按钮的表格
    private Table buttonTable;

    private ImageTextButton settingButton;
    private ImageTextButton tradeButton;
    private ImageTextButton buffButton;

//    private final SettingScreen settingScreen;
//    private final TradeScreen tradeScreen;
//    private final ProfileScreen profileScreen;
//    private final BuffScreen buffScreen;
//    private final DrawCardScreen drawCardScreen;

    private SpriteBatch batch;

    private static MainScreen instance;

    public static MainScreen getInstance() {
        if (instance == null) {
            instance = new MainScreen();
        }
        return instance;
    }

    /**
     * 初始化所有宝可梦相关的静态资源
     */
    private void initPokemonResources() {
        this.pokemonList = PokemonUtil.getPokemonsByPlayerId(PokeRun.player.getPlayerName());
        pokemonCnt = 0;

        this.pokemonSpritesGroup.clearChildren();

        //使用scene2D中的小部件组进行左侧宝可梦UI的描绘
        for (Pokemon p : pokemonList) {
            PokemonBarElement elem = new PokemonBarElement(p);
//            elem.setX(0);
//            elem.setY(i * (LibPokemonDetails.POKEBALL_UI_H + gapY));
            pokemonCnt++;
            this.pokemonSpritesGroup.addActor(elem);
        }

        for (int i = pokemonCnt; i < 6; i++) {
            PokemonBarElement none = new PokemonBarElement(0, -1, false);
            this.pokemonSpritesGroup.addActor(none);
        }

//        i = 5;
        //为左侧栏的每一个宝可梦添加事件监听器
        for (int i = 0; i < pokemonCnt; i++) {
            Actor a = this.pokemonSpritesGroup.getChild(i);
            int finalI = i;
            a.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    SoundUtil.playPokemonClickSound();
                    //取消原本的选择
                    ((PokemonBarElement) pokemonSpritesGroup.getChild(selectedNum)).setSelected(false);

                    //选择新的宝可梦
                    PokemonBarElement elemTemp = (PokemonBarElement) a;
                    elemTemp.setSelected(true);
                    selectedNum = finalI;

                    //设置中间大图
                    pokemonMain.getActor().setDrawable(elemTemp.getActor().getDrawable());
                    //更新友情值和经验值的槽
                    updateBars(finalI);

                    return super.touchDown(event, x, y, pointer, button);
                }
            });
        }

        //默认选择第一只
        PokemonBarSprite pokemon1st = ((PokemonBarElement) this.pokemonSpritesGroup.getChildren().get(0)).getActor();
        pokemon1st.setSelected(true);
        pokemonMain.getActor().setDrawable(pokemon1st.getDrawable());
        updateBars(0);

//        this.pokemonSpritesGroup.fill(80f/64f);
    }

    private void initBgResource() {
        this.bgMain = new Background(MathUtils.random(0, 2));
        this.bgImg = new Image(new Texture(Gdx.files.internal("img/bg/" + this.bgMain.getBgName() + "/" + this.bgMain.getBgName() + MathUtils.random(1, 3) + ".png")));
        this.bgImg.setScale(
                BackgroundUtil.screenScale.getFirst(),
                BackgroundUtil.screenScale.getSecond()
        );
    }

    /**
     * 初始化养成按钮（清洁和喂食）
     */
    private void initCaringResources() {

        //设置弹出工具栏的样式
        PopTable.PopTableStyle popTableStyle = new PopTable.PopTableStyle();

        //定义清洁弹出工具栏的监听器
        PopTableClickListener cleanPopTableClickListener = new PopTableClickListener(Align.left, Align.bottomLeft, popTableStyle);
        this.cleanTool.addListener(cleanPopTableClickListener);
        this.cleanTool.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SoundUtil.playToolClickSound();
            }
        });

        //定义喂食弹出工具栏的监听器
        PopTableClickListener feedPopTableClickListener = new PopTableClickListener(Align.left, Align.left, popTableStyle);
        this.feedTool.addListener(feedPopTableClickListener);
        this.feedTool.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SoundUtil.playToolClickSound();
            }
        });

        //获取弹出工具栏
        this.cleanPopTable = cleanPopTableClickListener.getPopTable();
        this.feedPopTable = feedPopTableClickListener.getPopTable();

        //为清洁工具栏添加工具
        for (DragToolElement tool : ToolElementList.CleanToolList) {
            this.cleanToolGroup.addActor(tool);
        }

        //为食品工具栏添加工具
        for (DragToolElement tool : ToolElementList.FeedToolList) {
            this.feedToolGroup.addActor(tool);
        }

        //将工具组添加到弹出工具栏中
        this.cleanPopTable.add(this.cleanToolGroup);
        this.feedPopTable.add(this.feedToolGroup);

        //将可以弹出工具栏的按钮添加至工具的纵向组中
        this.toolGroup.addActor(this.cleanTool);
        this.toolGroup.addActor(this.feedTool);

    }

    /**
     * 初始化按钮样式
     */
    private void initGlobalSkin() {
        //使用外部文件中的样式
        skin = new Skin(Gdx.files.internal("style/uiskin.json"));
    }

    /**
     * 初始化下方经验条、亲密度条
     */
    private void initBarResources() {
        this.friendshipBar.setColor(255f / 255f, 20f / 255f, 147f / 255f, 1f);
        this.expBar.setColor(0f / 255f, 191f / 255f, 255f / 255f, 1f);
    }

    /**
     * 更新经验条、亲密度条
     *
     * @param i 待更新的精灵在背包中的位置
     */
    private void updateBars(int i) {
        if (pokemonList.size > 0) {
            this.levelLabel.setText(pokemonList.get(i).getLevel());
            this.friendshipBar.setValue(pokemonList.get(i).getFriendship());
            this.expBar.setValue(pokemonList.get(i).getExperience());
        }
    }

    public MainScreen() {
        instance = this;

        this.mainStage = new Stage(new FitScreenViewport());
//        this.mainStage.getCamera().project(new Vector3(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),0));
//        Gdx.input.setInputProcessor(this.mainStage);

        initBgResource();
        this.mainStage.addActor(this.bgImg);

        this.mainTable = new Table();
//        this.mainTable.debug();
//        this.mainTable.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.mainTable.setSize(LibMisc.SCREEN_W, LibMisc.SCREEN_H);
        this.mainStage.addActor(this.mainTable);

        this.mainTable.setFillParent(true);
//        this.mainTable.setBackground(new Image(bgTerrain).getDrawable());

//        this.mainStage.getCamera().position.set(LibMisc.SCREEN_W / 2f, LibMisc.SCREEN_H / 2f, 0);

        //加载visUI并调整其缩放率
//        VisUI.load(VisUI.SkinScale.X2);
        batch = new SpriteBatch();

//        settingScreen = new SettingScreen(this);
//        tradeScreen = new TradeScreen(this);
//        profileScreen = new ProfileScreen(this);
//        buffScreen = new BuffScreen(this);
//        drawCardScreen = new DrawCardScreen(this);

        initGlobalSkin();

        this.titleLabel = new Label(PokeRun.rb_default.format("label.title"), skin);
        this.titleLabel.setAlignment(Align.center);
        this.titleLabel.setFontScale(2);
        this.userButton = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("img/ui/profile/profile.png"))));
        this.userButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SoundUtil.playNavigationSound();
                PokeRun.getInstance().setScreen(new ProfileScreen(MainScreen.getInstance()));
                SoundUtil.ensureMusicPlaying();
            }
        });
        this.gashButton = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("img/ui/profile/gashapon.png"))));
        this.gashButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SoundUtil.playNavigationSound();
                PokeRun.getInstance().setScreen(new DrawCardScreen(MainScreen.getInstance()));
                SoundUtil.ensureMusicPlaying();
            }
        });

        this.levelLabel = new Label("0", skin);
        this.friendshipBar = new ProgressBar(0, 255, 1, false, skin);
        this.expBar = new ProgressBar(0, 10000, 1, false, skin);
        initBarResources();

        this.pokemonSpritesGroup = new VerticalGroup().space(gapY);
        this.pokemonMain = new PokemonMain(0, false, this.bgMain.getBgName());

        this.toolGroup = new VerticalGroup().space(gapY);
        this.cleanToolGroup = new VerticalGroup().space(gapY);
        this.feedToolGroup = new VerticalGroup().space(gapY);
        this.cleanTool = new CleanTool();
        this.feedTool = new FeedTool();
        initCaringResources();

        this.settingButton = new ImageTextButton(PokeRun.rb_default.format("button.setting"), skin);
        this.tradeButton = new ImageTextButton(PokeRun.rb_default.format("button.trade"), skin);
        this.buffButton = new ImageTextButton(PokeRun.rb_default.format("button.buff"), skin);

        this.settingButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SoundUtil.playNavigationSound();
                PokeRun.getInstance().setScreen(new SettingScreen(MainScreen.getInstance()));
                SoundUtil.ensureMusicPlaying();
            }
        });

        this.tradeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SoundUtil.playNavigationSound();
                PokeRun.getInstance().setScreen(new TradeScreen(MainScreen.getInstance()));
                SoundUtil.ensureMusicPlaying();
            }
        });

        this.buffButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SoundUtil.playNavigationSound();
                PokeRun.getInstance().setScreen(new BuffScreen(MainScreen.getInstance()));
                SoundUtil.ensureMusicPlaying();
            }
        });

        this.mainTable.row().width(LibWidgetDetails.PROFILE_W).height(LibWidgetDetails.PROFILE_H).padBottom(8);
        this.mainTable.add(this.userButton);
        this.mainTable.add(this.titleLabel);
        this.mainTable.add(this.gashButton);

        this.mainTable.row();
        this.mainTable.add(this.pokemonSpritesGroup).minWidth(LibPokemonDetails.POKEBALL_UI_W).center().expandX();

        this.centerTable = new Table();
        this.centerTable.add(this.pokemonMain).minSize(LibPokemonDetails.POKEMON_MAIN_W, LibPokemonDetails.POKEMON_MAIN_H);
        this.centerTable.row().padTop(64);
        this.barTable = new Table();
        this.barTable.row();
        this.levelTitleLabel = new Label(PokeRun.rb_default.format("label.level"), skin);
        this.barTable.add(this.levelTitleLabel);
        this.barTable.add(this.levelLabel);
        this.barTable.row();
        this.friendshipLabel = new Label(PokeRun.rb_default.format("label.friendship"), skin);
        this.barTable.add(this.friendshipLabel);
        this.barTable.add(this.friendshipBar);
        EffectMgr.effectMgr.register("friendship", this.friendshipBar);
        this.barTable.row();
        this.expLabel = new Label(PokeRun.rb_default.format("label.exp"), skin);
        this.barTable.add(this.expLabel);
        this.barTable.add(this.expBar);
        EffectMgr.effectMgr.register("exp", this.expBar);
        this.centerTable.add(this.barTable);

        this.mainTable.add(this.centerTable).minSize(LibPokemonDetails.POKEMON_MAIN_W, LibPokemonDetails.POKEMON_MAIN_H).expandX();
        this.mainTable.add(this.toolGroup).minWidth(LibPokemonDetails.CLEAN_ITEM_W).top().expandX();

        this.mainTable.row().colspan(3).padTop(8);
        this.buttonTable = new Table();
        this.buttonTable.row().space(LibWidgetDetails.BUTTON_SPACE);
        this.buttonTable.add(this.settingButton).minSize(LibWidgetDetails.BUTTON_W, LibWidgetDetails.BUTTON_H);
        this.buttonTable.add(this.tradeButton).minSize(LibWidgetDetails.BUTTON_W, LibWidgetDetails.BUTTON_H);
        this.buttonTable.add(this.buffButton).minSize(LibWidgetDetails.BUTTON_W, LibWidgetDetails.BUTTON_H);
        this.mainTable.add(this.buttonTable);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this.mainStage);
        initPokemonResources();
        this.titleLabel.setText(PokeRun.rb_default.format("label.title"));
        this.levelTitleLabel.setText(PokeRun.rb_default.format("label.level"));
        this.friendshipLabel.setText(PokeRun.rb_default.format("label.friendship"));
        this.expLabel.setText(PokeRun.rb_default.format("label.exp"));
        this.settingButton.setText(PokeRun.rb_default.format("button.setting"));
        this.tradeButton.setText(PokeRun.rb_default.format("button.trade"));
        this.buffButton.setText(PokeRun.rb_default.format("button.buff"));
    }

    @Override
    public void render(float delta) {
//        System.out.println("group:"+this.pokemonSpritesGroup.getWidth()+", "+this.pokemonSpritesGroup.getHeight());
//        System.out.println("main:"+this.pokemonMain.getWidth()+", "+this.pokemonMain.getHeight());
        //根据场景切换背景填充色
        ScreenUtils.clear(this.bgMain.getBgColor());

        this.mainStage.act(Gdx.graphics.getDeltaTime());

//        this.game.batch.setProjectionMatrix(mainStage.getCamera().combined);

        this.mainStage.draw();
        //判是否按中了清洁物品或食物

        Camera camera = mainStage.getCamera();
        camera.update();
        Batch batch = mainStage.getBatch();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        Stream.concat(Arrays.stream(ToolElementList.FeedToolList),
                Arrays.stream(ToolElementList.CleanToolList))
                .map(DragToolElement::getDraggableTexture)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(f -> f.apply(point -> {
                    Vector3 v = new Vector3();
                    v.set(point.first, point.second, 0);
                    camera.unproject(v);
                    return new Pair<>(Float.valueOf(v.x), Float.valueOf(v.y));
                }))
                .forEach(pair -> {
                    //System.out.println(pair.second.first + " " + pair.second.second);
                    batch.draw(pair.first, pair.second.first, pair.second.second);
                    //batch.draw(pair.first, pair.second.first, pair.second.second);
                });
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        this.mainStage.getViewport().update(width, height, true);
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
//        VisUI.dispose();
//        settingScreen.dispose();
        mainStage.dispose();
        batch.dispose();

    }
}
