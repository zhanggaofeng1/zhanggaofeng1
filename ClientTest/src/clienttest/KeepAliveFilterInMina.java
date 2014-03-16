/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clienttest;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;

/**
 *
 * @author Administrator
 */
public class KeepAliveFilterInMina extends KeepAliveFilter {

    public KeepAliveFilterInMina(KeepAliveMessageFactory factory, KeepAliveRequestTimeoutHandler timeoutHandler, int interval, int timeout) {
        super(factory, IdleStatus.BOTH_IDLE, timeoutHandler, interval, timeout);
        this.setForwardEvent(false);
    }
}
