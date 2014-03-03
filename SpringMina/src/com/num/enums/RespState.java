/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.enums;

/**
 *
 * @author Administrator
 */
public enum RespState {

    login_error(-100),
    login_success(2),
    ;
    private int value;
    private RespState(int value) {
        this.value = value;
    }
    
    public int value() {
        return value;
    }
}
