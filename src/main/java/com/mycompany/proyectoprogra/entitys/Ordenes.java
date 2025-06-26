package com.mycompany.proyectoprogra.entitys;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "ordenes")
@NamedQueries({
    @NamedQuery(name = "Ordenes.findAll", query = "SELECT o FROM Ordenes o"),
    @NamedQuery(name = "Ordenes.findByIdOrden", query = "SELECT o FROM Ordenes o WHERE o.idOrden = :idOrden"),
    @NamedQuery(name = "Ordenes.findByFechaOrden", query = "SELECT o FROM Ordenes o WHERE o.fechaOrden = :fechaOrden")
})
public class Ordenes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_orden")
    private long idOrden;

    @Basic(optional = false)
    @Column(name = "fecha_orden")
    @Temporal(TemporalType.DATE)
    private Date fechaOrden;

    @JoinColumn(name = "id_envio", referencedColumnName = "id_envio")
    @ManyToOne(optional = false)
    private Envios idEnvio;

    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    @ManyToOne(optional = false)
    private Clientes idCliente;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordenes")
    private Collection<Detalleordenes> detalleordenesCollection;

    public Ordenes() {
    }

    public Ordenes(long idOrden) {
        this.idOrden = idOrden;
    }

    public Ordenes(long idOrden, Date fechaOrden) {
        this.idOrden = idOrden;
        this.fechaOrden = fechaOrden;
    }

    public long getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(long idOrden) {
        this.idOrden = idOrden;
    }

    public Date getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public Envios getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(Envios idEnvio) {
        this.idEnvio = idEnvio;
    }

    public Clientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Clientes idCliente) {
        this.idCliente = idCliente;
    }

    public Collection<Detalleordenes> getDetalleordenesCollection() {
        return detalleordenesCollection;
    }

    public void setDetalleordenesCollection(Collection<Detalleordenes> detalleordenesCollection) {
        this.detalleordenesCollection = detalleordenesCollection;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(idOrden);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Ordenes)) {
            return false;
        }
        Ordenes other = (Ordenes) object;
        return this.idOrden == other.idOrden;
    }

    @Override
    public String toString() {
        return "com.mycompany.proyectoprogra.entitys.Ordenes[ idOrden=" + idOrden + " ]";
    }
}
