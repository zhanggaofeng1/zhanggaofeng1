/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.tab.vo;

import com.num.tab.utils.TabUtil;

/**
 *
 * @author Administrator
 */
public class UserInfo extends AbstTab {
    
    private int id;
    private String num;
    private String name;
    private String pwd;
    
    @Override
    public void initData(TabUtil tab) {
        
        this.id = tab.getInt("id");
        this.num = tab.getString("num");
        this.name = tab.getString("name");
        this.pwd = tab.getString("pwd");
        setMapKey(id);
    }
    
    public int getId() {
        return id;
    }
    
    public String getNum() {
        return num;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPwd() {
        return pwd;
    }
    
}
