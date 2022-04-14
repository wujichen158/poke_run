package com.qdu.pokerun.dao;

import com.qdu.pokerun.entity.Player;

public interface PlayerDao extends BaseDao{

    /**
     * 检查玩家是否合法
     *
     * @param playerName 玩家名
     * @param pwd 密码
     * @return 合法则返回非0值
     */
    public Player checkPlayerPwd(String playerName, String pwd);

}
