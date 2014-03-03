/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.proto.req;

import com.num.mina.vo.GsSession;
import org.apache.mina.core.buffer.IoBuffer;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author Administrator
 */
public abstract class AbstReqProto {

    private ApplicationContext context;
    private GsSession session;
    
    public abstract void reader(IoBuffer buf);
    public abstract void req_handler();
    
    public void init(GsSession session, ApplicationContext context) {
        this.session = session;
        this.context = context;
    }
    
    public final GsSession getGsSessioin() {
        return session;
    }
    
    public final ApplicationContext getContext() {
        return context;
    }
    
    protected int readInt(IoBuffer buf) {
        return buf.getInt();
    }

    protected int readShort(IoBuffer buf) {
        return buf.getShort();
    }

    protected long readLong(IoBuffer buf) {
        return buf.getLong();
    }

    protected char readChar(IoBuffer buf) {
        return buf.getChar();
    }

    protected String readString(IoBuffer buf) {
        short strLen = buf.getShort();
        String result = "";
        for (int i = 0; i < strLen; i++) {
            result += readChar(buf);
        }
        return result;
    }

    protected double readDouble(IoBuffer buf) {
        return buf.getDouble();
    }

    protected float readFloat(IoBuffer buf) {
        return buf.getFloat();
    }

    protected int readByte(IoBuffer buf) {
        return buf.get();
    }
}
