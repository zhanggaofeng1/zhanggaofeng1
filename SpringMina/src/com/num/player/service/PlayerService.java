/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.player.service;

import com.num.player.vo.Player;
import com.num.proto.req.AbstReqProto;
import javolution.util.FastMap;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class PlayerService {

    private static final Logger log = LoggerFactory.getLogger(LoginService.class);
    private static final String sessionKey = "playerId";
    private FastMap<Integer, Player> players = new FastMap<Integer, Player>().setShared(true);
    @Autowired
    private ApplicationContext context;

    public void init(AbstReqProto reqPto, IoSession session) {
        reqPto.init(session, getPlayer(session), context);
    }

    private Player getPlayer(IoSession session) {
        Integer playerId = getPlayerId(session);
        if (playerId == null || playerId <= 0) {
            return null;
        }
        return players.get(playerId);
    }

    private Integer getPlayerId(IoSession session) {
        return (Integer) session.getAttribute(sessionKey);
    }
    
    public boolean addPlayer(Player player) {
        players.put(player.getId(), player);
        player.getSession().setAttribute(sessionKey, player.getId());
        return true;
    }
}
