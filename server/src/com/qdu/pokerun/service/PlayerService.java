package com.qdu.pokerun.service;

import com.qdu.pokerun.dao.PlayerDao;
import com.qdu.pokerun.dao.impl.PlayerDaoImpl;
import com.qdu.pokerun.entity.Player;

public interface PlayerService {

    PlayerDao dao=new PlayerDaoImpl();

    public Player checkPlayerPwd(String playerName, String pwd);
}
