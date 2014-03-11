/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.act.enums;

/**
 *
 * @author Administrator
 */
public enum ActIdEnum {

    login_act_id(1000),
    ;
    private int actId;
    
    ActIdEnum(int actId) {
        this.actId = actId;
    }
    
    public int value() {
        return this.actId;
    }
}
