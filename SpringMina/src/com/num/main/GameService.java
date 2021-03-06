/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.num.config.ConfigBean;
import com.num.main.service.ShutdownService;
import com.num.main.service.StartService;
import java.io.FileInputStream;
import java.util.Properties;
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

        try {
            Properties props = new Properties();
            props.load(new FileInputStream(ConfigBean.log4j_cfg_path));
            PropertyConfigurator.configure(props);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

    }

    public static void main(String... args) {

        ApplicationContext context = new ClassPathXmlApplicationContext(ConfigBean.spring_cfg_path);
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
