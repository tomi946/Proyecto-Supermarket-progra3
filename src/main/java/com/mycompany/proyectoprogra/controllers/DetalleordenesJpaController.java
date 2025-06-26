
package com.mycompany.proyectoprogra.controllers;

import com.mycompany.proyectoprogra.controllers.exceptions.NonexistentEntityException;
import com.mycompany.proyectoprogra.controllers.exceptions.PreexistingEntityException;
import com.mycompany.proyectoprogra.entitys.Detalleordenes;
import com.mycompany.proyectoprogra.entitys.DetalleordenesPK;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import com.mycompany.proyectoprogra.entitys.Ordenes;
import com.mycompany.proyectoprogra.entitys.Productos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

/**
 *
 * @author totol
 */
public class DetalleordenesJpaController implements Serializable {

    public DetalleordenesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public DetalleordenesJpaController() {
        emf = com.mycompany.proyectoprogra.controllers.JpaUtil.getEntityManagerFactory();
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalleordenes detalleordenes) throws PreexistingEntityException, Exception {
        if (detalleordenes.getDetalleordenesPK() == null) {
            detalleordenes.setDetalleordenesPK(new DetalleordenesPK());
        }
        detalleordenes.getDetalleordenesPK().setIdOrden(detalleordenes.getOrdenes().getIdOrden());
        detalleordenes.getDetalleordenesPK().setIdProducto(detalleordenes.getProductos().getIdProducto());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ordenes ordenes = detalleordenes.getOrdenes();
            if (ordenes != null) {
                ordenes = em.getReference(ordenes.getClass(), ordenes.getIdOrden());
                detalleordenes.setOrdenes(ordenes);
            }
            Productos productos = detalleordenes.getProductos();
            if (productos != null) {
                productos = em.getReference(productos.getClass(), productos.getIdProducto());
                detalleordenes.setProductos(productos);
            }
            em.persist(detalleordenes);
            if (ordenes != null) {
                ordenes.getDetalleordenesCollection().add(detalleordenes);
                ordenes = em.merge(ordenes);
            }
            if (productos != null) {
                productos.getDetalleordenesCollection().add(detalleordenes);
                productos = em.merge(productos);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleordenes(detalleordenes.getDetalleordenesPK()) != null) {
                throw new PreexistingEntityException("Detalleordenes " + detalleordenes + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalleordenes detalleordenes) throws NonexistentEntityException, Exception {
        detalleordenes.getDetalleordenesPK().setIdOrden(detalleordenes.getOrdenes().getIdOrden());
        detalleordenes.getDetalleordenesPK().setIdProducto(detalleordenes.getProductos().getIdProducto());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleordenes persistentDetalleordenes = em.find(Detalleordenes.class, detalleordenes.getDetalleordenesPK());
            Ordenes ordenesOld = persistentDetalleordenes.getOrdenes();
            Ordenes ordenesNew = detalleordenes.getOrdenes();
            Productos productosOld = persistentDetalleordenes.getProductos();
            Productos productosNew = detalleordenes.getProductos();
            if (ordenesNew != null) {
                ordenesNew = em.getReference(ordenesNew.getClass(), ordenesNew.getIdOrden());
                detalleordenes.setOrdenes(ordenesNew);
            }
            if (productosNew != null) {
                productosNew = em.getReference(productosNew.getClass(), productosNew.getIdProducto());
                detalleordenes.setProductos(productosNew);
            }
            detalleordenes = em.merge(detalleordenes);
            if (ordenesOld != null && !ordenesOld.equals(ordenesNew)) {
                ordenesOld.getDetalleordenesCollection().remove(detalleordenes);
                ordenesOld = em.merge(ordenesOld);
            }
            if (ordenesNew != null && !ordenesNew.equals(ordenesOld)) {
                ordenesNew.getDetalleordenesCollection().add(detalleordenes);
                ordenesNew = em.merge(ordenesNew);
            }
            if (productosOld != null && !productosOld.equals(productosNew)) {
                productosOld.getDetalleordenesCollection().remove(detalleordenes);
                productosOld = em.merge(productosOld);
            }
            if (productosNew != null && !productosNew.equals(productosOld)) {
                productosNew.getDetalleordenesCollection().add(detalleordenes);
                productosNew = em.merge(productosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetalleordenesPK id = detalleordenes.getDetalleordenesPK();
                if (findDetalleordenes(id) == null) {
                    throw new NonexistentEntityException("The detalleordenes with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetalleordenesPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleordenes detalleordenes;
            try {
                detalleordenes = em.getReference(Detalleordenes.class, id);
                detalleordenes.getDetalleordenesPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleordenes with id " + id + " no longer exists.", enfe);
            }
            Ordenes ordenes = detalleordenes.getOrdenes();
            if (ordenes != null) {
                ordenes.getDetalleordenesCollection().remove(detalleordenes);
                ordenes = em.merge(ordenes);
            }
            Productos productos = detalleordenes.getProductos();
            if (productos != null) {
                productos.getDetalleordenesCollection().remove(detalleordenes);
                productos = em.merge(productos);
            }
            em.remove(detalleordenes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalleordenes> findDetalleordenesEntities() {
        return findDetalleordenesEntities(true, -1, -1);
    }

    public List<Detalleordenes> findDetalleordenesEntities(int maxResults, int firstResult) {
        return findDetalleordenesEntities(false, maxResults, firstResult);
    }

    private List<Detalleordenes> findDetalleordenesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalleordenes.class));
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

    public Detalleordenes findDetalleordenes(DetalleordenesPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalleordenes.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleordenesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalleordenes> rt = cq.from(Detalleordenes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
