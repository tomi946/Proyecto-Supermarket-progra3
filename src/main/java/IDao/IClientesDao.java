
package IDao;

import com.mycompany.proyectoprogra.controllers.exceptions.IllegalOrphanException;
import com.mycompany.proyectoprogra.controllers.exceptions.NonexistentEntityException;
import com.mycompany.proyectoprogra.controllers.exceptions.PreexistingEntityException;
import com.mycompany.proyectoprogra.entitys.Clientes;
import java.util.List;


public interface IClientesDao {

    public void create(Clientes clientes) throws PreexistingEntityException, Exception; 

    public void edit(Clientes clientes) throws IllegalOrphanException, NonexistentEntityException, Exception;

    public void destroy(long id) throws IllegalOrphanException, NonexistentEntityException ;

    public List<Clientes> findClientesEntities();

    public List<Clientes> findClientesEntities(int maxResults, int firstResult);
        
    public Clientes findClientes(long id) ;
      
    public Clientes findClienteConOrdenes(long id) ;
   
    public int getClientesCount() ;
}
