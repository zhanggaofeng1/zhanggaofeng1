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
public abstract class AbstTab {
    
    protected int keyId;// 数据行的唯一性标示符
    
    public abstract void initData(TabUtil tab);
    
    public int getKey() {
        return keyId;
    }
    
    protected void setKey(int mapKeyId) {
        this.keyId = mapKeyId;
    }
}
