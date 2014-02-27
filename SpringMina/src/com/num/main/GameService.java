/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.num.config.Configs;
import com.num.service.ShutdownService;

/**
 *
 * @author Administrator
 */
public class GameService {
    
    public static void main(String... args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(Configs.spring_cfg_path);
        GameService gameService = context.getBean(GameService.class);
        gameService.startGame();
        gameService.endGame(context);
    }
    
    public void startGame() {
    
    }
    
    public void endGame(final ApplicationContext context) {
        Runtime rt = Runtime.getRuntime();
        rt.addShutdownHook(new Thread(new ShutdownOper(context)));
    }
    
    public class ShutdownOper implements Runnable{
        
        private ApplicationContext context;
        
        public ShutdownOper(final ApplicationContext context) {
            this.context = context;
        }
        
        @Override
        public void run() {
            ShutdownService shutService = context.getBean(ShutdownService.class);
            shutService.shutdown();
        }
    }
}
