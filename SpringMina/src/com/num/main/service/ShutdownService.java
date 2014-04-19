/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.main.service;

import com.num.act.service.PlayerActService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class ShutdownService {
    @Autowired
    private PlayerActService actDataManager;
    
    public void shutdown() {
        actDataManager.saveToDbAllUserActInfo();
    }
    
}
