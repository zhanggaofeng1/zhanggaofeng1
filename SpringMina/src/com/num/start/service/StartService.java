/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.start.service;

import com.num.config.Configs;
import com.num.mina.handler.GameHandlerService;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class StartService {
    
    @Autowired
    private NioSocketAcceptor ioAcceptor;
    @Autowired
    private GameHandlerService minaHandler;
    @Autowired
    private LoggingFilter loggingFilter;
    @Autowired
    private ProtocolCodecFilter protoCodecFilter;
    @Autowired
    private ExecutorFilter executorFilter;
    
    public void startGame() {
        
        try {
            
            ioAcceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, Configs.game_idle_both_time);
            ioAcceptor.getSessionConfig().setUseReadOperation(Configs.game_session_read_oper);
            ioAcceptor.getSessionConfig().setWriteTimeout(Configs.game_write_timeout);
            
            ioAcceptor.getFilterChain().addLast("logger", loggingFilter);
            ioAcceptor.getFilterChain().addLast("codec", protoCodecFilter);
            ioAcceptor.getFilterChain().addLast("executor", executorFilter);
            
            ioAcceptor.setHandler(minaHandler);
            ioAcceptor.bind();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("##################服务器启动已完成########################");
    }
}
