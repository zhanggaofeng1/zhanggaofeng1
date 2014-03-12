/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.act.vo;

/**
 *
 * @author Administrator
 */
public abstract class AbstActVo {

    private int id;
    private int actId;

    public AbstActVo fromDbInit(int id, int actId) {
        this.id = id;
        this.actId = actId;
        return this;
    }

    public void init() {
    }

    public int curDbId() {
        return id;
    }

    public int curActId() {
        return actId;
    }
}
