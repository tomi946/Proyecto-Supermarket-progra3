
package IDao;

import com.mycompany.proyectoprogra.controllers.exceptions.IllegalOrphanException;
import com.mycompany.proyectoprogra.controllers.exceptions.NonexistentEntityException;
import com.mycompany.proyectoprogra.controllers.exceptions.PreexistingEntityException;
import com.mycompany.proyectoprogra.entitys.Envios;
import java.util.List;


public interface IEnvios {
     
    public void create(Envios envios) throws PreexistingEntityException, Exception;
        
    public void edit(Envios envios) throws IllegalOrphanException, NonexistentEntityException, Exception;
      
    public void destroy(long id) throws IllegalOrphanException, NonexistentEntityException;

    public List<Envios> findEnviosEntities();
       
    public List<Envios> findEnviosEntities(int maxResults, int firstResult);
        
    public Envios findEnvios(long id);
       
    public int getEnviosCount();
      
}
