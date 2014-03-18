/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.config;

/**
 *
 * @author Administrator
 */
public class ConfigBean {
    
    public static final String spring_cfg_path = "/conf/spring_context.xml";
    public static final String log4j_cfg_path = "src/log4j_props/log4j.properties";
    public int minaIdle = 30;
    public int minaWeTimeout = 10;
    public boolean minaRdOper = true;
    public int corePoolSize = 200;
    public int maxPoolSize = 250;

    public int getMinaIdle() {
        return minaIdle;
    }

    public void setMinaIdle(int minaIdle) {
        this.minaIdle = minaIdle;
    }

    public int getMinaWeTimeout() {
        return minaWeTimeout;
    }

    public void setMinaWeTimeout(int minaWeTimeout) {
        this.minaWeTimeout = minaWeTimeout;
    }

    public boolean isMinaRdOper() {
        return minaRdOper;
    }

    public void setMinaRdOper(boolean minaRdOper) {
        this.minaRdOper = minaRdOper;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }
    
}
