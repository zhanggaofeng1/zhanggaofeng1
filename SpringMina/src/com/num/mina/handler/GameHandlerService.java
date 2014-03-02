/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.mina.handler;

import com.num.player.service.LoginService;
import com.num.player.service.PlayerService;
import com.num.proto.req.AbstReqProto;
import com.num.proto.service.RegisterProtoService;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Administrator
 */
public class GameHandlerService extends IoHandlerAdapter {

    private static final Logger log = LoggerFactory.getLogger(GameHandlerService.class);
    @Autowired
    private RegisterProtoService registerPtoService;
    @Autowired
    private PlayerService playerService;
    
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        log.debug("############# session opened ###################");
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        log.debug("############# session closed ###################");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        log.debug("############# session idle ###################");
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        cause.printStackTrace();
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        
        IoBuffer buf = (IoBuffer) message;
        short protoId = buf.getShort();
        Class<? extends AbstReqProto> protoClazz = registerPtoService.getReqProtoById(protoId);
        if (protoClazz == null) {
            log.error("协议id = " + protoId + " 的协议没有注册！");
            return ;
        }
        AbstReqProto proto = protoClazz.newInstance();
        playerService.init(proto, session);
        proto.reader(buf);
        proto.req_handler();
    }
    
   
}
