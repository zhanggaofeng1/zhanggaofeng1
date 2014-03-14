/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.mina.kalive;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;

/**
 *
 * @author Administrator
 */
public class KeepAliveExceptionHandler implements KeepAliveRequestTimeoutHandler{

    @Override
    public void keepAliveRequestTimedOut(KeepAliveFilter kaf, IoSession is) throws Exception {
    }
    
}
