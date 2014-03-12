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
public abstract class AbstActVo {

    public abstract FastMap<String, Object> saveToDb(FastMap<String, Object> saveMap);
    public abstract void loadFromDb(FastMap<String, Object> data);
    public void init() {
    }
}
