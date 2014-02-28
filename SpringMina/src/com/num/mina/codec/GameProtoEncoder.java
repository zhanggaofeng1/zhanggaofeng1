/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.mina.codec;

import java.nio.charset.Charset;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 *
 * @author Administrator
 */
public class GameProtoEncoder extends ProtocolEncoderAdapter{

    private Charset charset;

    public GameProtoEncoder() {
        charset = Charset.forName("utf8");
    }

    public GameProtoEncoder(Charset charset) {
        this.charset = charset;
    }

    @Override
    public void encode(IoSession is, Object o, ProtocolEncoderOutput peo) throws Exception {
        
    }
}
