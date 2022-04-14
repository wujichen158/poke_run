package com.qdu.pokerun.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.qdu.pokerun.lib.LibMisc;

public class BackgroundUtil {

    //计算背景图片应缩放的比例
    public static Pair<Float, Float> screenScale = new Pair<>(1f, 1f);
    //宽度比率
    public static float wRatio;
    //高度比率
    public static float hRatio;

    //屏幕比率。取宽高比率最大者
    public static float screenRatio;

    //计算
    static {
        wRatio = (float) LibMisc.SCREEN_W / (float) Gdx.graphics.getWidth();
        hRatio = (float) LibMisc.SCREEN_H / (float) Gdx.graphics.getHeight();
        screenScale.first = (wRatio >= hRatio ? 1f : hRatio / wRatio);
        screenScale.second = (hRatio >= wRatio ? 1f : wRatio / hRatio);
        screenRatio = Math.max(wRatio, hRatio);
    }

    /**
     * 通过背景的ID获取该背景的名称
     *
     * @param bgID
     * @return
     */
    public static String getBgNameByID(int bgID) {
        StringBuilder bgName = new StringBuilder();
        switch (bgID) {
            case 1:
                bgName.append("rock");
                break;
            case 2:
                bgName.append("water");
                break;
            default:
                bgName.append("grass");
        }
        return bgName.toString();
    }

    /**
     * 通过背景的ID获取该背景的填充色
     *
     * @param bgID
     * @return
     */
    public static Color getBgColorByID(int bgID) {
        Color bgColor;
        switch (bgID) {
            case 1:
                bgColor = Color.BROWN;
                break;
            case 2:
                bgColor = Color.CYAN;
                break;
            default:
                bgColor = Color.CHARTREUSE;
        }
        return bgColor;
    }
}
