/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.player.service;

import com.num.enums.RespState;
import com.num.mina.vo.GsSession;
import com.num.player.dao.PlayerDao;
import com.num.proto.service.SendMsgService;
import com.num.proto.resp.ResultState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class LoginService {

    private static final Logger log = LoggerFactory.getLogger(LoginService.class);
    @Autowired
    private PlayerService playerService;
    @Autowired
    private SendMsgService sendService;
    @Autowired
    private PlayerDao playerDao;

    public void savePlayer(GsSession gsSession, int playerId) {

//        playerDao.savePlayer(null);
        playerDao.loadPlayer();
        log.error("playerId = " + playerId + " 的用户还没有注册！！！");
        sendService.sendMsg(gsSession, new ResultState(RespState.login_error.value()));
        
//        gsSession.addPlayer(player);
//        player.setGsSession(gsSession);
//
//        playerService.addPlayer(player);
//        sendService.sendMsg(gsSession, new ResultState(RespState.login_success.value()));
    }
}
