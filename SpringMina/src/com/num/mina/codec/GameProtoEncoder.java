/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.mina.codec;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 *
 * @author Administrator
 */
public class GameProtoEncoder extends ProtocolEncoderAdapter {

    private Charset charset;
    private static final int data_size = 4;// 数据长度值字节数

    public GameProtoEncoder() {
        charset = Charset.forName("utf8");
    }

    public GameProtoEncoder(Charset charset) {
        this.charset = charset;
    }

    @Override
    public void encode(IoSession session, Object o, ProtocolEncoderOutput out) throws Exception {

        ByteBuffer buf = (ByteBuffer)o;
        byte[] dataArr = Arrays.copyOf(buf.array(), buf.remaining());
        int dataLen = dataArr.length + data_size;
        
        IoBuffer data = IoBuffer.allocate(dataLen);
        data.putInt(dataLen);
        data.put(dataArr);
        data.flip();
        
        out.write(data);
    }
}
