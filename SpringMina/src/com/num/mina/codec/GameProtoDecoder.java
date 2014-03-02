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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Administrator
 */
public class GameProtoDecoder extends CumulativeProtocolDecoder {

    private static final Logger log = LoggerFactory.getLogger(GameProtoDecoder.class);
    private final AttributeKey key = new AttributeKey(getClass(), "context");
    private final static int data_len = 4; // 数据长度值字节数
    private final static int ptroto_len = 2; // 协议id长度
    private Charset charset;

    public GameProtoDecoder() {
        charset = Charset.forName("utf8");
    }

    public GameProtoDecoder(Charset charset) {
        this.charset = charset;
    }

    private class ContextObject {

        public IoBuffer inBuf;
        public int length;
        public int matchCt;
        public boolean use;

        public void initContext() {
            
            this.inBuf = IoBuffer.allocate(100).setAutoExpand(true);
            length = 0;
            matchCt = 0;
            use = false;
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

        if (ctx.readOver()) {
            ctx.initContext();
        }
        return ctx;
    }

    @Override
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {

        ContextObject ctx = getContext(session);
        while (in.hasRemaining()) {
            if (!ctx.use) {
                if (in.remaining() <= data_len) {
                    return true;
                }

                ctx.length = in.getInt() - data_len;
                if (ctx.length < ptroto_len) {
                    log.error("数据长度不能小于6");
                    return true;
                }
                ctx.use = true;
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
