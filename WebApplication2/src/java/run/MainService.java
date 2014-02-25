/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import org.hibernate.Transaction;

/**
 *
 * @author Administrator
 */
public class MainService {

    public static void main(String[] args) {
        // 在只有查找数据的逻辑中，不需要事务管理
        Transaction transaction = HibernateUtil.getCurrentSession().beginTransaction();
        BaseDao.getInstance().simpleDbOper();
        transaction.commit();// getcurrentsession,在提交时自动关闭connection链接
    }
}
