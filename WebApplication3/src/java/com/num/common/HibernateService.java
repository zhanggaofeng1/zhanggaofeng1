/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.common;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class HibernateService {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernaTemplate() {
        return hibernateTemplate;
    }

    public Session getCurrentSession() {
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }

    public Transaction getTransaction() {
        return getCurrentSession().beginTransaction();
    }
}
