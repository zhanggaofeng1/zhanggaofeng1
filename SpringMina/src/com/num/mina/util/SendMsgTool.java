/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.mina.util;

import com.num.proto.resp.AbstResp;
import java.util.Arrays;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

/**
 *
 * @author Administrator
 */
public class SendMsgTool {

    private static final int data_size = 4;// 数据长度值字节数

    private static IoBuffer encoder(IoBuffer buf) {

        int remainLen = buf.remaining();
        byte[] data = Arrays.copyOf(buf.array(), remainLen);
        buf.clear();
        buf.putInt(remainLen + data_size);
        for (int i = 0; i < remainLen; i++) {
            buf.put(data[i]);
        }
        buf.flip();
        return buf;
    }
    
    public static void sendMsg(IoSession session, AbstResp resp) {
        resp.writer();
        resp.sendMsg(session);
    }
}
