/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clienttest;

import java.nio.charset.Charset;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 *
 * @author Administrator
 */
public class GameProtoCodecFactory implements ProtocolCodecFactory{

    private final ProtocolDecoder decoder;
    private final ProtocolEncoder encoder;
    
    public GameProtoCodecFactory() {
        this("utf8");
    }
    
    public GameProtoCodecFactory(String charset) {
        this.decoder = new GameProtoDecoder(Charset.forName(charset));
        this.encoder = new GameProtoEncoder(Charset.forName(charset));
    }
    @Override
    public ProtocolEncoder getEncoder(IoSession is) throws Exception {
        return encoder;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession is) throws Exception {
        return decoder;
    }
    
}
