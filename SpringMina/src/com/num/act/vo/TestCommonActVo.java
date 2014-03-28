/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.act.vo;

import java.nio.ByteBuffer;

/**
 *
 * @author Administrator
 */
public class TestCommonActVo extends CommonAbstActVo {

    private static final byte version = 1;
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

    @Override
    public int save(ByteBuffer buf) {
        writer(buf, version);
        writer(buf, (short) day);
        writer(buf, (byte) count);
        writer(buf, say);
        return buf.position();
    }

    @Override
    public void read(byte[] data) {
        ByteBuffer buf = ByteBuffer.wrap(data);
        int v = readByte(buf);
        switch (v) {
            case 1:
                read1(buf);
            default:
                System.out.println("version = " + v + " 没有对应的读取函数！！！class" + this.getClass().getName());
        }
    }

    private void read1(ByteBuffer buf) {
        this.day = readShort(buf);
        this.count = readByte(buf);
        this.say = readString(buf);
    }
}
