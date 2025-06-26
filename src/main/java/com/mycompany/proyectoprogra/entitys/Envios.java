package com.mycompany.proyectoprogra.entitys;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "envios")
@NamedQueries({
    @NamedQuery(name = "Envios.findAll", query = "SELECT e FROM Envios e"),
    @NamedQuery(name = "Envios.findByIdEnvio", query = "SELECT e FROM Envios e WHERE e.idEnvio = :idEnvio"),
    @NamedQuery(name = "Envios.findByModoEnvio", query = "SELECT e FROM Envios e WHERE e.modoEnvio = :modoEnvio"),
    @NamedQuery(name = "Envios.findByCiudad", query = "SELECT e FROM Envios e WHERE e.ciudad = :ciudad"),
    @NamedQuery(name = "Envios.findByEstado", query = "SELECT e FROM Envios e WHERE e.estado = :estado"),
    @NamedQuery(name = "Envios.findByCodigoPostal", query = "SELECT e FROM Envios e WHERE e.codigoPostal = :codigoPostal")
})
public class Envios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Basic(optional = false)
    @Column(name = "id_envio")
    private long idEnvio;

    @Basic(optional = false)
    @Column(name = "modo_envio")
    private String modoEnvio;

    @Basic(optional = false)
    @Column(name = "ciudad")
    private String ciudad;

    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;

    @Basic(optional = false)
    @Column(name = "codigo_postal")
    private int codigoPostal;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEnvio")
    private Collection<Ordenes> ordenesCollection;

    public Envios() {
    }

    public Envios(long idEnvio) {
        this.idEnvio = idEnvio;
    }

    public Envios(long idEnvio, String modoEnvio, String ciudad, String estado, int codigoPostal) {
        this.idEnvio = idEnvio;
        this.modoEnvio = modoEnvio;
        this.ciudad = ciudad;
        this.estado = estado;
        this.codigoPostal = codigoPostal;
    }

    public long getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(long idEnvio) {
        this.idEnvio = idEnvio;
    }

    public String getModoEnvio() {
        return modoEnvio;
    }

    public void setModoEnvio(String modoEnvio) {
        this.modoEnvio = modoEnvio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Collection<Ordenes> getOrdenesCollection() {
        return ordenesCollection;
    }

    public void setOrdenesCollection(Collection<Ordenes> ordenesCollection) {
        this.ordenesCollection = ordenesCollection;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(idEnvio);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Envios)) {
            return false;
        }
        Envios other = (Envios) object;
        return this.idEnvio == other.idEnvio;
    }

    @Override
    public String toString() {
        return "com.mycompany.proyectoprogra.entitys.Envios[ idEnvio=" + idEnvio + " ]";
    }
}
