
package IDao;

import com.mycompany.proyectoprogra.controllers.exceptions.IllegalOrphanException;
import com.mycompany.proyectoprogra.controllers.exceptions.NonexistentEntityException;
import com.mycompany.proyectoprogra.controllers.exceptions.PreexistingEntityException;
import com.mycompany.proyectoprogra.entitys.Categorias;
import java.util.List;


public interface ICategoriasDao {
    
    public void create(Categorias categorias) throws PreexistingEntityException, Exception;
       
    public void edit(Categorias categorias) throws IllegalOrphanException, NonexistentEntityException, Exception; 
       
    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException; 

    public List<Categorias> findCategoriasEntities();

    public List<Categorias> findCategoriasEntities(int maxResults, int firstResult);

    public Categorias findCategorias(Integer id);

    public int getCategoriasCount();
    
}
