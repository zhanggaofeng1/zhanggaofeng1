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

    private static final Logger log = LoggerFactory.getLogger(PlayerService.class);

    public Player loadPlayerInfo(int userId) {


        return null;
    }

    public boolean savePlayerInfo(Player player) {

        if (player == null) {
            return false;
        }

        return true;
    }
}
