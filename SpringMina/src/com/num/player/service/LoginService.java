/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.player.service;

import com.num.enums.RespState;
import com.num.mina.util.GsSession;
import com.num.mina.util.SendMsgTool;
import com.num.player.vo.Player;
import com.num.player.dao.LoadPlayerDao;
import com.num.proto.resp.impl.ResultState;
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
    private LoadPlayerDao loadPlayerDao;
    @Autowired
    private PlayerService playerService;

    public void playerLogin(GsSession session, int playerId) {

        Player player = loadPlayerDao.loadPlayer(playerId);
        if (player == null) {
            log.error("playerId = " + playerId + " 的用户还没有注册！！！");
            SendMsgTool.sendMsg(session, new ResultState(RespState.login_error.value()));
            return;
        }
        session.addPlayer(player);
        playerService.addPlayer(player);
        SendMsgTool.sendMsg(session, new ResultState(RespState.login_success.value()));
    }
}
