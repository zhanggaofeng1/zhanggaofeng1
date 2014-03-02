/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.proto.req;

import com.num.player.vo.Player;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author Administrator
 */
public abstract class AbstReqProto {

    private Player player;
    private ApplicationContext context;
    private IoSession session;
    
    public abstract void reader(IoBuffer buf);
    public abstract void req_handler();
    
    public void init(IoSession session, Player player, ApplicationContext context) {
        this.session = session;
        this.player = player;
        this.context = context;
    }
    
    public IoSession getIoSessioin() {
        return session;
    }
    
    public ApplicationContext getContext() {
        return context;
    }
    
    public Player getPlayer() {
        return player;
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
