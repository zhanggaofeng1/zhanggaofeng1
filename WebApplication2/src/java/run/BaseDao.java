/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import java.util.List;
import org.hibernate.Session;
import tab.Student;

/**
 *
 * @author Administrator
 */
public class BaseDao {

    private static BaseDao baseDao = new BaseDao();

    private BaseDao() {
    }

    public static BaseDao getInstance() {
        return baseDao;
    }

    public void simpleDbOper() {
        
        Session session = HibernateUtil.getCurrentSession();
        Student stu = (Student) session.get(Student.class, 4);
        stu.setStuName("zhanggaofeng");
    }
    
}
