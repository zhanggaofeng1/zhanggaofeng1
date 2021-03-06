/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.mina.vo;

import com.num.player.vo.Player;
import org.apache.mina.core.session.IoSession;

/**
 *
 * @author Administrator
 */
public class GsSession {
    
    public static final String playerKey = "player_obj";
    private IoSession session;
    
    public GsSession(IoSession session) {
        this.session = session;
    }
    
    public void setSession(IoSession session) {
        this.session = session;
    }
    
    public void addPlayer(Player player) {
        session.setAttribute(playerKey, player);
    }
    
    public Player getPlayer() {
        return (Player)session.getAttribute(playerKey);
    }
    
    public void sendMessage(Object obj) {
        session.write(obj);
    }
}
