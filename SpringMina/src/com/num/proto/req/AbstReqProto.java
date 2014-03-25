/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.proto.req;

import com.num.mina.vo.GsSession;
import com.num.player.vo.Player;
import com.num.proto.CommonProto;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

/**
 *
 * @author Administrator
 */
public abstract class AbstReqProto extends CommonProto{

    private IoSession session;
    public AbstReqProto(short protoId) {
        super(protoId);
    }
    
    public abstract void reader(IoBuffer buf);
    public abstract void req_handler();
    public void init() {
    }

    public void setSession(IoSession session) {
        this.session = session;
    }
    
    public Player getPlayer() {
        Object player = session.getAttribute(GsSession.playerKey);
        if (player == null) {
            return null;
        }
        return (Player) player;
    }
    
    public GsSession getGsSession() {
        if (session == null) {
            return null;
        }
        return new GsSession(session);
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
