/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.main.service;

import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class ShutdownService {
    
    public void shutdown() {
        System.out.println("***************** end **********************");
    }
    
}