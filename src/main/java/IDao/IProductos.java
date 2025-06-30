/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IDao;

import com.mycompany.proyectoprogra.controllers.exceptions.IllegalOrphanException;
import com.mycompany.proyectoprogra.controllers.exceptions.NonexistentEntityException;
import com.mycompany.proyectoprogra.entitys.Productos;
import java.util.List;


public interface IProductos {
    
    public void create(Productos productos);
        
    public void edit(Productos productos) throws IllegalOrphanException, NonexistentEntityException, Exception;
       
    public void destroy(long id) throws IllegalOrphanException, NonexistentEntityException;
      
    public List<Productos> findProductosEntities();

    public List<Productos> findProductosEntities(int maxResults, int firstResult);

    public Productos findProductos(long id);  

    public int getProductosCount();
        
    
}
