/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.async.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class AsyncHandlerService {
    
    
    public void test() {
    
        async_test();
    }
    
    @Async
    public void async_test() {
        
    }
}
