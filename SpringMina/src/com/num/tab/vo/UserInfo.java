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
        setKey(id);
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNum() {
        return num;
    }
    
    public void setNum(String num) {
        this.num = num;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPwd() {
        return pwd;
    }
    
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
