/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.proto.resp;

import com.num.proto.resp.AbstResp;
import org.apache.mina.core.buffer.IoBuffer;

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
    public void writer(IoBuffer buf) {
        this.writeInt(buf, state);
    }
}
