/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.player.vo;

import org.apache.mina.core.session.IoSession;

/**
 *
 * @author Administrator
 */
public class Player {

    private IoSession session;
    private int id;
    private int playerId;
    private int playerName;
    private int playerLv;
    private int playerVip;

    public IoSession getSession() {
        return session;
    }

    public void setSession(IoSession session) {
        this.session = session;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
