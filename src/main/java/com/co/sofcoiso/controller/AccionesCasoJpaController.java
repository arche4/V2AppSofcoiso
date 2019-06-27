/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.sofcoiso.controller;

import com.co.sofcoiso.controller.exceptions.NonexistentEntityException;
import com.co.sofcoiso.controller.exceptions.PreexistingEntityException;
import com.co.sofcoiso.modelo.AccionesCaso;
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
public class AccionesCasoJpaController implements Serializable {

    public AccionesCasoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AccionesCaso accionesCaso) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(accionesCaso);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAccionesCaso(accionesCaso.getCodigoCaso()) != null) {
                throw new PreexistingEntityException("AccionesCaso " + accionesCaso + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AccionesCaso accionesCaso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            accionesCaso = em.merge(accionesCaso);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = accionesCaso.getCodigoCaso();
                if (findAccionesCaso(id) == null) {
                    throw new NonexistentEntityException("The accionesCaso with id " + id + " no longer exists.");
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
            AccionesCaso accionesCaso;
            try {
                accionesCaso = em.getReference(AccionesCaso.class, id);
                accionesCaso.getCodigoCaso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The accionesCaso with id " + id + " no longer exists.", enfe);
            }
            em.remove(accionesCaso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AccionesCaso> findAccionesCasoEntities() {
        return findAccionesCasoEntities(true, -1, -1);
    }

    public List<AccionesCaso> findAccionesCasoEntities(int maxResults, int firstResult) {
        return findAccionesCasoEntities(false, maxResults, firstResult);
    }

    private List<AccionesCaso> findAccionesCasoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AccionesCaso.class));
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

    public AccionesCaso findAccionesCaso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AccionesCaso.class, id);
        } finally {
            em.close();
        }
    }

    public int getAccionesCasoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AccionesCaso> rt = cq.from(AccionesCaso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
