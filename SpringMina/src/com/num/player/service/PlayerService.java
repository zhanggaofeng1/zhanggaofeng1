/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.player.service;

import com.num.mina.util.GsSession;
import com.num.player.dao.SavePlayerDao;
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
    @Autowired
    private SavePlayerDao savePlayerDao;

    public void init(AbstReqProto reqPto, IoSession session) {
        reqPto.init(getGsSession(session), getPlayer(session), context);
    }
    
    private GsSession getGsSession(IoSession session) {
        return new GsSession(session);
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
        player.getGsSession().addAttr(sessionKey, player.getId());
        return true;
    }

    public boolean removePlayer(IoSession session) {
        Integer id = getPlayerId(session);
        if (id == null || id <= 0) {
            return true;
        }
        if (!players.containsKey(id)) {
            return true;
        }
        Player player = players.remove(id);
        return savePlayerDao.savePlayer(player);
    }
}
