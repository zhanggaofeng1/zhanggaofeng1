/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.mina.kalive;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

/**
 *
 * @author Administrator
 */
public class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {

    private static final byte int_req = -1;
    private static final byte int_rep = -2;
    private static final IoBuffer KAMSG_REQ = IoBuffer.wrap(new byte[]{-1});
    private static final IoBuffer KAMSG_REP = IoBuffer.wrap(new byte[]{-2});

    @Override
    public boolean isRequest(IoSession is, Object o) {

        if (!(o instanceof IoBuffer)) {
            return false;
        }
        
        IoBuffer ka_req = (IoBuffer) o;
        if (ka_req.limit() != 1) {
            return false;
        }
        
        boolean result = (ka_req.get() == int_req);

        ka_req.rewind();

        return result;
    }

    @Override
    public boolean isResponse(IoSession is, Object o) {

        if (!(o instanceof IoBuffer)) {
            return false;
        }
        
        IoBuffer ka_rep = (IoBuffer)o;
        if (ka_rep.limit() != 1) {
            return false;
        }
        
        boolean result = (ka_rep.get() == -2);
        
        ka_rep.rewind();
        
        return result;
    }

    @Override
    public Object getRequest(IoSession is) {
        return KAMSG_REQ.duplicate();// 起到copy作用
    }

    @Override
    public Object getResponse(IoSession is, Object o) {
        return KAMSG_REP.duplicate();// 起到copy作用
    }
}
