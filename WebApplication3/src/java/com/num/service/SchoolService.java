/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.service;

import com.num.common.HibernateService;
import com.num.dao.SchoolDao;
import com.num.tab.Student;
import java.util.List;
import org.hibernate.Transaction;
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
    @Autowired
    private HibernateService hibernateService;
    
    public List<Student> showStudentInfo() {
        Transaction transaction = hibernateService.getCurrentTransaction();
        try {
        return schoolDao.showSchoolInfo();
        } finally {
            hibernateService.currentCommit(transaction);
        }
    }
    
    public List<Student> setStudentInfo() {
        return null;
    }
    
    
}
