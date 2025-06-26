package com.mycompany.proyectoprogra.entitys;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "clientes")
@NamedQueries({
    @NamedQuery(name = "Clientes.findAll", query = "SELECT c FROM Clientes c"),
    @NamedQuery(name = "Clientes.findByIdCliente", query = "SELECT c FROM Clientes c WHERE c.idCliente = :idCliente"),
    @NamedQuery(name = "Clientes.findByNombreCliente", query = "SELECT c FROM Clientes c WHERE c.nombreCliente = :nombreCliente")
})
public class Clientes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cliente")
    private long idCliente;

    @Basic(optional = false)
    @Column(name = "nombre_cliente")
    private String nombreCliente;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCliente")
    private Collection<Ordenes> ordenesCollection;

    // Constructor vacío
    public Clientes() {
    }

    // Constructor solo con id
    public Clientes(long idCliente) {
        this.idCliente = idCliente;
    }

    // Constructor con todos los atributos
    public Clientes(long idCliente, String nombreCliente) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Collection<Ordenes> getOrdenesCollection() {
        return ordenesCollection;
    }

    public void setOrdenesCollection(Collection<Ordenes> ordenesCollection) {
        this.ordenesCollection = ordenesCollection;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(idCliente);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Clientes)) {
            return false;
        }
        Clientes other = (Clientes) object;
        return this.idCliente == other.idCliente;
    }

    @Override
    public String toString() {
        return idCliente + " - " + nombreCliente;
    }

}
