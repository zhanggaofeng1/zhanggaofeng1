/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.dao;

import com.num.common.HibernateService;
import com.num.tab.Student;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public class SchoolDao {
    
    @Autowired
    private HibernateService hibernateService;
    
    public List<Student> showSchoolInfo() {
        Session session = hibernateService.getCurrentSession();
        return (List<Student>)session.createQuery("from Student").list();
    }
    
}
