/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.sofcoiso.controller;

import com.co.sofcoiso.controller.exceptions.IllegalOrphanException;
import com.co.sofcoiso.controller.exceptions.NonexistentEntityException;
import com.co.sofcoiso.controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.co.sofcoiso.modelo.Caso;
import com.co.sofcoiso.modelo.CasoAcciones;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author manue
 */
public class CasoAccionesJpaController implements Serializable {

    public CasoAccionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CasoAcciones casoAcciones) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Caso casoOrphanCheck = casoAcciones.getCaso();
        if (casoOrphanCheck != null) {
            CasoAcciones oldCasoAccionesOfCaso = casoOrphanCheck.getCasoAcciones();
            if (oldCasoAccionesOfCaso != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Caso " + casoOrphanCheck + " already has an item of type CasoAcciones whose caso column cannot be null. Please make another selection for the caso field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Caso caso = casoAcciones.getCaso();
            if (caso != null) {
                caso = em.getReference(caso.getClass(), caso.getCodigocaso());
                casoAcciones.setCaso(caso);
            }
            em.persist(casoAcciones);
            if (caso != null) {
                caso.setCasoAcciones(casoAcciones);
                caso = em.merge(caso);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCasoAcciones(casoAcciones.getCasoCodigocaso()) != null) {
                throw new PreexistingEntityException("CasoAcciones " + casoAcciones + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CasoAcciones casoAcciones) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CasoAcciones persistentCasoAcciones = em.find(CasoAcciones.class, casoAcciones.getCasoCodigocaso());
            Caso casoOld = persistentCasoAcciones.getCaso();
            Caso casoNew = casoAcciones.getCaso();
            List<String> illegalOrphanMessages = null;
            if (casoNew != null && !casoNew.equals(casoOld)) {
                CasoAcciones oldCasoAccionesOfCaso = casoNew.getCasoAcciones();
                if (oldCasoAccionesOfCaso != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Caso " + casoNew + " already has an item of type CasoAcciones whose caso column cannot be null. Please make another selection for the caso field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (casoNew != null) {
                casoNew = em.getReference(casoNew.getClass(), casoNew.getCodigocaso());
                casoAcciones.setCaso(casoNew);
            }
            casoAcciones = em.merge(casoAcciones);
            if (casoOld != null && !casoOld.equals(casoNew)) {
                casoOld.setCasoAcciones(null);
                casoOld = em.merge(casoOld);
            }
            if (casoNew != null && !casoNew.equals(casoOld)) {
                casoNew.setCasoAcciones(casoAcciones);
                casoNew = em.merge(casoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = casoAcciones.getCasoCodigocaso();
                if (findCasoAcciones(id) == null) {
                    throw new NonexistentEntityException("The casoAcciones with id " + id + " no longer exists.");
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
            CasoAcciones casoAcciones;
            try {
                casoAcciones = em.getReference(CasoAcciones.class, id);
                casoAcciones.getCasoCodigocaso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The casoAcciones with id " + id + " no longer exists.", enfe);
            }
            Caso caso = casoAcciones.getCaso();
            if (caso != null) {
                caso.setCasoAcciones(null);
                caso = em.merge(caso);
            }
            em.remove(casoAcciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CasoAcciones> findCasoAccionesEntities() {
        return findCasoAccionesEntities(true, -1, -1);
    }

    public List<CasoAcciones> findCasoAccionesEntities(int maxResults, int firstResult) {
        return findCasoAccionesEntities(false, maxResults, firstResult);
    }

    private List<CasoAcciones> findCasoAccionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CasoAcciones.class));
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

    public CasoAcciones findCasoAcciones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CasoAcciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getCasoAccionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CasoAcciones> rt = cq.from(CasoAcciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
