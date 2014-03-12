/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.act.vo;

import javolution.util.FastMap;

/**
 *
 * @author Administrator
 */
public class LoginActVo extends AbstActVo{
    
    private int day;
    private int count;
    private String say = "Hello world";

    @Override
    public FastMap<String, Object> saveToDb(FastMap<String, Object> saveMap) {
        saveMap.put("day", day);
        saveMap.put("count", count);
        saveMap.put("say", say);
        return saveMap;
    }

    @Override
    public void loadFromDb(FastMap<String, Object> data) {
    }
    
}
