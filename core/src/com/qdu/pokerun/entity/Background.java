package com.qdu.pokerun.entity;

import com.badlogic.gdx.graphics.Color;
import com.qdu.pokerun.util.BackgroundUtil;

/**
 * 场地
 */
public class Background {
    //场地名称
    private String bgName;

    //场地ID
    private int bgID;

    //场地所代表的背景色
    private Color bgColor;

    public Background() {
    }

    public Background(int bgID) {
        this.bgID = bgID;
        this.bgName= BackgroundUtil.getBgNameByID(bgID);
        this.bgColor=BackgroundUtil.getBgColorByID(bgID);
    }

    public Background(String bgName, int bgID, Color bgColor) {
        this.bgName = bgName;
        this.bgID = bgID;
        this.bgColor = bgColor;
    }

    public String getBgName() {
        return bgName;
    }

    public void setBgName(String bgName) {
        this.bgName = bgName;
    }

    public int getBgID() {
        return bgID;
    }

    public void setBgID(int bgID) {
        this.bgID = bgID;
    }

    public Color getBgColor() {
        return bgColor;
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }
}
