/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.rmi.local.impl;

import com.num.rmi.local.IRmiService;

/**
 *
 * @author Administrator
 */
public class RemoteRmiService implements IRmiService{

    @Override
    public String returnJson() {
        return "hello world";
    }
    
}
