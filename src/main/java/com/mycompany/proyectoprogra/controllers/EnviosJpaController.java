
package com.mycompany.proyectoprogra.controllers;

import com.mycompany.proyectoprogra.controllers.exceptions.IllegalOrphanException;
import com.mycompany.proyectoprogra.controllers.exceptions.NonexistentEntityException;
import com.mycompany.proyectoprogra.controllers.exceptions.PreexistingEntityException;
import com.mycompany.proyectoprogra.entitys.Envios;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import com.mycompany.proyectoprogra.entitys.Ordenes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author totol
 */
public class EnviosJpaController implements Serializable {

    public EnviosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public EnviosJpaController() {
        emf = com.mycompany.proyectoprogra.controllers.JpaUtil.getEntityManagerFactory();
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Envios envios) throws PreexistingEntityException, Exception {
        if (envios.getOrdenesCollection() == null) {
            envios.setOrdenesCollection(new ArrayList<Ordenes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Ordenes> attachedOrdenesCollection = new ArrayList<Ordenes>();
            for (Ordenes ordenesCollectionOrdenesToAttach : envios.getOrdenesCollection()) {
                ordenesCollectionOrdenesToAttach = em.getReference(ordenesCollectionOrdenesToAttach.getClass(), ordenesCollectionOrdenesToAttach.getIdOrden());
                attachedOrdenesCollection.add(ordenesCollectionOrdenesToAttach);
            }
            envios.setOrdenesCollection(attachedOrdenesCollection);
            em.persist(envios);
            for (Ordenes ordenesCollectionOrdenes : envios.getOrdenesCollection()) {
                Envios oldIdEnvioOfOrdenesCollectionOrdenes = ordenesCollectionOrdenes.getIdEnvio();
                ordenesCollectionOrdenes.setIdEnvio(envios);
                ordenesCollectionOrdenes = em.merge(ordenesCollectionOrdenes);
                if (oldIdEnvioOfOrdenesCollectionOrdenes != null) {
                    oldIdEnvioOfOrdenesCollectionOrdenes.getOrdenesCollection().remove(ordenesCollectionOrdenes);
                    oldIdEnvioOfOrdenesCollectionOrdenes = em.merge(oldIdEnvioOfOrdenesCollectionOrdenes);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEnvios(envios.getIdEnvio()) != null) {
                throw new PreexistingEntityException("Envios " + envios + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Envios envios) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Envios persistentEnvios = em.find(Envios.class, envios.getIdEnvio());
            Collection<Ordenes> ordenesCollectionOld = persistentEnvios.getOrdenesCollection();
            Collection<Ordenes> ordenesCollectionNew = envios.getOrdenesCollection();
            List<String> illegalOrphanMessages = null;
            for (Ordenes ordenesCollectionOldOrdenes : ordenesCollectionOld) {
                if (!ordenesCollectionNew.contains(ordenesCollectionOldOrdenes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ordenes " + ordenesCollectionOldOrdenes + " since its idEnvio field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Ordenes> attachedOrdenesCollectionNew = new ArrayList<Ordenes>();
            for (Ordenes ordenesCollectionNewOrdenesToAttach : ordenesCollectionNew) {
                ordenesCollectionNewOrdenesToAttach = em.getReference(ordenesCollectionNewOrdenesToAttach.getClass(), ordenesCollectionNewOrdenesToAttach.getIdOrden());
                attachedOrdenesCollectionNew.add(ordenesCollectionNewOrdenesToAttach);
            }
            ordenesCollectionNew = attachedOrdenesCollectionNew;
            envios.setOrdenesCollection(ordenesCollectionNew);
            envios = em.merge(envios);
            for (Ordenes ordenesCollectionNewOrdenes : ordenesCollectionNew) {
                if (!ordenesCollectionOld.contains(ordenesCollectionNewOrdenes)) {
                    Envios oldIdEnvioOfOrdenesCollectionNewOrdenes = ordenesCollectionNewOrdenes.getIdEnvio();
                    ordenesCollectionNewOrdenes.setIdEnvio(envios);
                    ordenesCollectionNewOrdenes = em.merge(ordenesCollectionNewOrdenes);
                    if (oldIdEnvioOfOrdenesCollectionNewOrdenes != null && !oldIdEnvioOfOrdenesCollectionNewOrdenes.equals(envios)) {
                        oldIdEnvioOfOrdenesCollectionNewOrdenes.getOrdenesCollection().remove(ordenesCollectionNewOrdenes);
                        oldIdEnvioOfOrdenesCollectionNewOrdenes = em.merge(oldIdEnvioOfOrdenesCollectionNewOrdenes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = envios.getIdEnvio();
                if (findEnvios(id) == null) {
                    throw new NonexistentEntityException("The envios with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Envios envios;
            try {
                envios = em.getReference(Envios.class, id);
                envios.getIdEnvio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The envios with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Ordenes> ordenesCollectionOrphanCheck = envios.getOrdenesCollection();
            for (Ordenes ordenesCollectionOrphanCheckOrdenes : ordenesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Envios (" + envios + ") cannot be destroyed since the Ordenes " + ordenesCollectionOrphanCheckOrdenes + " in its ordenesCollection field has a non-nullable idEnvio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(envios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Envios> findEnviosEntities() {
        return findEnviosEntities(true, -1, -1);
    }

    public List<Envios> findEnviosEntities(int maxResults, int firstResult) {
        return findEnviosEntities(false, maxResults, firstResult);
    }

    private List<Envios> findEnviosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Envios.class));
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

    public Envios findEnvios(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Envios.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnviosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Envios> rt = cq.from(Envios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
