/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.mina.codec;

import com.num.proto.resp.AbstResp;
import java.nio.charset.Charset;
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

    public GameProtoEncoder() {
        charset = Charset.forName("utf8");
    }

    public GameProtoEncoder(Charset charset) {
        this.charset = charset;
    }

    @Override
    public void encode(IoSession session, Object o, ProtocolEncoderOutput out) throws Exception {
        
        AbstResp resp = (AbstResp)o;
        IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true);
        buf.putInt(0);
        buf.putShort(resp.getProtoId());
        resp.writer(buf);
        buf.flip();
        buf.putInt(buf.remaining());
        buf.position(0);
        out.write(buf);
    }
}
