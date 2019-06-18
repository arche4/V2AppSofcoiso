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
import com.co.sofcoiso.modelo.Afp;
import com.co.sofcoiso.modelo.Arl;
import com.co.sofcoiso.modelo.Eps;
import com.co.sofcoiso.modelo.Persona;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author manue
 */
public class PersonaJpaController implements Serializable {

    public PersonaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Persona persona) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Afp afpCodigoafp = persona.getAfpCodigoafp();
            if (afpCodigoafp != null) {
                afpCodigoafp = em.getReference(afpCodigoafp.getClass(), afpCodigoafp.getCodigoafp());
                persona.setAfpCodigoafp(afpCodigoafp);
            }
            Arl arlCodigoarl = persona.getArlCodigoarl();
            if (arlCodigoarl != null) {
                arlCodigoarl = em.getReference(arlCodigoarl.getClass(), arlCodigoarl.getCodigoarl());
                persona.setArlCodigoarl(arlCodigoarl);
            }
            Eps epsCodigoeps = persona.getEpsCodigoeps();
            if (epsCodigoeps != null) {
                epsCodigoeps = em.getReference(epsCodigoeps.getClass(), epsCodigoeps.getCodigoeps());
                persona.setEpsCodigoeps(epsCodigoeps);
            }
            em.persist(persona);
            if (afpCodigoafp != null) {
                afpCodigoafp.getPersonaCollection().add(persona);
                afpCodigoafp = em.merge(afpCodigoafp);
            }
            if (arlCodigoarl != null) {
                arlCodigoarl.getPersonaCollection().add(persona);
                arlCodigoarl = em.merge(arlCodigoarl);
            }
            if (epsCodigoeps != null) {
                epsCodigoeps.getPersonaCollection().add(persona);
                epsCodigoeps = em.merge(epsCodigoeps);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPersona(persona.getCedula()) != null) {
                throw new PreexistingEntityException("Persona " + persona + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Persona persona) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona persistentPersona = em.find(Persona.class, persona.getCedula());
            Afp afpCodigoafpOld = persistentPersona.getAfpCodigoafp();
            Afp afpCodigoafpNew = persona.getAfpCodigoafp();
            Arl arlCodigoarlOld = persistentPersona.getArlCodigoarl();
            Arl arlCodigoarlNew = persona.getArlCodigoarl();
            Eps epsCodigoepsOld = persistentPersona.getEpsCodigoeps();
            Eps epsCodigoepsNew = persona.getEpsCodigoeps();
            if (afpCodigoafpNew != null) {
                afpCodigoafpNew = em.getReference(afpCodigoafpNew.getClass(), afpCodigoafpNew.getCodigoafp());
                persona.setAfpCodigoafp(afpCodigoafpNew);
            }
            if (arlCodigoarlNew != null) {
                arlCodigoarlNew = em.getReference(arlCodigoarlNew.getClass(), arlCodigoarlNew.getCodigoarl());
                persona.setArlCodigoarl(arlCodigoarlNew);
            }
            if (epsCodigoepsNew != null) {
                epsCodigoepsNew = em.getReference(epsCodigoepsNew.getClass(), epsCodigoepsNew.getCodigoeps());
                persona.setEpsCodigoeps(epsCodigoepsNew);
            }
            persona = em.merge(persona);
            if (afpCodigoafpOld != null && !afpCodigoafpOld.equals(afpCodigoafpNew)) {
                afpCodigoafpOld.getPersonaCollection().remove(persona);
                afpCodigoafpOld = em.merge(afpCodigoafpOld);
            }
            if (afpCodigoafpNew != null && !afpCodigoafpNew.equals(afpCodigoafpOld)) {
                afpCodigoafpNew.getPersonaCollection().add(persona);
                afpCodigoafpNew = em.merge(afpCodigoafpNew);
            }
            if (arlCodigoarlOld != null && !arlCodigoarlOld.equals(arlCodigoarlNew)) {
                arlCodigoarlOld.getPersonaCollection().remove(persona);
                arlCodigoarlOld = em.merge(arlCodigoarlOld);
            }
            if (arlCodigoarlNew != null && !arlCodigoarlNew.equals(arlCodigoarlOld)) {
                arlCodigoarlNew.getPersonaCollection().add(persona);
                arlCodigoarlNew = em.merge(arlCodigoarlNew);
            }
            if (epsCodigoepsOld != null && !epsCodigoepsOld.equals(epsCodigoepsNew)) {
                epsCodigoepsOld.getPersonaCollection().remove(persona);
                epsCodigoepsOld = em.merge(epsCodigoepsOld);
            }
            if (epsCodigoepsNew != null && !epsCodigoepsNew.equals(epsCodigoepsOld)) {
                epsCodigoepsNew.getPersonaCollection().add(persona);
                epsCodigoepsNew = em.merge(epsCodigoepsNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = persona.getCedula();
                if (findPersona(id) == null) {
                    throw new NonexistentEntityException("The persona with id " + id + " no longer exists.");
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
            Persona persona;
            try {
                persona = em.getReference(Persona.class, id);
                persona.getCedula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The persona with id " + id + " no longer exists.", enfe);
            }
            Afp afpCodigoafp = persona.getAfpCodigoafp();
            if (afpCodigoafp != null) {
                afpCodigoafp.getPersonaCollection().remove(persona);
                afpCodigoafp = em.merge(afpCodigoafp);
            }
            Arl arlCodigoarl = persona.getArlCodigoarl();
            if (arlCodigoarl != null) {
                arlCodigoarl.getPersonaCollection().remove(persona);
                arlCodigoarl = em.merge(arlCodigoarl);
            }
            Eps epsCodigoeps = persona.getEpsCodigoeps();
            if (epsCodigoeps != null) {
                epsCodigoeps.getPersonaCollection().remove(persona);
                epsCodigoeps = em.merge(epsCodigoeps);
            }
            em.remove(persona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Persona> findPersonaEntities() {
        return findPersonaEntities(true, -1, -1);
    }

    public List<Persona> findPersonaEntities(int maxResults, int firstResult) {
        return findPersonaEntities(false, maxResults, firstResult);
    }

    private List<Persona> findPersonaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Persona.class));
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

    public Persona findPersona(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Persona.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Persona> rt = cq.from(Persona.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
     public String  crear(Persona personas){
        String respuesta = null;
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(personas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPersona(personas.getCedula()) != null) {
                 respuesta = "Persona ya existe";
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
