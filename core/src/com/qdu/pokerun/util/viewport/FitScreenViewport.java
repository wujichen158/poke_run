package com.qdu.pokerun.util.viewport;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.qdu.pokerun.lib.LibMisc;
import com.qdu.pokerun.util.BackgroundUtil;

/**
 * 适用于Android的视口策略
 */
public class FitScreenViewport extends ScreenViewport {

    public FitScreenViewport(){
        super();
        //密度取最大，否则会过度放大
        setUnitsPerPixel(BackgroundUtil.screenRatio);
    }
}
