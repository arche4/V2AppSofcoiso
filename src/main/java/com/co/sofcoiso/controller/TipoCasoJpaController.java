/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.sofcoiso.controller;

import com.co.sofcoiso.controller.exceptions.NonexistentEntityException;
import com.co.sofcoiso.controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.co.sofcoiso.modelo.Caso;
import com.co.sofcoiso.modelo.TipoCaso;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author manue
 */
public class TipoCasoJpaController implements Serializable {

    public TipoCasoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoCaso tipoCaso) throws PreexistingEntityException, Exception {
        if (tipoCaso.getCasoCollection() == null) {
            tipoCaso.setCasoCollection(new ArrayList<Caso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Caso> attachedCasoCollection = new ArrayList<Caso>();
            for (Caso casoCollectionCasoToAttach : tipoCaso.getCasoCollection()) {
                casoCollectionCasoToAttach = em.getReference(casoCollectionCasoToAttach.getClass(), casoCollectionCasoToAttach.getCodigocaso());
                attachedCasoCollection.add(casoCollectionCasoToAttach);
            }
            tipoCaso.setCasoCollection(attachedCasoCollection);
            em.persist(tipoCaso);
            for (Caso casoCollectionCaso : tipoCaso.getCasoCollection()) {
                casoCollectionCaso.getTipoCasoCollection().add(tipoCaso);
                casoCollectionCaso = em.merge(casoCollectionCaso);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoCaso(tipoCaso.getCodigoTipoCaso()) != null) {
                throw new PreexistingEntityException("TipoCaso " + tipoCaso + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoCaso tipoCaso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoCaso persistentTipoCaso = em.find(TipoCaso.class, tipoCaso.getCodigoTipoCaso());
            Collection<Caso> casoCollectionOld = persistentTipoCaso.getCasoCollection();
            Collection<Caso> casoCollectionNew = tipoCaso.getCasoCollection();
            Collection<Caso> attachedCasoCollectionNew = new ArrayList<Caso>();
            for (Caso casoCollectionNewCasoToAttach : casoCollectionNew) {
                casoCollectionNewCasoToAttach = em.getReference(casoCollectionNewCasoToAttach.getClass(), casoCollectionNewCasoToAttach.getCodigocaso());
                attachedCasoCollectionNew.add(casoCollectionNewCasoToAttach);
            }
            casoCollectionNew = attachedCasoCollectionNew;
            tipoCaso.setCasoCollection(casoCollectionNew);
            tipoCaso = em.merge(tipoCaso);
            for (Caso casoCollectionOldCaso : casoCollectionOld) {
                if (!casoCollectionNew.contains(casoCollectionOldCaso)) {
                    casoCollectionOldCaso.getTipoCasoCollection().remove(tipoCaso);
                    casoCollectionOldCaso = em.merge(casoCollectionOldCaso);
                }
            }
            for (Caso casoCollectionNewCaso : casoCollectionNew) {
                if (!casoCollectionOld.contains(casoCollectionNewCaso)) {
                    casoCollectionNewCaso.getTipoCasoCollection().add(tipoCaso);
                    casoCollectionNewCaso = em.merge(casoCollectionNewCaso);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = tipoCaso.getCodigoTipoCaso();
                if (findTipoCaso(id) == null) {
                    throw new NonexistentEntityException("The tipoCaso with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoCaso tipoCaso;
            try {
                tipoCaso = em.getReference(TipoCaso.class, id);
                tipoCaso.getCodigoTipoCaso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoCaso with id " + id + " no longer exists.", enfe);
            }
            Collection<Caso> casoCollection = tipoCaso.getCasoCollection();
            for (Caso casoCollectionCaso : casoCollection) {
                casoCollectionCaso.getTipoCasoCollection().remove(tipoCaso);
                casoCollectionCaso = em.merge(casoCollectionCaso);
            }
            em.remove(tipoCaso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoCaso> findTipoCasoEntities() {
        return findTipoCasoEntities(true, -1, -1);
    }

    public List<TipoCaso> findTipoCasoEntities(int maxResults, int firstResult) {
        return findTipoCasoEntities(false, maxResults, firstResult);
    }

    private List<TipoCaso> findTipoCasoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoCaso.class));
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

    public TipoCaso findTipoCaso(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoCaso.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoCasoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoCaso> rt = cq.from(TipoCaso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
