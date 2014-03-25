/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.proto.service;

import com.num.mina.vo.GsSession;
import com.num.proto.resp.AbstResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class SendMsgService {

    @Autowired
    private RegisterProtoService registerPtoService;

    public void sendMsg(GsSession session, AbstResp resp) {

        Short protoId = registerPtoService.getRespProtoIdByClazz(resp.getClass());
        if (protoId == null || protoId <= 0) {
            try {
                throw new Throwable("resp : 协议名字：" + resp.getClass().getName() + " 的协议没有注册");
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
        resp.setProtoId(protoId);
        
        session.sendMessage(resp);
    }
}
