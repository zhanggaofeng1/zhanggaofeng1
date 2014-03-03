/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.mina.util;

import org.apache.mina.core.session.IoSession;

/**
 *
 * @author Administrator
 */
public class GsSession {
    private IoSession session;

    public GsSession(IoSession session) {
        this.session = session;
    }
    
    public void addAttr(Object key, Object value) {
        session.setAttribute(key, value);
    }
    
    public void sendMessage(Object obj) {
        try {
            session.getHandler().messageSent(session, obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
