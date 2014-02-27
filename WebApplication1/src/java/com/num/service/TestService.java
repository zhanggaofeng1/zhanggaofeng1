/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.service;

import com.num.common.Student;
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
    
    public List<Student> showLvInfo() {
        return testDao.showLvInfo();
    }
}
