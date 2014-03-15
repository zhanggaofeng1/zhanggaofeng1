/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.mina.handler;

import com.num.act.service.ActDataManager;
import com.num.mina.vo.GsSession;
import com.num.main.service.SendMsgService;
import com.num.player.service.PlayerService;
import com.num.proto.req.AbstReqProto;
import com.num.proto.resp.AbstResp;
import com.num.proto.resp.impl.ResultState;
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
    @Autowired
    private ActDataManager actManager;

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        log.debug("############# session opened ###################");
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
<<<<<<< HEAD
        GsSession gsSession = new GsSession(session);
        actManager.userOffline(gsSession);
        playerService.removePlayer(gsSession);
=======
        
        GsSession gsSession = new GsSession(session);
        try {
            actManager.playerOffline(gsSession);
            playerService.removePlayer(gsSession);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
>>>>>>> 26728141c00c26ae4031e2e915bc36e93a12911a
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        
        GsSession gsSession = new GsSession(session);
        try {
            actManager.playerOffline(gsSession);
            playerService.removePlayer(gsSession);
            session.close(true);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        
        cause.printStackTrace();
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        // 信息发送成功之后，调用！！
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {

        IoBuffer buf = (IoBuffer) message;
        short protoId = buf.getShort();
        Class<? extends AbstReqProto> protoClazz = registerPtoService.getReqProtoById(protoId);
        if (protoClazz == null) {
            log.error("req : 协议id = " + protoId + " 的协议没有注册！");
            return;
        }
        AbstReqProto proto = protoClazz.newInstance();
        playerService.init(proto, session);
        proto.reader(buf);
        proto.req_handler();
    }
}
