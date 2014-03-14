/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.mina.kalive;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;

/**
 *
 * @author Administrator
 */
public class KeepAliveFilterInMina extends KeepAliveFilter{
    
    public KeepAliveFilterInMina() {
        super(null, IdleStatus.READER_IDLE, KeepAliveRequestTimeoutHandler.NOOP, 1, 1);
    }
}
