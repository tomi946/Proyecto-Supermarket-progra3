
package IDao;

import com.mycompany.proyectoprogra.controllers.exceptions.IllegalOrphanException;
import com.mycompany.proyectoprogra.controllers.exceptions.NonexistentEntityException;
import com.mycompany.proyectoprogra.controllers.exceptions.PreexistingEntityException;
import com.mycompany.proyectoprogra.entitys.Ordenes;
import java.util.List;


public interface IOrdenes {
    
    public void create(Ordenes ordenes) throws PreexistingEntityException, Exception;

    public void edit(Ordenes ordenes) throws IllegalOrphanException, NonexistentEntityException, Exception; 

    public void destroy(long id) throws IllegalOrphanException, NonexistentEntityException;

    public List<Ordenes> findOrdenesEntities();

    public List<Ordenes> findOrdenesEntities(int maxResults, int firstResult);

    public Ordenes findOrdenes(long id);

    public int getOrdenesCount();
}
