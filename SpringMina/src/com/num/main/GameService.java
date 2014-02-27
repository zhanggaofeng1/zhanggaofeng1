/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.num.config.Configs;
import com.num.shutdown.service.ShutdownService;
import com.num.start.service.StartService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Administrator
 */
public class GameService {

    @Autowired
    private StartService startService;
    @Autowired
    private ShutdownService shutdownService;

    public static void main(String... args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(Configs.spring_cfg_path);
        GameService gameService = context.getBean(GameService.class);
        gameService.startGame();
        gameService.shutdownGame();
    }

    // 启动服务器调用
    public void startGame() {
        startService.startGame();
    }
    
    // 关闭服务器调用
    public void shutdownGame() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                shutdownService.shutdown();
            }
        }));
    }
    
}
