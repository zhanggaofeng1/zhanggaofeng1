/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.proto.resp;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

/**
 *
 * @author Administrator
 */
public abstract class AbstResp {

    protected IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true);
    public abstract void writer();
    public void sendMsg(IoSession session) {
        session.write(buf.buf());
    } 
    
    protected void writeByte(IoBuffer buf, int value) {
        buf.put((byte)value);
    }
    
    protected void writeShort(IoBuffer buf, int value) {
        buf.putShort((short)value);
    }
    
    protected void writeInt(IoBuffer buf, int value) {
        buf.putInt(value);
    }
    
    protected void writeFloat(IoBuffer buf, float value) {
        buf.putFloat(value);
    }
    
    protected void writeDouble(IoBuffer buf, double value) {
        buf.putDouble(value);
    }
    
    protected void writeChar(IoBuffer buf, char value) {
        buf.putChar(value);
    }
    
    protected void writeString(IoBuffer buf, String value) {
        short len = (short)value.length();
        this.writeShort(buf, len);
        for (int i = 0; i < len; i++) {
            this.writeChar(buf, value.charAt(i));
        }
    }
}
