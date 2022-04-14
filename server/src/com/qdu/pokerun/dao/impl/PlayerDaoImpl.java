package com.qdu.pokerun.dao.impl;

import com.qdu.pokerun.dao.PlayerDao;
import com.qdu.pokerun.entity.Player;

public class PlayerDaoImpl extends BaseDaoImpl implements PlayerDao {

    @Override
    public Player checkPlayerPwd(String playerName, String pwd) {
        return super.executeQueryDaoOBJ("select * from player where playerName=? and pwd=?", Player.class, playerName, pwd);
    }
}
