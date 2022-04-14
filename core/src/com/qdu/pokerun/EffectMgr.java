package com.qdu.pokerun;

import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;

import java.util.HashMap;
import java.util.Map;

public class EffectMgr {
    public void emit(String name, float val) {
        ProgressBar bar = map.get(name);
        if (bar == null) {
            throw new RuntimeException();
        }
        bar.setValue(bar.getValue() + val);
    }
    public void register(String name, ProgressBar bar) {
        map.put(name, bar);
    }
    Map<String, ProgressBar> map;
    public EffectMgr() {
        this.map = new HashMap<>();
    }
    static public EffectMgr effectMgr = new EffectMgr();
};

