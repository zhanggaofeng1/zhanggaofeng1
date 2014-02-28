/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.mina.handler;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 *
 * @author Administrator
 */
public class GameHandlerService extends IoHandlerAdapter{
    
    @Override
    public void sessionOpened(IoSession session) throws Exception {
      
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
  
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
    
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
 
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        System.out.println("############## rec ######################");
    }
}
