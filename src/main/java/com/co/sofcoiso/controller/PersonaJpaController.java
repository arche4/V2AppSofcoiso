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
import com.co.sofcoiso.modelo.Afp;
import com.co.sofcoiso.modelo.Arl;
import com.co.sofcoiso.modelo.Eps;
import com.co.sofcoiso.modelo.Caso;
import com.co.sofcoiso.modelo.Persona;
import java.util.ArrayList;
import java.util.Collection;
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
        if (persona.getCasoCollection() == null) {
            persona.setCasoCollection(new ArrayList<Caso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Afp codigoafp = persona.getCodigoafp();
            if (codigoafp != null) {
                codigoafp = em.getReference(codigoafp.getClass(), codigoafp.getCodigoafp());
                persona.setCodigoafp(codigoafp);
            }
            Arl codigoarl = persona.getCodigoarl();
            if (codigoarl != null) {
                codigoarl = em.getReference(codigoarl.getClass(), codigoarl.getCodigoarl());
                persona.setCodigoarl(codigoarl);
            }
            Eps codigoeps = persona.getCodigoeps();
            if (codigoeps != null) {
                codigoeps = em.getReference(codigoeps.getClass(), codigoeps.getCodigoeps());
                persona.setCodigoeps(codigoeps);
            }
            Collection<Caso> attachedCasoCollection = new ArrayList<Caso>();
            for (Caso casoCollectionCasoToAttach : persona.getCasoCollection()) {
                casoCollectionCasoToAttach = em.getReference(casoCollectionCasoToAttach.getClass(), casoCollectionCasoToAttach.getCodigocaso());
                attachedCasoCollection.add(casoCollectionCasoToAttach);
            }
            persona.setCasoCollection(attachedCasoCollection);
            em.persist(persona);
            if (codigoafp != null) {
                codigoafp.getPersonaCollection().add(persona);
                codigoafp = em.merge(codigoafp);
            }
            if (codigoarl != null) {
                codigoarl.getPersonaCollection().add(persona);
                codigoarl = em.merge(codigoarl);
            }
            if (codigoeps != null) {
                codigoeps.getPersonaCollection().add(persona);
                codigoeps = em.merge(codigoeps);
            }
            for (Caso casoCollectionCaso : persona.getCasoCollection()) {
                Persona oldPersonaCedulaOfCasoCollectionCaso = casoCollectionCaso.getPersonaCedula();
                casoCollectionCaso.setPersonaCedula(persona);
                casoCollectionCaso = em.merge(casoCollectionCaso);
                if (oldPersonaCedulaOfCasoCollectionCaso != null) {
                    oldPersonaCedulaOfCasoCollectionCaso.getCasoCollection().remove(casoCollectionCaso);
                    oldPersonaCedulaOfCasoCollectionCaso = em.merge(oldPersonaCedulaOfCasoCollectionCaso);
                }
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

    public void edit(Persona persona) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona persistentPersona = em.find(Persona.class, persona.getCedula());
            Afp codigoafpOld = persistentPersona.getCodigoafp();
            Afp codigoafpNew = persona.getCodigoafp();
            Arl codigoarlOld = persistentPersona.getCodigoarl();
            Arl codigoarlNew = persona.getCodigoarl();
            Eps codigoepsOld = persistentPersona.getCodigoeps();
            Eps codigoepsNew = persona.getCodigoeps();
            Collection<Caso> casoCollectionOld = persistentPersona.getCasoCollection();
            Collection<Caso> casoCollectionNew = persona.getCasoCollection();
            List<String> illegalOrphanMessages = null;
            for (Caso casoCollectionOldCaso : casoCollectionOld) {
                if (!casoCollectionNew.contains(casoCollectionOldCaso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Caso " + casoCollectionOldCaso + " since its personaCedula field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codigoafpNew != null) {
                codigoafpNew = em.getReference(codigoafpNew.getClass(), codigoafpNew.getCodigoafp());
                persona.setCodigoafp(codigoafpNew);
            }
            if (codigoarlNew != null) {
                codigoarlNew = em.getReference(codigoarlNew.getClass(), codigoarlNew.getCodigoarl());
                persona.setCodigoarl(codigoarlNew);
            }
            if (codigoepsNew != null) {
                codigoepsNew = em.getReference(codigoepsNew.getClass(), codigoepsNew.getCodigoeps());
                persona.setCodigoeps(codigoepsNew);
            }
            Collection<Caso> attachedCasoCollectionNew = new ArrayList<Caso>();
            for (Caso casoCollectionNewCasoToAttach : casoCollectionNew) {
                casoCollectionNewCasoToAttach = em.getReference(casoCollectionNewCasoToAttach.getClass(), casoCollectionNewCasoToAttach.getCodigocaso());
                attachedCasoCollectionNew.add(casoCollectionNewCasoToAttach);
            }
            casoCollectionNew = attachedCasoCollectionNew;
            persona.setCasoCollection(casoCollectionNew);
            persona = em.merge(persona);
            if (codigoafpOld != null && !codigoafpOld.equals(codigoafpNew)) {
                codigoafpOld.getPersonaCollection().remove(persona);
                codigoafpOld = em.merge(codigoafpOld);
            }
            if (codigoafpNew != null && !codigoafpNew.equals(codigoafpOld)) {
                codigoafpNew.getPersonaCollection().add(persona);
                codigoafpNew = em.merge(codigoafpNew);
            }
            if (codigoarlOld != null && !codigoarlOld.equals(codigoarlNew)) {
                codigoarlOld.getPersonaCollection().remove(persona);
                codigoarlOld = em.merge(codigoarlOld);
            }
            if (codigoarlNew != null && !codigoarlNew.equals(codigoarlOld)) {
                codigoarlNew.getPersonaCollection().add(persona);
                codigoarlNew = em.merge(codigoarlNew);
            }
            if (codigoepsOld != null && !codigoepsOld.equals(codigoepsNew)) {
                codigoepsOld.getPersonaCollection().remove(persona);
                codigoepsOld = em.merge(codigoepsOld);
            }
            if (codigoepsNew != null && !codigoepsNew.equals(codigoepsOld)) {
                codigoepsNew.getPersonaCollection().add(persona);
                codigoepsNew = em.merge(codigoepsNew);
            }
            for (Caso casoCollectionNewCaso : casoCollectionNew) {
                if (!casoCollectionOld.contains(casoCollectionNewCaso)) {
                    Persona oldPersonaCedulaOfCasoCollectionNewCaso = casoCollectionNewCaso.getPersonaCedula();
                    casoCollectionNewCaso.setPersonaCedula(persona);
                    casoCollectionNewCaso = em.merge(casoCollectionNewCaso);
                    if (oldPersonaCedulaOfCasoCollectionNewCaso != null && !oldPersonaCedulaOfCasoCollectionNewCaso.equals(persona)) {
                        oldPersonaCedulaOfCasoCollectionNewCaso.getCasoCollection().remove(casoCollectionNewCaso);
                        oldPersonaCedulaOfCasoCollectionNewCaso = em.merge(oldPersonaCedulaOfCasoCollectionNewCaso);
                    }
                }
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            Collection<Caso> casoCollectionOrphanCheck = persona.getCasoCollection();
            for (Caso casoCollectionOrphanCheckCaso : casoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persona (" + persona + ") cannot be destroyed since the Caso " + casoCollectionOrphanCheckCaso + " in its casoCollection field has a non-nullable personaCedula field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Afp codigoafp = persona.getCodigoafp();
            if (codigoafp != null) {
                codigoafp.getPersonaCollection().remove(persona);
                codigoafp = em.merge(codigoafp);
            }
            Arl codigoarl = persona.getCodigoarl();
            if (codigoarl != null) {
                codigoarl.getPersonaCollection().remove(persona);
                codigoarl = em.merge(codigoarl);
            }
            Eps codigoeps = persona.getCodigoeps();
            if (codigoeps != null) {
                codigoeps.getPersonaCollection().remove(persona);
                codigoeps = em.merge(codigoeps);
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
