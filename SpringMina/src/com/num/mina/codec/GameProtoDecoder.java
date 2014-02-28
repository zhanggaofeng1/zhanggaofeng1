/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.mina.codec;

import java.nio.charset.Charset;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 *
 * @author Administrator
 */
public class GameProtoDecoder extends CumulativeProtocolDecoder{

    private final AttributeKey key = new AttributeKey(getClass(), "context");
    private final static int data_len = 4; // 数据长度值字节数
    private Charset charset;
    
    public GameProtoDecoder() {
        charset = Charset.forName("utf8");
    }
    
    public GameProtoDecoder(Charset charset) {
        this.charset = charset;
    }
    
    private class ContextObject {
        public IoBuffer inBuf;
        public int length = 0;
        public int matchCt = 0;
        
        public void initContext() {
            if (inBuf == null) {
                this.inBuf = IoBuffer.allocate(512).setAutoExpand(true);
            } else {
                inBuf.clear();
            }
            length = 0;
            matchCt = 0;
        }
        
        public boolean readOver() {
            return length == matchCt;
        }
        
        public ContextObject() {
        }
    }
    
    private ContextObject getContext(IoSession session) {
        
        ContextObject ctx = (ContextObject) session.getAttribute(key);
        if (ctx == null) {
            ctx = new ContextObject();
            ctx.initContext();
            session.setAttribute(key, ctx);
        }
        return ctx;
    }
    

    @Override
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        
        ContextObject ctx = getContext(session);
        while (in.hasRemaining()) {
            if (ctx.inBuf.position() < 0) {
                if (in.remaining() <= data_len) {
                    return true;
                }
                ctx.length = in.getInt() - data_len;
            }
            byte b = in.get();
            ctx.inBuf.put(b);
            ctx.matchCt++;
            if (ctx.readOver()) {
                ctx.inBuf.flip();
                out.write(ctx.inBuf);
                ctx.initContext();
                return false;
            }
        }
        return true;
    }
    
}
