/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.player.vo;

/**
 *
 * @author Administrator
 */
public class Player {

    private int id;
    private int playerId;
    private int playerName;
    private int playerLv;
    private int playerVip;
    
    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }
    
    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getPlayerName() {
        return playerName;
    }

    public void setPlayerName(int playerName) {
        this.playerName = playerName;
    }

    public int getPlayerLv() {
        return playerLv;
    }

    public void setPlayerLv(int playerLv) {
        this.playerLv = playerLv;
    }

    public int getPlayerVip() {
        return playerVip;
    }

    public void setPlayerVip(int playerVip) {
        this.playerVip = playerVip;
    }
    
}
