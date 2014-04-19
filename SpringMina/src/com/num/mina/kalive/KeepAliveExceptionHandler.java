/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.mina.kalive;

import com.num.mina.vo.GsSession;
import com.num.player.service.PlayerService;
import com.num.player.vo.Player;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Administrator
 */
public class KeepAliveExceptionHandler implements KeepAliveRequestTimeoutHandler {

     private static final Logger log = LoggerFactory.getLogger(KeepAliveExceptionHandler.class);
    @Autowired
    private PlayerService playerService;

    @Override
    public void keepAliveRequestTimedOut(KeepAliveFilter kaf, IoSession session) throws Exception {
        
        Player player = new GsSession(session).getPlayer();
        playerService.savePlayerInfo(player);
        session.close(true);
        log.info("用户id = " + player.getPlayerId() + "  keep-alive timeout#####################");
    }
}
