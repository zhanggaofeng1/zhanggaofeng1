/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.service;

import com.num.common.CdLv;
import com.num.dao.TestDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class TestService {
    
    @Autowired
    private TestDao testDao;
    
    public List<CdLv> showLvInfo(String lv) {
        Integer level = 0;
        if (lv != null) {
            level = Integer.valueOf(lv);
        }
        return testDao.showLvInfo(level);
    }
}
