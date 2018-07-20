/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javaapplication1.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author pdoan4
 */
public class EventJpaController implements Serializable {

    public EventJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Event event) {
        if (event.getUserCollection() == null) {
            event.setUserCollection(new ArrayList<User>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<User> attachedUserCollection = new ArrayList<User>();
            for (User userCollectionUserToAttach : event.getUserCollection()) {
                userCollectionUserToAttach = em.getReference(userCollectionUserToAttach.getClass(), userCollectionUserToAttach.getUserName());
                attachedUserCollection.add(userCollectionUserToAttach);
            }
            event.setUserCollection(attachedUserCollection);
            em.persist(event);
            for (User userCollectionUser : event.getUserCollection()) {
                userCollectionUser.getEventCollection().add(event);
                userCollectionUser = em.merge(userCollectionUser);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Event event) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Event persistentEvent = em.find(Event.class, event.getId());
            Collection<User> userCollectionOld = persistentEvent.getUserCollection();
            Collection<User> userCollectionNew = event.getUserCollection();
            Collection<User> attachedUserCollectionNew = new ArrayList<User>();
            for (User userCollectionNewUserToAttach : userCollectionNew) {
                userCollectionNewUserToAttach = em.getReference(userCollectionNewUserToAttach.getClass(), userCollectionNewUserToAttach.getUserName());
                attachedUserCollectionNew.add(userCollectionNewUserToAttach);
            }
            userCollectionNew = attachedUserCollectionNew;
            event.setUserCollection(userCollectionNew);
            event = em.merge(event);
            for (User userCollectionOldUser : userCollectionOld) {
                if (!userCollectionNew.contains(userCollectionOldUser)) {
                    userCollectionOldUser.getEventCollection().remove(event);
                    userCollectionOldUser = em.merge(userCollectionOldUser);
                }
            }
            for (User userCollectionNewUser : userCollectionNew) {
                if (!userCollectionOld.contains(userCollectionNewUser)) {
                    userCollectionNewUser.getEventCollection().add(event);
                    userCollectionNewUser = em.merge(userCollectionNewUser);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = event.getId();
                if (findEvent(id) == null) {
                    throw new NonexistentEntityException("The event with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Event event;
            try {
                event = em.getReference(Event.class, id);
                event.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The event with id " + id + " no longer exists.", enfe);
            }
            Collection<User> userCollection = event.getUserCollection();
            for (User userCollectionUser : userCollection) {
                userCollectionUser.getEventCollection().remove(event);
                userCollectionUser = em.merge(userCollectionUser);
            }
            em.remove(event);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Event> findEventEntities() {
        return findEventEntities(true, -1, -1);
    }

    public List<Event> findEventEntities(int maxResults, int firstResult) {
        return findEventEntities(false, maxResults, firstResult);
    }

    private List<Event> findEventEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Event.class));
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

    public Event findEvent(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Event.class, id);
        } finally {
            em.close();
        }
    }

    public int getEventCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Event> rt = cq.from(Event.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
