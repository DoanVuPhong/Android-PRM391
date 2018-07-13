/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.Leaf;
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

            System.out.println("Save OK");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
      public boolean updateUser(User user) {
        try {
            SessionFactory fatory = HibernateUtil.getSessionFactory();
            Session session = fatory.openSession();
            Transaction t = session.beginTransaction();
            session.update(user);
            t.commit();
            session.close();

            System.out.println("Save OK");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
      public boolean deleteUser(User user) {
        try {
            SessionFactory fatory = HibernateUtil.getSessionFactory();
            Session session = fatory.openSession();
            Transaction t = session.beginTransaction();
            session.delete(user);
            t.commit();
            session.close();

            System.out.println("Save OK");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    

    public static List<Leaf> getAllUser() {
        ArrayList<User> list = new ArrayList();
        ArrayList result = new ArrayList();
        try {
            SessionFactory fatory = HibernateUtil.getSessionFactory();
            Session session = fatory.openSession();
            Transaction t = session.beginTransaction();
            list = (ArrayList) session.createQuery("From User").list();

            t.commit();
            session.close();

            System.out.println("Get list OK");

            for (int i = 0; i < list.size(); i++) {
                Leaf l = new Leaf(list.get(i).getUsername(), list.get(i).getRole(), list.get(i).getPassword(), list.get(i).getFullname());
                l.setLeaf(true);
                result.add(l);
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

        return result;

    }

}
