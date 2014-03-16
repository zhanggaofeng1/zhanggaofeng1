/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.main.service;

import com.num.config.ConfigBean;
import com.num.mina.handler.GameHandlerService;
import com.num.mina.kalive.KeepAliveFilterInMina;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class StartService {
    
    private static final Logger log = LoggerFactory.getLogger(StartService.class);
    @Autowired
    private NioSocketAcceptor ioAcceptor;
    @Autowired
    private GameHandlerService minaHandler;
    @Autowired
    private ProtocolCodecFilter protoCodecFilter;
    @Autowired
    private KeepAliveFilterInMina kaFilter;
    @Autowired
    private ConfigBean config;
    
    public void startGame() {
        
        try {
            
            ioAcceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, config.getMinaIdle());
            ioAcceptor.getSessionConfig().setUseReadOperation(config.isMinaRdOper());
            ioAcceptor.getSessionConfig().setWriteTimeout(config.getMinaWeTimeout());
            ioAcceptor.getSessionConfig().setTcpNoDelay(true);
            ioAcceptor.setReuseAddress(true);
            
            ioAcceptor.getFilterChain().addLast("keep-alive", kaFilter);
            ioAcceptor.getFilterChain().addLast("codec", protoCodecFilter);
            ioAcceptor.getFilterChain().addLast("logger", new LoggingFilter());
            ioAcceptor.getFilterChain().addLast("executor", new ExecutorFilter(config.getCorePoolSize(), config.getMaxPoolSize()));
            ioAcceptor.setHandler(minaHandler);
            ioAcceptor.bind();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
