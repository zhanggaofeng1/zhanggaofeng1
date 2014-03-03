/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.proto.req.impl;

import com.num.player.service.LoginService;
import com.num.proto.req.AbstReqProto;
import org.apache.mina.core.buffer.IoBuffer;

/**
 *
 * @author Administrator
 */
public class ReqLoginProto extends AbstReqProto{

    private int playerId;
    
    @Override
    public void reader(IoBuffer buf) {
        playerId = this.readInt(buf);
    }

    @Override
    public void req_handler() {
        LoginService loginService = getContext().getBean(LoginService.class);
        loginService.playerLogin(getGsSessioin(), playerId);
    }
    
}
