/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clienttest;

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
        
        Integer[] arr = (Integer[])o;
        IoBuffer buf = IoBuffer.allocate(10);
        buf.putInt(10);
        int id = arr[0];
        buf.putShort((short)id);
        buf.putInt(arr[1]);
        buf.flip();
        out.write(buf);
    }
}
