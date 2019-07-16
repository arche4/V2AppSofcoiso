/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.sofcoiso.controller;

import com.co.sofcoiso.controller.exceptions.NonexistentEntityException;
import com.co.sofcoiso.modelo.PersonaDirreccion;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author manue
 */
public class PersonaDirreccionJpaController implements Serializable {

    public PersonaDirreccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PersonaDirreccion personaDirreccion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(personaDirreccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PersonaDirreccion personaDirreccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            personaDirreccion = em.merge(personaDirreccion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = personaDirreccion.getIdDirrecion();
                if (findPersonaDirreccion(id) == null) {
                    throw new NonexistentEntityException("The personaDirreccion with id " + id + " no longer exists.");
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
            PersonaDirreccion personaDirreccion;
            try {
                personaDirreccion = em.getReference(PersonaDirreccion.class, id);
                personaDirreccion.getIdDirrecion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The personaDirreccion with id " + id + " no longer exists.", enfe);
            }
            em.remove(personaDirreccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PersonaDirreccion> findPersonaDirreccionEntities() {
        return findPersonaDirreccionEntities(true, -1, -1);
    }

    public List<PersonaDirreccion> findPersonaDirreccionEntities(int maxResults, int firstResult) {
        return findPersonaDirreccionEntities(false, maxResults, firstResult);
    }

    private List<PersonaDirreccion> findPersonaDirreccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PersonaDirreccion.class));
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

    public PersonaDirreccion findPersonaDirreccion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PersonaDirreccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonaDirreccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PersonaDirreccion> rt = cq.from(PersonaDirreccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
