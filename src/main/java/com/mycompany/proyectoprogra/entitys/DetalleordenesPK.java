package com.mycompany.proyectoprogra.entitys;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class DetalleordenesPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_orden")
    private long idOrden;

    @Basic(optional = false)
    @Column(name = "id_producto")
    private long idProducto;

    public DetalleordenesPK() {
    }

    public DetalleordenesPK(long idOrden, long idProducto) {
        this.idOrden = idOrden;
        this.idProducto = idProducto;
    }

    public long getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(long idOrden) {
        this.idOrden = idOrden;
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Long.hashCode(idOrden);
        hash = 31 * hash + Long.hashCode(idProducto);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DetalleordenesPK)) {
            return false;
        }
        DetalleordenesPK other = (DetalleordenesPK) object;
        return this.idOrden == other.idOrden && this.idProducto == other.idProducto;
    }

    @Override
    public String toString() {
        return "com.mycompany.proyectoprogra.entitys.DetalleordenesPK[ idOrden=" + idOrden + ", idProducto=" + idProducto + " ]";
    }
}
