/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.utils;

/**
 *
 * @author Administrator
 */
public class JsonBuilder {
    
    private String result = "";
    public JsonBuilder(String clazzName) {
        result += clazzName + "[";
    }
    
    public void addParams(String key, String value) {
        result += key + "=" + value + ",";
    }
}
