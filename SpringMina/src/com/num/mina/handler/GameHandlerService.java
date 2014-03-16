/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.mina.handler;

import com.num.act.service.ActDataManager;
import com.num.mina.vo.GsSession;
import com.num.player.service.PlayerService;
import com.num.player.vo.Player;
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
    @Autowired
    private ActDataManager actManager;

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        log.debug("############# session opened ###################");
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        try {
            GsSession gsSession = new GsSession(session);
            Player player = gsSession.getPlayer();
            if (player != null) {
                actManager.playerOffline(player);
                playerService.removePlayer(player);
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        log.debug("################################  idle  ####################################");
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        try {
            GsSession gsSession = new GsSession(session);
            Player player = gsSession.getPlayer();
            if (player != null) {
                actManager.playerOffline(player);
                playerService.removePlayer(player);
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
        } finally {
            session.close(true);
            cause.printStackTrace();
        }
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        log.debug("#################信息发送成功之后调用###########################");
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
