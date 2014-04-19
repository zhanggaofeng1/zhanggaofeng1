/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.mina.handler;

import com.num.act.service.PlayerActService;
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
    private PlayerActService actManager;

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        log.debug("############# session opened ###################");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        log.debug("################################  idle  ####################################");
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        log.debug("#################信息发送成功之后调用###########################");
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        log.debug("################### 信息session关闭 ###############################");
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        
        Player player = new GsSession(session).getPlayer();
        playerService.savePlayerInfo(player);
        session.close(true);
        log.error("用户id " + player.getPlayerId() + "EXCEPTION:" + cause.getMessage());
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {

        IoBuffer buf = (IoBuffer) message;
        short protoId = buf.getShort();
        AbstReqProto reqProto = registerPtoService.getReqProtoById(protoId);
        if (reqProto == null) {
            log.error("req : 协议id = " + protoId + " 的协议没有注册！");
            return;
        }
        reqProto.reader(buf);
        reqProto.req_handler();
    }
}
