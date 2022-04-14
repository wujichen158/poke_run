package com.qdu.pokerun.actor.trade;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.qdu.pokerun.lib.LibWidgetDetails;
import com.qdu.pokerun.util.BackgroundUtil;

public class Animator implements ApplicationListener {

    // 精灵图的行列数
    private int FRAME_COLS , FRAME_ROWS;
    private String path;
    Animation<TextureRegion> walkAnimation;
    private Animation.PlayMode playMode;
    Texture walkSheet;
    SpriteBatch spriteBatch;
    private float x,y;

    float stateTime;

    public Animator(int col, int row,String path){
        this(col,row,path,30,30);
    }

    public Animator(int col, int row,String path,int x,int y){
        this.FRAME_COLS=5;
        this.FRAME_ROWS=5;
        this.path = path;
        this.x=x;
        this.y=y;
    }

    @Override
    public void create() {

        walkSheet = new Texture(Gdx.files.internal(path));


        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / FRAME_COLS,
                walkSheet.getHeight() / FRAME_ROWS);

        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        playMode =  Animation.PlayMode.NORMAL;
        walkAnimation = new Animation<TextureRegion>(0.08f, walkFrames);
        walkAnimation.setPlayMode(playMode);

        spriteBatch = new SpriteBatch();
        stateTime = 0f;
    }

    @Override
    public void resize(int width, int height) {
    }

    public void setPos(float x,float y){
        this.x=x;
        this.y=y;
    }

    public boolean isAnimationFinished(){
        return walkAnimation.isAnimationFinished(stateTime);
    }

    public void rePlay(){
        this.stateTime = 0;
    }

    @Override
    public void render() {
        Gdx.gl.glClear(1);
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, false);
        spriteBatch.begin();
        spriteBatch.draw(currentFrame, x, y,
                LibWidgetDetails.ANIMATOR_W / BackgroundUtil.wRatio,
                LibWidgetDetails.ANIMATOR_H/BackgroundUtil.hRatio
        );
        spriteBatch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        walkSheet.dispose();
    }
}
