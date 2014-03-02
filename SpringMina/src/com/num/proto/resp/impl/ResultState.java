/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.proto.resp.impl;

import com.num.proto.resp.AbstResp;

/**
 *
 * @author Administrator
 */
public class ResultState extends AbstResp{
    
    private int state;
    
    public ResultState(int state){
        this.state = state;
    }

    @Override
    public void writer() {
        this.writeInt(buf, state);
    }
}
