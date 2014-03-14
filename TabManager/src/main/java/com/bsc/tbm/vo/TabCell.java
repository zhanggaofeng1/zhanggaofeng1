/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsc.tbm.vo;

/**
 *
 * @author Administrator
 */
public class TabCell {
    
    private boolean key;
    private String title;
    private String type;
    private String value;

    public TabCell(boolean key, String title, String type, String value) {
        this.title = title;
        this.type = type;
        this.value = value;
        this.key = key;
    }
    
    public boolean isKey() {
        return key;
    }
    
    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
