/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.act.vo;

/**
 *
 * @author Administrator
 */
public abstract class CommonActVo extends TimeManager {

    private static final int cls_day = 1;// 一天重新初始化
    private static final int cls_week = 2;// 每周重新初始化
    private static final int cls_month = 3;//每月重新初始化
    private int id;
    private int actId;
    private String date;
    private int joinCt;

    public CommonActVo init_data(int id, int actId, String date, int joinCt, String json) {
        this.id = id;
        this.actId = actId;
        this.joinCt = joinCt;
        setParamJson(json);
        return this;
    }

    public void init_method(int clsType) {

        if (cls_day == clsType) {
            init_day();
        } else if (cls_week == clsType) {
            init_week();
        } else if (cls_month == clsType) {
            init_month();
        }
    }

    private void init_day() {

        if (date != null && !date.isEmpty()) {
            String[] tms = date.split("-");
            if (Integer.parseInt(tms[0]) == getCurYear() && Integer.parseInt(tms[1]) == getCurMonth() && Integer.parseInt(tms[2]) == getCurDay()) {
                return;
            }
        }
        this.date = getCurDayStr();
        this.joinCt = 0;
        init_impl();
    }

    private void init_month() {

        if (date != null && !date.isEmpty()) {
            String[] tms = date.split("-");
            if (Integer.parseInt(tms[0]) == getCurYear() && Integer.parseInt(tms[1]) == getCurMonth()) {
                return;
            }
        }
        this.date = getCurDayStr();
        this.joinCt = 0;
        init_impl();
    }

    private void init_week() {

        if (date != null && !date.isEmpty()) {
            String[] tms = date.split("-");
            if (Integer.parseInt(tms[0]) == getCurYear()) {
                return;
            }
        }
        this.date = getCurDayStr();
        this.joinCt = 0;
        init_impl();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getJoinCt() {
        return joinCt;
    }

    public void setJoinCt(int joinCt) {
        this.joinCt = joinCt;
    }

    public int getId() {
        return id;
    }

    public int getActId() {
        return actId;
    }

    // 子类中根据需要实现的方法
    public String getParamJson() {
        return null;
    }

    protected void setParamJson(String json) {
    }

    protected void init_impl() {
    }
}
