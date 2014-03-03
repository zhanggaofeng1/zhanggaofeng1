/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.num.config.Configs;
import com.num.main.service.ShutdownService;
import com.num.main.service.StartService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Administrator
 */
public class GameService {

     private static final org.slf4j.Logger log = LoggerFactory.getLogger(StartService.class);
    @Autowired
    private StartService startService;
    @Autowired
    private ShutdownService shutdownService;

    static {
        // 启动载入log4j配置文件
        try {
            Properties props = new Properties();
            props.load(new FileInputStream(Configs.log4j_cfg_path));
            PropertyConfigurator.configure(props);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        
    }

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
