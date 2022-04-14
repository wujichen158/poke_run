package com.qdu.pokerun.util;

import com.qdu.pokerun.entity.Player;

@Deprecated
public class PlayerUtil {

    /**
     * 检查登录玩家的信息
     *
     * @param name 玩家名
     * @param pwd 玩家密码
     * @return 如果玩家合法登录则返回玩家对象
     */
    public static Player checkPlayerInfo(String name, String pwd){
        Player player = new Player("", "", "");
        if((name.equals("wujichen158") && pwd.equals("123456"))
            ||(name.equals("muyoo") && pwd.equals("123456"))
            ||(name.equals("yxaa") && pwd.equals("123456"))
            ||(name.equals("gyf") && pwd.equals("123456"))
        ){
            player.setPlayerName(name);
            player.setEmail(getEmailByPlayername(name));
        }
        return player;
    }

    public static String getEmailByPlayername(String playername){
        if (playername.equals("wujichen158")){
            return "1169083089@qq.com";
        } else if (playername.equals("muyoo")){
            return "muyoo.top@qq.com";
        } else if (playername.equals("yxaa")){
            return "yxa2111@qq.com";
        } else if (playername.equals("gyf")){
            return "1404220384@qq.com";
        }
        return "";
    }
}
