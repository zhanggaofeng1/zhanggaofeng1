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
public abstract class CommonAbstActVo extends AbstActVo {

    protected void writer(ByteBuffer buf, byte data) {
        buf.put(data);
    }

    protected byte readByte(ByteBuffer buf) {
        return buf.get();
    }

    protected void writer(ByteBuffer buf, short data) {
        buf.putShort(data);
    }

    protected Short readShort(ByteBuffer buf) {
        return buf.getShort();
    }

    protected void writer(ByteBuffer buf, int data) {
        buf.putInt(data);
    }

    protected int readInt(ByteBuffer buf) {
        return buf.getInt();
    }

    protected void writer(ByteBuffer buf, char data) {
        buf.putChar(data);
    }

    protected char readChar(ByteBuffer buf) {
        return buf.getChar();
    }

    protected void writer(ByteBuffer buf, String data) {
        short strLen = (short) data.length();
        writer(buf, strLen);
        for (int index = 0; index < strLen; index++) {
            writer(buf, data.charAt(index));
        }
    }

    protected String readString(ByteBuffer buf) {
        short strLen = readShort(buf);
        char[] charArr = new char[strLen];
        for (int index = 0; index < strLen; index++) {
            charArr[index] = readChar(buf);
        }
        return new String(charArr);
    }

    public abstract int save(ByteBuffer buf);

    public abstract void read(byte[] data);
}
