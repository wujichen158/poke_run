package com.qdu.pokerun.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetUtil {

    public static final I18NBundle rb = I18NBundle.createBundle(Gdx.files.internal("config/netconfig"));

    public static final String URL;

    static {
        URL = rb.get("http.url");
    }

    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
