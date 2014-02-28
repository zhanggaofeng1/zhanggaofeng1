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
    
    @Async
    public void async_test() {
    
    }
}
