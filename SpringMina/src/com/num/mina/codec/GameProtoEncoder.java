/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.mina.codec;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 *
 * @author Administrator
 */
public class GameProtoEncoder extends ProtocolEncoderAdapter{

    private Charset charset;
    private static final int data_size = 4;// 数据长度值字节数

    public GameProtoEncoder() {
        charset = Charset.forName("utf8");
    }

    public GameProtoEncoder(Charset charset) {
        this.charset = charset;
    }

    @Override
    public void encode(IoSession is, Object o, ProtocolEncoderOutput peo) throws Exception {
        this.encode(is, (ByteBuffer)o, peo);
    }
    
    private void encode(IoSession session, ByteBuffer data, ProtocolEncoderOutput out) {
        int dataLen = data.array().length + data_size;
        IoBuffer buf = IoBuffer.allocate(dataLen).setAutoExpand(true);
        buf.putInt(dataLen);
        buf.put(data);
        out.write(buf);
    }
}
