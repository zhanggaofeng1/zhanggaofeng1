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

    public List<Student> showSchoolInfo1() {
        Session session = hibernateService.getOpenSession();
        return (List<Student>) session.createQuery("from Student").list();
    }

    public List<Student> showSchoolInfo2() {
        Session session = hibernateService.getCurrentSession();
        Student stu = new Student();
        stu.setStuId(5);
        stu.setStuName("dddd111");
        stu.setStuTel("ddddd111");
        stu.setTeaId(1);
        session.save(stu);
        return session.createQuery("from Student").list();
    }
}
