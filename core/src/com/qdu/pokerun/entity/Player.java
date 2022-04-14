package com.qdu.pokerun.entity;

public class Player {

    String uuid;
    String playerName;
    String pwd;
    String email;
    String avatar;
    int permission;

    public Player() {
    }

    public Player(String playerName, String uuid, String email) {
        this.playerName = playerName;
        this.uuid = uuid;
        this.email = email;
    }

    public Player(String playerName, String email){
        this.playerName=playerName;
        this.email=email;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }
}
