/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.proto;

import org.springframework.context.ApplicationContext;

/**
 *
 * @author Administrator
 */
public class CommonProto {
    
    private short protoId;
    private ApplicationContext context;

    public CommonProto(short protoId) {
        this.protoId = protoId;
    }
    
    protected ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }
    
    public short getProtoId() {
        return protoId;
    }
}
