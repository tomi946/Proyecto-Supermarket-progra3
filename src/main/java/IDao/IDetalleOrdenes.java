
package IDao;

import com.mycompany.proyectoprogra.controllers.exceptions.NonexistentEntityException;
import com.mycompany.proyectoprogra.controllers.exceptions.PreexistingEntityException;
import com.mycompany.proyectoprogra.entitys.Detalleordenes;
import com.mycompany.proyectoprogra.entitys.DetalleordenesPK;
import java.util.List;


public interface IDetalleOrdenes {
    
    public void create(Detalleordenes detalleordenes) throws PreexistingEntityException, Exception;

    public void edit(Detalleordenes detalleordenes) throws NonexistentEntityException, Exception;

    public void destroy(DetalleordenesPK id) throws NonexistentEntityException;

    public List<Detalleordenes> findDetalleordenesEntities();
       
    public List<Detalleordenes> findDetalleordenesEntities(int maxResults, int firstResult);

    public Detalleordenes findDetalleordenes(DetalleordenesPK id);
       
    public int getDetalleordenesCount();
        
}
