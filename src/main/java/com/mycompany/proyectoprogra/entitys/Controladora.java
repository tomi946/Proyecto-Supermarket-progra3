
package com.mycompany.proyectoprogra.entitys;

import com.mycompany.proyectoprogra.controllers.ControladoraController;
import com.mycompany.proyectoprogra.controllers.exceptions.IllegalOrphanException;
import com.mycompany.proyectoprogra.controllers.exceptions.NonexistentEntityException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controladora {
    
ControladoraController controladoraPao = new ControladoraController();


//CLIENTES    
public List<Clientes> traerClientes(){
    return controladoraPao.traerClientes();
}

public Clientes traerCliente(long id_cliente){
    return controladoraPao.traerCliente(id_cliente);
}

public void agregarCliente(String nombre_cliente) throws Exception{
    Clientes cliente = new Clientes();
    cliente.setNombreCliente(nombre_cliente);
    controladoraPao.agregarCliente(cliente);
}

public void eliminarCliente(long id){
    try {
        controladoraPao.borrarCliente(id);
    } catch (IllegalOrphanException ex) {
        Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
    }
}

public void modificarCliente(long id, String nuevoNombre) throws Exception { // IMPORTANTE: Agrega 'throws Exception'
        try {
            controladoraPao.modificarCliente(id, nuevoNombre); // Llama al método de ControladoraController
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, "Cliente no encontrado para modificar (desde Controladora)", ex);
            throw ex; // Relanza la excepción para que la IGU pueda manejarla
        } catch (Exception ex) {
            Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, "Error inesperado al modificar cliente (desde Controladora)", ex);
            throw ex; // Relanza cualquier otra excepción
        }
}

// PRODUCTOS

public void agregarProducto(String nombreProducto, double precioUnitario,Categorias categoria) throws Exception {
    Productos producto = new Productos();
    producto.setNombreProducto(nombreProducto);
    producto.setIdCategoria(categoria);
    producto.setPrecioUnitario(precioUnitario);
    controladoraPao.agregarProducto(producto);
}

public void eliminarProducto(long idProducto) {
    try {
        controladoraPao.borrarProducto(idProducto);
    } catch (Exception ex) {
        Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
    }
}

public void modificarProducto(long idProducto, String nuevoNombre, double precioUnitario, Categorias nuevaCategoria) throws Exception {
    controladoraPao.modificarProducto(idProducto, nuevoNombre,precioUnitario, nuevaCategoria);
}

public List<Productos> traerProductos() {
    return controladoraPao.traerProductos();
}

public Productos traerProducto(long idProducto) {
    return controladoraPao.traerProducto(idProducto);
}

//Categorias

public List<Categorias> traerCategorias() {
    return controladoraPao.traerCategorias();
}

//envios
public void agregarEnvio(String modoEnvio, String ciudad, String estado, int codigoPostal){
    controladoraPao.agregarEnvio(modoEnvio, ciudad, estado, codigoPostal);
}
    
public Envios traerEnvio(long idEnvio){
        return controladoraPao.traerEnvio(idEnvio);
    }

//Orden
public boolean guardarOrdenCompleta(Date fechaOrden, long idCliente, String modoEnvio, String ciudad, String estado, int codigoPostal, List<Detalleordenes> detallesTemp){
    return controladoraPao.guardarOrdenCompleta(fechaOrden, idCliente, modoEnvio, ciudad, estado, codigoPostal, detallesTemp);
}
  
public Ordenes traerOrden(long idOrden) {
     return controladoraPao.traerOrden(idOrden);
 }
     
 public List<Ordenes> traerTodasLasOrdenes() {
        return controladoraPao.traerTodasLasOrdenes();
    }
    
 //Detalle Ordenes
 public List<Detalleordenes> traerDetalleOrdenes(){
     return controladoraPao.traerDetalleOrdenes();
 }
}
