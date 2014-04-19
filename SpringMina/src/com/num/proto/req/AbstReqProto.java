/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.proto.req;

import org.apache.mina.core.buffer.IoBuffer;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author Administrator
 */
public abstract class AbstReqProto implements Cloneable{

    protected ApplicationContext context;
    private short protoId;
    
    public AbstReqProto(short protoId) {
        this.protoId = protoId;
    }
    
    public short getProtoId() {
        return protoId;
    }
    
    public AbstReqProto clonePackage() {
        try {
            return (AbstReqProto) super.clone();
        } catch (CloneNotSupportedException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    public void setApplicationContext(ApplicationContext context) {
        this.context = context;
    }
    
    public void init() {
    }
    
    public abstract void reader(IoBuffer buf);
    public abstract void req_handler();
    
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
