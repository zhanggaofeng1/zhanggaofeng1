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
    private static final String playerKey = "player_obj";
    private IoSession session;

    public GsSession(IoSession session) {
        this.session = session;
    }
    
    public void addPlayer(Player player) {
        session.setAttribute(playerKey, player);
    }
    
    public Player getPlayer() {
        return (Player)session.getAttribute(playerKey);
    }
    
    public void sendMessage(Object obj) {
        try {
            session.getHandler().messageSent(session, obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
