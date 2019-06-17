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
import com.co.sofcoiso.modelo.EstadoCaso;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author manue
 */
public class EstadoCasoJpaController implements Serializable {

    public EstadoCasoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EstadoCaso estadoCaso) throws PreexistingEntityException, Exception {
        if (estadoCaso.getCasoCollection() == null) {
            estadoCaso.setCasoCollection(new ArrayList<Caso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Caso> attachedCasoCollection = new ArrayList<Caso>();
            for (Caso casoCollectionCasoToAttach : estadoCaso.getCasoCollection()) {
                casoCollectionCasoToAttach = em.getReference(casoCollectionCasoToAttach.getClass(), casoCollectionCasoToAttach.getCodigocaso());
                attachedCasoCollection.add(casoCollectionCasoToAttach);
            }
            estadoCaso.setCasoCollection(attachedCasoCollection);
            em.persist(estadoCaso);
            for (Caso casoCollectionCaso : estadoCaso.getCasoCollection()) {
                casoCollectionCaso.getEstadoCasoCollection().add(estadoCaso);
                casoCollectionCaso = em.merge(casoCollectionCaso);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadoCaso(estadoCaso.getCodigoestado()) != null) {
                throw new PreexistingEntityException("EstadoCaso " + estadoCaso + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EstadoCaso estadoCaso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EstadoCaso persistentEstadoCaso = em.find(EstadoCaso.class, estadoCaso.getCodigoestado());
            Collection<Caso> casoCollectionOld = persistentEstadoCaso.getCasoCollection();
            Collection<Caso> casoCollectionNew = estadoCaso.getCasoCollection();
            Collection<Caso> attachedCasoCollectionNew = new ArrayList<Caso>();
            for (Caso casoCollectionNewCasoToAttach : casoCollectionNew) {
                casoCollectionNewCasoToAttach = em.getReference(casoCollectionNewCasoToAttach.getClass(), casoCollectionNewCasoToAttach.getCodigocaso());
                attachedCasoCollectionNew.add(casoCollectionNewCasoToAttach);
            }
            casoCollectionNew = attachedCasoCollectionNew;
            estadoCaso.setCasoCollection(casoCollectionNew);
            estadoCaso = em.merge(estadoCaso);
            for (Caso casoCollectionOldCaso : casoCollectionOld) {
                if (!casoCollectionNew.contains(casoCollectionOldCaso)) {
                    casoCollectionOldCaso.getEstadoCasoCollection().remove(estadoCaso);
                    casoCollectionOldCaso = em.merge(casoCollectionOldCaso);
                }
            }
            for (Caso casoCollectionNewCaso : casoCollectionNew) {
                if (!casoCollectionOld.contains(casoCollectionNewCaso)) {
                    casoCollectionNewCaso.getEstadoCasoCollection().add(estadoCaso);
                    casoCollectionNewCaso = em.merge(casoCollectionNewCaso);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estadoCaso.getCodigoestado();
                if (findEstadoCaso(id) == null) {
                    throw new NonexistentEntityException("The estadoCaso with id " + id + " no longer exists.");
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
            EstadoCaso estadoCaso;
            try {
                estadoCaso = em.getReference(EstadoCaso.class, id);
                estadoCaso.getCodigoestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadoCaso with id " + id + " no longer exists.", enfe);
            }
            Collection<Caso> casoCollection = estadoCaso.getCasoCollection();
            for (Caso casoCollectionCaso : casoCollection) {
                casoCollectionCaso.getEstadoCasoCollection().remove(estadoCaso);
                casoCollectionCaso = em.merge(casoCollectionCaso);
            }
            em.remove(estadoCaso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EstadoCaso> findEstadoCasoEntities() {
        return findEstadoCasoEntities(true, -1, -1);
    }

    public List<EstadoCaso> findEstadoCasoEntities(int maxResults, int firstResult) {
        return findEstadoCasoEntities(false, maxResults, firstResult);
    }

    private List<EstadoCaso> findEstadoCasoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EstadoCaso.class));
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

    public EstadoCaso findEstadoCaso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EstadoCaso.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoCasoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EstadoCaso> rt = cq.from(EstadoCaso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
