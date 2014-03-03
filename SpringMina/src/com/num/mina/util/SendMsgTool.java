/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.mina.util;

import com.num.mina.vo.GsSession;
import com.num.proto.resp.AbstResp;

/**
 *
 * @author Administrator
 */
public class SendMsgTool {
    public static void sendMsg(GsSession session, AbstResp resp) {
            session.sendMessage(resp);
    }
}
