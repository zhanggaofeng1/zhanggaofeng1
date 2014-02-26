/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.dao;

import com.num.common.HibernateService;
import com.num.tab.Student;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
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
        return hibernateService.getHibernaTemplate().find("from Student");
    }

    public List<Student> showSchoolInfo1() {
        HibernateTemplate hibernateTemplate = hibernateService.getHibernaTemplate();
        return hibernateTemplate.executeFind(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session sn) throws HibernateException, SQLException {
                return sn.createQuery("from Student").list();
            }
        });
    }

    public List<Student> showSchoolInfo2() {
        HibernateTemplate hibernateTemplate = hibernateService.getHibernaTemplate();
        return hibernateTemplate.execute(new HibernateCallback<List<Student>>(){
            @Override
            public List<Student> doInHibernate(Session sn) throws HibernateException, SQLException {
                return sn.createQuery("from Student").list();
            }
        });
    }
}
