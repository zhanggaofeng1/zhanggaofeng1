/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.mina.util;

import com.num.proto.resp.AbstResp;
import org.apache.mina.core.session.IoSession;

/**
 *
 * @author Administrator
 */
public class SendMsgTool {

    public static void sendMsg(IoSession session, AbstResp resp) {
        try {
            session.getHandler().messageSent(session, resp);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
