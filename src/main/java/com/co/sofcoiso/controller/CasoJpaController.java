/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.sofcoiso.controller;

import com.co.sofcoiso.controller.exceptions.IllegalOrphanException;
import com.co.sofcoiso.controller.exceptions.NonexistentEntityException;
import com.co.sofcoiso.controller.exceptions.PreexistingEntityException;
import com.co.sofcoiso.modelo.Caso;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.co.sofcoiso.modelo.CasoAcciones;
import com.co.sofcoiso.modelo.Persona;
import com.co.sofcoiso.modelo.TipoCaso;
import java.util.ArrayList;
import java.util.Collection;
import com.co.sofcoiso.modelo.EstadoCaso;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author manue
 */
public class CasoJpaController implements Serializable {

    public CasoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Caso caso) throws PreexistingEntityException, Exception {
        if (caso.getTipoCasoCollection() == null) {
            caso.setTipoCasoCollection(new ArrayList<TipoCaso>());
        }
        if (caso.getEstadoCasoCollection() == null) {
            caso.setEstadoCasoCollection(new ArrayList<EstadoCaso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CasoAcciones casoAcciones = caso.getCasoAcciones();
            if (casoAcciones != null) {
                casoAcciones = em.getReference(casoAcciones.getClass(), casoAcciones.getCasoCodigocaso());
                caso.setCasoAcciones(casoAcciones);
            }
            Persona personaCedula = caso.getPersonaCedula();
            if (personaCedula != null) {
                personaCedula = em.getReference(personaCedula.getClass(), personaCedula.getCedula());
                caso.setPersonaCedula(personaCedula);
            }
            Collection<TipoCaso> attachedTipoCasoCollection = new ArrayList<TipoCaso>();
            for (TipoCaso tipoCasoCollectionTipoCasoToAttach : caso.getTipoCasoCollection()) {
                tipoCasoCollectionTipoCasoToAttach = em.getReference(tipoCasoCollectionTipoCasoToAttach.getClass(), tipoCasoCollectionTipoCasoToAttach.getCodigoTipoCaso());
                attachedTipoCasoCollection.add(tipoCasoCollectionTipoCasoToAttach);
            }
            caso.setTipoCasoCollection(attachedTipoCasoCollection);
            Collection<EstadoCaso> attachedEstadoCasoCollection = new ArrayList<EstadoCaso>();
            for (EstadoCaso estadoCasoCollectionEstadoCasoToAttach : caso.getEstadoCasoCollection()) {
                estadoCasoCollectionEstadoCasoToAttach = em.getReference(estadoCasoCollectionEstadoCasoToAttach.getClass(), estadoCasoCollectionEstadoCasoToAttach.getCodigoestado());
                attachedEstadoCasoCollection.add(estadoCasoCollectionEstadoCasoToAttach);
            }
            caso.setEstadoCasoCollection(attachedEstadoCasoCollection);
            em.persist(caso);
            if (casoAcciones != null) {
                Caso oldCasoOfCasoAcciones = casoAcciones.getCaso();
                if (oldCasoOfCasoAcciones != null) {
                    oldCasoOfCasoAcciones.setCasoAcciones(null);
                    oldCasoOfCasoAcciones = em.merge(oldCasoOfCasoAcciones);
                }
                casoAcciones.setCaso(caso);
                casoAcciones = em.merge(casoAcciones);
            }
            if (personaCedula != null) {
                personaCedula.getCasoCollection().add(caso);
                personaCedula = em.merge(personaCedula);
            }
            for (TipoCaso tipoCasoCollectionTipoCaso : caso.getTipoCasoCollection()) {
                tipoCasoCollectionTipoCaso.getCasoCollection().add(caso);
                tipoCasoCollectionTipoCaso = em.merge(tipoCasoCollectionTipoCaso);
            }
            for (EstadoCaso estadoCasoCollectionEstadoCaso : caso.getEstadoCasoCollection()) {
                estadoCasoCollectionEstadoCaso.getCasoCollection().add(caso);
                estadoCasoCollectionEstadoCaso = em.merge(estadoCasoCollectionEstadoCaso);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCaso(caso.getCodigocaso()) != null) {
                throw new PreexistingEntityException("Caso " + caso + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Caso caso) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Caso persistentCaso = em.find(Caso.class, caso.getCodigocaso());
            CasoAcciones casoAccionesOld = persistentCaso.getCasoAcciones();
            CasoAcciones casoAccionesNew = caso.getCasoAcciones();
            Persona personaCedulaOld = persistentCaso.getPersonaCedula();
            Persona personaCedulaNew = caso.getPersonaCedula();
            Collection<TipoCaso> tipoCasoCollectionOld = persistentCaso.getTipoCasoCollection();
            Collection<TipoCaso> tipoCasoCollectionNew = caso.getTipoCasoCollection();
            Collection<EstadoCaso> estadoCasoCollectionOld = persistentCaso.getEstadoCasoCollection();
            Collection<EstadoCaso> estadoCasoCollectionNew = caso.getEstadoCasoCollection();
            List<String> illegalOrphanMessages = null;
            if (casoAccionesOld != null && !casoAccionesOld.equals(casoAccionesNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain CasoAcciones " + casoAccionesOld + " since its caso field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (casoAccionesNew != null) {
                casoAccionesNew = em.getReference(casoAccionesNew.getClass(), casoAccionesNew.getCasoCodigocaso());
                caso.setCasoAcciones(casoAccionesNew);
            }
            if (personaCedulaNew != null) {
                personaCedulaNew = em.getReference(personaCedulaNew.getClass(), personaCedulaNew.getCedula());
                caso.setPersonaCedula(personaCedulaNew);
            }
            Collection<TipoCaso> attachedTipoCasoCollectionNew = new ArrayList<TipoCaso>();
            for (TipoCaso tipoCasoCollectionNewTipoCasoToAttach : tipoCasoCollectionNew) {
                tipoCasoCollectionNewTipoCasoToAttach = em.getReference(tipoCasoCollectionNewTipoCasoToAttach.getClass(), tipoCasoCollectionNewTipoCasoToAttach.getCodigoTipoCaso());
                attachedTipoCasoCollectionNew.add(tipoCasoCollectionNewTipoCasoToAttach);
            }
            tipoCasoCollectionNew = attachedTipoCasoCollectionNew;
            caso.setTipoCasoCollection(tipoCasoCollectionNew);
            Collection<EstadoCaso> attachedEstadoCasoCollectionNew = new ArrayList<EstadoCaso>();
            for (EstadoCaso estadoCasoCollectionNewEstadoCasoToAttach : estadoCasoCollectionNew) {
                estadoCasoCollectionNewEstadoCasoToAttach = em.getReference(estadoCasoCollectionNewEstadoCasoToAttach.getClass(), estadoCasoCollectionNewEstadoCasoToAttach.getCodigoestado());
                attachedEstadoCasoCollectionNew.add(estadoCasoCollectionNewEstadoCasoToAttach);
            }
            estadoCasoCollectionNew = attachedEstadoCasoCollectionNew;
            caso.setEstadoCasoCollection(estadoCasoCollectionNew);
            caso = em.merge(caso);
            if (casoAccionesNew != null && !casoAccionesNew.equals(casoAccionesOld)) {
                Caso oldCasoOfCasoAcciones = casoAccionesNew.getCaso();
                if (oldCasoOfCasoAcciones != null) {
                    oldCasoOfCasoAcciones.setCasoAcciones(null);
                    oldCasoOfCasoAcciones = em.merge(oldCasoOfCasoAcciones);
                }
                casoAccionesNew.setCaso(caso);
                casoAccionesNew = em.merge(casoAccionesNew);
            }
            if (personaCedulaOld != null && !personaCedulaOld.equals(personaCedulaNew)) {
                personaCedulaOld.getCasoCollection().remove(caso);
                personaCedulaOld = em.merge(personaCedulaOld);
            }
            if (personaCedulaNew != null && !personaCedulaNew.equals(personaCedulaOld)) {
                personaCedulaNew.getCasoCollection().add(caso);
                personaCedulaNew = em.merge(personaCedulaNew);
            }
            for (TipoCaso tipoCasoCollectionOldTipoCaso : tipoCasoCollectionOld) {
                if (!tipoCasoCollectionNew.contains(tipoCasoCollectionOldTipoCaso)) {
                    tipoCasoCollectionOldTipoCaso.getCasoCollection().remove(caso);
                    tipoCasoCollectionOldTipoCaso = em.merge(tipoCasoCollectionOldTipoCaso);
                }
            }
            for (TipoCaso tipoCasoCollectionNewTipoCaso : tipoCasoCollectionNew) {
                if (!tipoCasoCollectionOld.contains(tipoCasoCollectionNewTipoCaso)) {
                    tipoCasoCollectionNewTipoCaso.getCasoCollection().add(caso);
                    tipoCasoCollectionNewTipoCaso = em.merge(tipoCasoCollectionNewTipoCaso);
                }
            }
            for (EstadoCaso estadoCasoCollectionOldEstadoCaso : estadoCasoCollectionOld) {
                if (!estadoCasoCollectionNew.contains(estadoCasoCollectionOldEstadoCaso)) {
                    estadoCasoCollectionOldEstadoCaso.getCasoCollection().remove(caso);
                    estadoCasoCollectionOldEstadoCaso = em.merge(estadoCasoCollectionOldEstadoCaso);
                }
            }
            for (EstadoCaso estadoCasoCollectionNewEstadoCaso : estadoCasoCollectionNew) {
                if (!estadoCasoCollectionOld.contains(estadoCasoCollectionNewEstadoCaso)) {
                    estadoCasoCollectionNewEstadoCaso.getCasoCollection().add(caso);
                    estadoCasoCollectionNewEstadoCaso = em.merge(estadoCasoCollectionNewEstadoCaso);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = caso.getCodigocaso();
                if (findCaso(id) == null) {
                    throw new NonexistentEntityException("The caso with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Caso caso;
            try {
                caso = em.getReference(Caso.class, id);
                caso.getCodigocaso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The caso with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            CasoAcciones casoAccionesOrphanCheck = caso.getCasoAcciones();
            if (casoAccionesOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Caso (" + caso + ") cannot be destroyed since the CasoAcciones " + casoAccionesOrphanCheck + " in its casoAcciones field has a non-nullable caso field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Persona personaCedula = caso.getPersonaCedula();
            if (personaCedula != null) {
                personaCedula.getCasoCollection().remove(caso);
                personaCedula = em.merge(personaCedula);
            }
            Collection<TipoCaso> tipoCasoCollection = caso.getTipoCasoCollection();
            for (TipoCaso tipoCasoCollectionTipoCaso : tipoCasoCollection) {
                tipoCasoCollectionTipoCaso.getCasoCollection().remove(caso);
                tipoCasoCollectionTipoCaso = em.merge(tipoCasoCollectionTipoCaso);
            }
            Collection<EstadoCaso> estadoCasoCollection = caso.getEstadoCasoCollection();
            for (EstadoCaso estadoCasoCollectionEstadoCaso : estadoCasoCollection) {
                estadoCasoCollectionEstadoCaso.getCasoCollection().remove(caso);
                estadoCasoCollectionEstadoCaso = em.merge(estadoCasoCollectionEstadoCaso);
            }
            em.remove(caso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Caso> findCasoEntities() {
        return findCasoEntities(true, -1, -1);
    }

    public List<Caso> findCasoEntities(int maxResults, int firstResult) {
        return findCasoEntities(false, maxResults, firstResult);
    }

    private List<Caso> findCasoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Caso.class));
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

    public Caso findCaso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Caso.class, id);
        } finally {
            em.close();
        }
    }

    public int getCasoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Caso> rt = cq.from(Caso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
