/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.mina.kalive;

import com.num.main.service.StartService;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Administrator
 */
public class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {

    private static final Logger log = LoggerFactory.getLogger(StartService.class);
    private static final byte int_req = -1;
    private static final IoBuffer KAMSG_REQ = IoBuffer.wrap(new byte[]{int_req});

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
        log.debug(" ##################### rec keep-alive from client result = " + result);
        return result;
    }

    @Override
    public Object getResponse(IoSession is, Object o) {
        return KAMSG_REQ.duplicate();// 起到copy作用
    }

    @Override
    public boolean isResponse(IoSession is, Object o) {
        return false;
    }

    @Override
    public Object getRequest(IoSession is) {
        return null;
    }
}
