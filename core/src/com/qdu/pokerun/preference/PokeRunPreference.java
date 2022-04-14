package com.qdu.pokerun.preference;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class PokeRunPreference {

    private final Preferences preferences;

    public PokeRunPreference() {
        preferences = Gdx.app.getPreferences("PokeRun");
    }

    /**
     * 获取一个浮点数偏好属性，若不存在则自动创建
     *
     * @param key 需要获取的偏好的键
     * @param val 默认设置的值
     * @return 偏好值或默认值
     */
    public float getFloatOrWriteDefault(String key, float val) {
        if (!preferences.contains(key)) {
            preferences.putFloat(key, val);
            preferences.flush();
            return val;
        }
        return preferences.getFloat(key);
    }

    /**
     * 获取音乐音量百分比
     *
     * @return 音乐音量百分比
     */
    public float getMusicVolume() {
        return getFloatOrWriteDefault("musicVolume", 1f);
    }

    /**
     * 设置音乐音量百分比
     */
    public void setMusicVolume(float volume) {
        preferences.putFloat("musicVolume", volume);
        preferences.flush();
    }

    /**
     * 获取音效音量百分比
     *
     * @return 音效音量百分比
     */
    public float getEffectsVolume() {
        return getFloatOrWriteDefault("effectsVolume", 1f);
    }

    /**
     * 设置音效音量百分比
     */
    public void setEffectsVolume(float volume) {
        preferences.putFloat("effectsVolume", volume);
        preferences.flush();
    }
}
