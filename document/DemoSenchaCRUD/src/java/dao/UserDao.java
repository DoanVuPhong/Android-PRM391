/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtil;

/**
 *
 * @author pdoan4
 */
public class UserDao {

    public boolean AddUser(User user) {
        try {
            SessionFactory fatory = HibernateUtil.getSessionFactory();
            Session session = fatory.openSession();
            Transaction t = session.beginTransaction();
            session.save(user);
            t.commit();
            session.close();
            fatory.close();
            System.out.println("Save OK");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static List<User> getAllUser() {
        ArrayList list = new ArrayList();
       
         try {
            SessionFactory fatory = HibernateUtil.getSessionFactory();
            Session session = fatory.openSession();
            Transaction t = session.beginTransaction();
            list= (ArrayList) session.createQuery("select * from user").list();
            t.commit();
            session.close();
            fatory.close();
            System.out.println("Get list OK");
        } catch (Exception e) {


        }
        
        
        return list;

    }

}
