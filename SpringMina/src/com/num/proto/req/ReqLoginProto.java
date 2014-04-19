/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.proto.req;

import com.num.player.service.LoginService;
import org.apache.mina.core.buffer.IoBuffer;

/**
 *
 * @author Administrator
 */
public class ReqLoginProto extends AbstReqProto{

    private LoginService loginService;
    private int playerId;
    
    public ReqLoginProto(short protoId) {
        super(protoId);
    }
    
    @Override
    public void init() {
        loginService = context.getBean(LoginService.class);
    }
    
    @Override
    public void reader(IoBuffer buf) {
        playerId = this.readInt(buf);
    }

    @Override
    public void req_handler() {

    }
    
}
