
package com.mycompany.proyectoprogra.controllers;

import com.mycompany.proyectoprogra.controllers.exceptions.IllegalOrphanException;
import com.mycompany.proyectoprogra.controllers.exceptions.NonexistentEntityException;
import com.mycompany.proyectoprogra.controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import com.mycompany.proyectoprogra.entitys.Envios;
import com.mycompany.proyectoprogra.entitys.Clientes;
import com.mycompany.proyectoprogra.entitys.Detalleordenes;
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
public class OrdenesJpaController implements Serializable {

    public OrdenesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
     public OrdenesJpaController() {
        emf = com.mycompany.proyectoprogra.controllers.JpaUtil.getEntityManagerFactory();
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ordenes ordenes) throws PreexistingEntityException, Exception {
        if (ordenes.getDetalleordenesCollection() == null) {
            ordenes.setDetalleordenesCollection(new ArrayList<Detalleordenes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Envios idEnvio = ordenes.getIdEnvio();
            if (idEnvio != null) {
                idEnvio = em.getReference(idEnvio.getClass(), idEnvio.getIdEnvio());
                ordenes.setIdEnvio(idEnvio);
            }
            Clientes idCliente = ordenes.getIdCliente();
            if (idCliente != null) {
                idCliente = em.getReference(idCliente.getClass(), idCliente.getIdCliente());
                ordenes.setIdCliente(idCliente);
            }
            Collection<Detalleordenes> attachedDetalleordenesCollection = new ArrayList<Detalleordenes>();
            for (Detalleordenes detalleordenesCollectionDetalleordenesToAttach : ordenes.getDetalleordenesCollection()) {
                detalleordenesCollectionDetalleordenesToAttach = em.getReference(detalleordenesCollectionDetalleordenesToAttach.getClass(), detalleordenesCollectionDetalleordenesToAttach.getDetalleordenesPK());
                attachedDetalleordenesCollection.add(detalleordenesCollectionDetalleordenesToAttach);
            }
            ordenes.setDetalleordenesCollection(attachedDetalleordenesCollection);
            em.persist(ordenes);
            if (idEnvio != null) {
                idEnvio.getOrdenesCollection().add(ordenes);
                idEnvio = em.merge(idEnvio);
            }
            if (idCliente != null) {
                idCliente.getOrdenesCollection().add(ordenes);
                idCliente = em.merge(idCliente);
            }
            for (Detalleordenes detalleordenesCollectionDetalleordenes : ordenes.getDetalleordenesCollection()) {
                Ordenes oldOrdenesOfDetalleordenesCollectionDetalleordenes = detalleordenesCollectionDetalleordenes.getOrdenes();
                detalleordenesCollectionDetalleordenes.setOrdenes(ordenes);
                detalleordenesCollectionDetalleordenes = em.merge(detalleordenesCollectionDetalleordenes);
                if (oldOrdenesOfDetalleordenesCollectionDetalleordenes != null) {
                    oldOrdenesOfDetalleordenesCollectionDetalleordenes.getDetalleordenesCollection().remove(detalleordenesCollectionDetalleordenes);
                    oldOrdenesOfDetalleordenesCollectionDetalleordenes = em.merge(oldOrdenesOfDetalleordenesCollectionDetalleordenes);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOrdenes(ordenes.getIdOrden()) != null) {
                throw new PreexistingEntityException("Ordenes " + ordenes + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ordenes ordenes) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ordenes persistentOrdenes = em.find(Ordenes.class, ordenes.getIdOrden());
            Envios idEnvioOld = persistentOrdenes.getIdEnvio();
            Envios idEnvioNew = ordenes.getIdEnvio();
            Clientes idClienteOld = persistentOrdenes.getIdCliente();
            Clientes idClienteNew = ordenes.getIdCliente();
            Collection<Detalleordenes> detalleordenesCollectionOld = persistentOrdenes.getDetalleordenesCollection();
            Collection<Detalleordenes> detalleordenesCollectionNew = ordenes.getDetalleordenesCollection();
            List<String> illegalOrphanMessages = null;
            for (Detalleordenes detalleordenesCollectionOldDetalleordenes : detalleordenesCollectionOld) {
                if (!detalleordenesCollectionNew.contains(detalleordenesCollectionOldDetalleordenes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleordenes " + detalleordenesCollectionOldDetalleordenes + " since its ordenes field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idEnvioNew != null) {
                idEnvioNew = em.getReference(idEnvioNew.getClass(), idEnvioNew.getIdEnvio());
                ordenes.setIdEnvio(idEnvioNew);
            }
            if (idClienteNew != null) {
                idClienteNew = em.getReference(idClienteNew.getClass(), idClienteNew.getIdCliente());
                ordenes.setIdCliente(idClienteNew);
            }
            Collection<Detalleordenes> attachedDetalleordenesCollectionNew = new ArrayList<Detalleordenes>();
            for (Detalleordenes detalleordenesCollectionNewDetalleordenesToAttach : detalleordenesCollectionNew) {
                detalleordenesCollectionNewDetalleordenesToAttach = em.getReference(detalleordenesCollectionNewDetalleordenesToAttach.getClass(), detalleordenesCollectionNewDetalleordenesToAttach.getDetalleordenesPK());
                attachedDetalleordenesCollectionNew.add(detalleordenesCollectionNewDetalleordenesToAttach);
            }
            detalleordenesCollectionNew = attachedDetalleordenesCollectionNew;
            ordenes.setDetalleordenesCollection(detalleordenesCollectionNew);
            ordenes = em.merge(ordenes);
            if (idEnvioOld != null && !idEnvioOld.equals(idEnvioNew)) {
                idEnvioOld.getOrdenesCollection().remove(ordenes);
                idEnvioOld = em.merge(idEnvioOld);
            }
            if (idEnvioNew != null && !idEnvioNew.equals(idEnvioOld)) {
                idEnvioNew.getOrdenesCollection().add(ordenes);
                idEnvioNew = em.merge(idEnvioNew);
            }
            if (idClienteOld != null && !idClienteOld.equals(idClienteNew)) {
                idClienteOld.getOrdenesCollection().remove(ordenes);
                idClienteOld = em.merge(idClienteOld);
            }
            if (idClienteNew != null && !idClienteNew.equals(idClienteOld)) {
                idClienteNew.getOrdenesCollection().add(ordenes);
                idClienteNew = em.merge(idClienteNew);
            }
            for (Detalleordenes detalleordenesCollectionNewDetalleordenes : detalleordenesCollectionNew) {
                if (!detalleordenesCollectionOld.contains(detalleordenesCollectionNewDetalleordenes)) {
                    Ordenes oldOrdenesOfDetalleordenesCollectionNewDetalleordenes = detalleordenesCollectionNewDetalleordenes.getOrdenes();
                    detalleordenesCollectionNewDetalleordenes.setOrdenes(ordenes);
                    detalleordenesCollectionNewDetalleordenes = em.merge(detalleordenesCollectionNewDetalleordenes);
                    if (oldOrdenesOfDetalleordenesCollectionNewDetalleordenes != null && !oldOrdenesOfDetalleordenesCollectionNewDetalleordenes.equals(ordenes)) {
                        oldOrdenesOfDetalleordenesCollectionNewDetalleordenes.getDetalleordenesCollection().remove(detalleordenesCollectionNewDetalleordenes);
                        oldOrdenesOfDetalleordenesCollectionNewDetalleordenes = em.merge(oldOrdenesOfDetalleordenesCollectionNewDetalleordenes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = ordenes.getIdOrden();
                if (findOrdenes(id) == null) {
                    throw new NonexistentEntityException("The ordenes with id " + id + " no longer exists.");
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
            Ordenes ordenes;
            try {
                ordenes = em.getReference(Ordenes.class, id);
                ordenes.getIdOrden();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ordenes with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Detalleordenes> detalleordenesCollectionOrphanCheck = ordenes.getDetalleordenesCollection();
            for (Detalleordenes detalleordenesCollectionOrphanCheckDetalleordenes : detalleordenesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ordenes (" + ordenes + ") cannot be destroyed since the Detalleordenes " + detalleordenesCollectionOrphanCheckDetalleordenes + " in its detalleordenesCollection field has a non-nullable ordenes field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Envios idEnvio = ordenes.getIdEnvio();
            if (idEnvio != null) {
                idEnvio.getOrdenesCollection().remove(ordenes);
                idEnvio = em.merge(idEnvio);
            }
            Clientes idCliente = ordenes.getIdCliente();
            if (idCliente != null) {
                idCliente.getOrdenesCollection().remove(ordenes);
                idCliente = em.merge(idCliente);
            }
            em.remove(ordenes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ordenes> findOrdenesEntities() {
        return findOrdenesEntities(true, -1, -1);
    }

    public List<Ordenes> findOrdenesEntities(int maxResults, int firstResult) {
        return findOrdenesEntities(false, maxResults, firstResult);
    }

    private List<Ordenes> findOrdenesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ordenes.class));
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

    public Ordenes findOrdenes(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ordenes.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrdenesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ordenes> rt = cq.from(Ordenes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
