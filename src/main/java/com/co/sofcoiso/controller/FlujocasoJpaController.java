/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.sofcoiso.controller;

import com.co.sofcoiso.Servlet.exceptions.NonexistentEntityException;
import com.co.sofcoiso.Servlet.exceptions.PreexistingEntityException;
import com.co.sofcoiso.modelo.Flujocaso;
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
public class FlujocasoJpaController implements Serializable {

    public FlujocasoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Flujocaso flujocaso) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(flujocaso);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFlujocaso(flujocaso.getCodigocaso()) != null) {
                throw new PreexistingEntityException("Flujocaso " + flujocaso + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Flujocaso flujocaso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            flujocaso = em.merge(flujocaso);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = flujocaso.getCodigocaso();
                if (findFlujocaso(id) == null) {
                    throw new NonexistentEntityException("The flujocaso with id " + id + " no longer exists.");
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
            Flujocaso flujocaso;
            try {
                flujocaso = em.getReference(Flujocaso.class, id);
                flujocaso.getCodigocaso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The flujocaso with id " + id + " no longer exists.", enfe);
            }
            em.remove(flujocaso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Flujocaso> findFlujocasoEntities() {
        return findFlujocasoEntities(true, -1, -1);
    }

    public List<Flujocaso> findFlujocasoEntities(int maxResults, int firstResult) {
        return findFlujocasoEntities(false, maxResults, firstResult);
    }

    private List<Flujocaso> findFlujocasoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Flujocaso.class));
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

    public Flujocaso findFlujocaso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Flujocaso.class, id);
        } finally {
            em.close();
        }
    }

    public int getFlujocasoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Flujocaso> rt = cq.from(Flujocaso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
