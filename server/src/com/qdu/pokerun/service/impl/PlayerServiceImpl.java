package com.qdu.pokerun.service.impl;

import com.qdu.pokerun.entity.Player;
import com.qdu.pokerun.service.PlayerService;

import java.util.Optional;

public class PlayerServiceImpl implements PlayerService {

    @Override
    public Player checkPlayerPwd(String playerName, String pwd) {
        return Optional.ofNullable(dao.checkPlayerPwd(playerName, pwd)).orElse(new Player("", "", ""));
    }
}
