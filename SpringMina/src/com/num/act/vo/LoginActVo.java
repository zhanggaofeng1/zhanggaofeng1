/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.act.vo;

/**
 *
 * @author Administrator
 */
public class LoginActVo extends AbstActVo {

    private int day;
    private int count;
    private String say = "Hello world";

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSay() {
        return say;
    }

    public void setSay(String say) {
        this.say = say;
    }
}
