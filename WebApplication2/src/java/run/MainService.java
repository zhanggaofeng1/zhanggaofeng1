/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tab.Student;

/**
 *
 * @author Administrator
 */
public class MainService {

    public static void main(String[] args) {
        Session session = HibernateUtil.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        List<Student> stus = session.createQuery("from Student as stu where stu.teaId = :tea_id")
                .setInteger("tea_id", 201)
                .setFirstResult(0)
                .setMaxResults(2)
                .list();

        for (Student stu : stus) {
            System.out.println(stu.getStuName());
        }
        transaction.commit();
    }
}
