package com.fpt.edu.services;


import com.fpt.edu.dao.UserRepository;
import com.fpt.edu.entities.Event;
import com.fpt.edu.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServices {

    @Autowired
    UserRepository userRepository;



    public UserServices(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Autowired
    private EntityManagerFactory emf;


    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public boolean create(User user) throws Exception {
        if (user.getEventList() == null) {
            user.setEventList(new ArrayList<Event>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Event> attachedEventList = new ArrayList<Event>();
            for (Event eventListEventToAttach : user.getEventList()) {
                eventListEventToAttach = em.getReference(eventListEventToAttach.getClass(), eventListEventToAttach.getId());
                attachedEventList.add(eventListEventToAttach);
            }
            user.setEventList(attachedEventList);
            em.persist(user);
            for (Event eventListEvent : user.getEventList()) {
                eventListEvent.getUserList().add(user);
                eventListEvent = em.merge(eventListEvent);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUser(user.getUserName()) != null) {
               return false;
            }
           return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return true;
    }

    public User findUser(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    public List<User> findUserEntities() {
        return findUserEntities(true, -1, -1);
    }

    public List<User> findUserEntities(int maxResults, int firstResult) {
        return findUserEntities(false, maxResults, firstResult);
    }

    private List<User> findUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(User.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    /* USING REPOSITORY PARTERN*/
    public List<User> getListUser() {

        return (List<User>) userRepository.findAll();

    }


}
