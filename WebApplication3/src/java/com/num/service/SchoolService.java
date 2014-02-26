/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.service;

import com.num.dao.SchoolDao;
import com.num.tab.Student;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class SchoolService {
    
    @Autowired
    private SchoolDao schoolDao;
    
    public List<Student> showStudentInfo() {
        return schoolDao.showSchoolInfo1();
    }
}
