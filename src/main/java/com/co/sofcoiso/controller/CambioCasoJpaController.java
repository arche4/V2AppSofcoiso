/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.sofcoiso.controller;

import com.co.sofcoiso.controller.exceptions.NonexistentEntityException;
import com.co.sofcoiso.modelo.CambioCaso;
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
public class CambioCasoJpaController implements Serializable {

    public CambioCasoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CambioCaso cambioCaso) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cambioCaso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CambioCaso cambioCaso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cambioCaso = em.merge(cambioCaso);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cambioCaso.getIdCambioCaso();
                if (findCambioCaso(id) == null) {
                    throw new NonexistentEntityException("The cambioCaso with id " + id + " no longer exists.");
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
            CambioCaso cambioCaso;
            try {
                cambioCaso = em.getReference(CambioCaso.class, id);
                cambioCaso.getIdCambioCaso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cambioCaso with id " + id + " no longer exists.", enfe);
            }
            em.remove(cambioCaso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CambioCaso> findCambioCasoEntities() {
        return findCambioCasoEntities(true, -1, -1);
    }

    public List<CambioCaso> findCambioCasoEntities(int maxResults, int firstResult) {
        return findCambioCasoEntities(false, maxResults, firstResult);
    }

    private List<CambioCaso> findCambioCasoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CambioCaso.class));
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

    public CambioCaso findCambioCaso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CambioCaso.class, id);
        } finally {
            em.close();
        }
    }

    public int getCambioCasoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CambioCaso> rt = cq.from(CambioCaso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
