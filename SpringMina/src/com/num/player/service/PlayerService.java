/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.player.service;

import com.num.player.dao.PlayerDao;
import com.num.player.vo.Player;
import javolution.util.FastMap;
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
    private FastMap<Integer, Player> players = new FastMap<Integer, Player>().setShared(true);
    @Autowired
    private ApplicationContext context;
    @Autowired
    private PlayerDao savePlayerDao;

    public boolean addPlayer(Player player) {
        players.put(player.getId(), player);
        return true;
    }

    public boolean removePlayer(Player player) {
        
        if (!players.containsKey(player.getId())) {
            return true;
        }
        players.remove((Integer)player.getId());
        return savePlayerDao.savePlayer(player);
    }
}
