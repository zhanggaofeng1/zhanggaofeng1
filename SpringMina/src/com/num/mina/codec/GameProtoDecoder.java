/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.mina.codec;

import java.nio.charset.Charset;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 *
 * @author Administrator
 */
public class GameProtoDecoder extends CumulativeProtocolDecoder{

    private Charset charset;
    
    public GameProtoDecoder() {
        charset = Charset.forName("utf8");
    }
    public GameProtoDecoder(Charset charset) {
        this.charset = charset;
    }

    @Override
    protected boolean doDecode(IoSession is, IoBuffer ib, ProtocolDecoderOutput pdo) throws Exception {
        return true;
    }
    
}
