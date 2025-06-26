package com.mycompany.proyectoprogra.entitys;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "productos")
@NamedQueries({
    @NamedQuery(name = "Productos.findAll", query = "SELECT p FROM Productos p"),
    @NamedQuery(name = "Productos.findByIdProducto", query = "SELECT p FROM Productos p WHERE p.idProducto = :idProducto")
})
public class Productos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_producto")
    private long idProducto;

    @Basic(optional = false)
    @Lob
    @Column(name = "nombre_producto")
    private String nombreProducto;
    
    private double precioUnitario;

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productos", fetch = FetchType.EAGER)
    private Collection<Detalleordenes> detalleordenesCollection;

    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Categorias idCategoria;

    public Productos() {
    }

    public Productos(long idProducto) {
        this.idProducto = idProducto;
    }

    public Productos(long idProducto, String nombreProducto, double precioUnitario) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precioUnitario = precioUnitario;
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Collection<Detalleordenes> getDetalleordenesCollection() {
        return detalleordenesCollection;
    }

    public void setDetalleordenesCollection(Collection<Detalleordenes> detalleordenesCollection) {
        this.detalleordenesCollection = detalleordenesCollection;
    }

    public Categorias getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categorias idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(idProducto);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Productos)) {
            return false;
        }
        Productos other = (Productos) object;
        return this.idProducto == other.idProducto;
    }

    @Override
    public String toString() {
        return "com.mycompany.proyectoprogra.entitys.Productos[ idProducto=" + idProducto + " ]";
    }
}
