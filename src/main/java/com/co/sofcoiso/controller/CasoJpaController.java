/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.sofcoiso.controller;

import com.co.sofcoiso.controller.exceptions.NonexistentEntityException;
import com.co.sofcoiso.controller.exceptions.PreexistingEntityException;
import com.co.sofcoiso.modelo.Caso;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.co.sofcoiso.modelo.EstadoCaso;
import com.co.sofcoiso.modelo.Persona;
import com.co.sofcoiso.modelo.TipoCaso;
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
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EstadoCaso estadoCasoCodigoestado = caso.getEstadoCasoCodigoestado();
            if (estadoCasoCodigoestado != null) {
                estadoCasoCodigoestado = em.getReference(estadoCasoCodigoestado.getClass(), estadoCasoCodigoestado.getCodigoestado());
                caso.setEstadoCasoCodigoestado(estadoCasoCodigoestado);
            }
            Persona personaCedula = caso.getPersonaCedula();
            if (personaCedula != null) {
                personaCedula = em.getReference(personaCedula.getClass(), personaCedula.getCedula());
                caso.setPersonaCedula(personaCedula);
            }
            TipoCaso tipoCasoCodigoTipoCaso = caso.getTipoCasoCodigoTipoCaso();
            if (tipoCasoCodigoTipoCaso != null) {
                tipoCasoCodigoTipoCaso = em.getReference(tipoCasoCodigoTipoCaso.getClass(), tipoCasoCodigoTipoCaso.getCodigoTipoCaso());
                caso.setTipoCasoCodigoTipoCaso(tipoCasoCodigoTipoCaso);
            }
            em.persist(caso);
            if (estadoCasoCodigoestado != null) {
                estadoCasoCodigoestado.getCasoCollection().add(caso);
                estadoCasoCodigoestado = em.merge(estadoCasoCodigoestado);
            }
            if (personaCedula != null) {
                personaCedula.getCasoCollection().add(caso);
                personaCedula = em.merge(personaCedula);
            }
            if (tipoCasoCodigoTipoCaso != null) {
                tipoCasoCodigoTipoCaso.getCasoCollection().add(caso);
                tipoCasoCodigoTipoCaso = em.merge(tipoCasoCodigoTipoCaso);
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

    public void edit(Caso caso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Caso persistentCaso = em.find(Caso.class, caso.getCodigocaso());
            EstadoCaso estadoCasoCodigoestadoOld = persistentCaso.getEstadoCasoCodigoestado();
            EstadoCaso estadoCasoCodigoestadoNew = caso.getEstadoCasoCodigoestado();
            Persona personaCedulaOld = persistentCaso.getPersonaCedula();
            Persona personaCedulaNew = caso.getPersonaCedula();
            TipoCaso tipoCasoCodigoTipoCasoOld = persistentCaso.getTipoCasoCodigoTipoCaso();
            TipoCaso tipoCasoCodigoTipoCasoNew = caso.getTipoCasoCodigoTipoCaso();
            if (estadoCasoCodigoestadoNew != null) {
                estadoCasoCodigoestadoNew = em.getReference(estadoCasoCodigoestadoNew.getClass(), estadoCasoCodigoestadoNew.getCodigoestado());
                caso.setEstadoCasoCodigoestado(estadoCasoCodigoestadoNew);
            }
            if (personaCedulaNew != null) {
                personaCedulaNew = em.getReference(personaCedulaNew.getClass(), personaCedulaNew.getCedula());
                caso.setPersonaCedula(personaCedulaNew);
            }
            if (tipoCasoCodigoTipoCasoNew != null) {
                tipoCasoCodigoTipoCasoNew = em.getReference(tipoCasoCodigoTipoCasoNew.getClass(), tipoCasoCodigoTipoCasoNew.getCodigoTipoCaso());
                caso.setTipoCasoCodigoTipoCaso(tipoCasoCodigoTipoCasoNew);
            }
            caso = em.merge(caso);
            if (estadoCasoCodigoestadoOld != null && !estadoCasoCodigoestadoOld.equals(estadoCasoCodigoestadoNew)) {
                estadoCasoCodigoestadoOld.getCasoCollection().remove(caso);
                estadoCasoCodigoestadoOld = em.merge(estadoCasoCodigoestadoOld);
            }
            if (estadoCasoCodigoestadoNew != null && !estadoCasoCodigoestadoNew.equals(estadoCasoCodigoestadoOld)) {
                estadoCasoCodigoestadoNew.getCasoCollection().add(caso);
                estadoCasoCodigoestadoNew = em.merge(estadoCasoCodigoestadoNew);
            }
            if (personaCedulaOld != null && !personaCedulaOld.equals(personaCedulaNew)) {
                personaCedulaOld.getCasoCollection().remove(caso);
                personaCedulaOld = em.merge(personaCedulaOld);
            }
            if (personaCedulaNew != null && !personaCedulaNew.equals(personaCedulaOld)) {
                personaCedulaNew.getCasoCollection().add(caso);
                personaCedulaNew = em.merge(personaCedulaNew);
            }
            if (tipoCasoCodigoTipoCasoOld != null && !tipoCasoCodigoTipoCasoOld.equals(tipoCasoCodigoTipoCasoNew)) {
                tipoCasoCodigoTipoCasoOld.getCasoCollection().remove(caso);
                tipoCasoCodigoTipoCasoOld = em.merge(tipoCasoCodigoTipoCasoOld);
            }
            if (tipoCasoCodigoTipoCasoNew != null && !tipoCasoCodigoTipoCasoNew.equals(tipoCasoCodigoTipoCasoOld)) {
                tipoCasoCodigoTipoCasoNew.getCasoCollection().add(caso);
                tipoCasoCodigoTipoCasoNew = em.merge(tipoCasoCodigoTipoCasoNew);
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

    public void destroy(Integer id) throws NonexistentEntityException {
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
            EstadoCaso estadoCasoCodigoestado = caso.getEstadoCasoCodigoestado();
            if (estadoCasoCodigoestado != null) {
                estadoCasoCodigoestado.getCasoCollection().remove(caso);
                estadoCasoCodigoestado = em.merge(estadoCasoCodigoestado);
            }
            Persona personaCedula = caso.getPersonaCedula();
            if (personaCedula != null) {
                personaCedula.getCasoCollection().remove(caso);
                personaCedula = em.merge(personaCedula);
            }
            TipoCaso tipoCasoCodigoTipoCaso = caso.getTipoCasoCodigoTipoCaso();
            if (tipoCasoCodigoTipoCaso != null) {
                tipoCasoCodigoTipoCaso.getCasoCollection().remove(caso);
                tipoCasoCodigoTipoCaso = em.merge(tipoCasoCodigoTipoCaso);
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

    public String crear(Caso caso) {
        String respuesta = null;
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(caso);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCaso(caso.getCodigocaso()) != null) {
                respuesta = "Caso ya existe";
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return respuesta;
    }

    public String cambiarEstado(Caso caso)  {
        EntityManager em = null;
        String respuesta = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            caso = em.merge(caso);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = caso.getCodigocaso();
                if (findCaso(id) == null) {
                    respuesta = "Caso no existe";
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
          return respuesta;
    }

}
