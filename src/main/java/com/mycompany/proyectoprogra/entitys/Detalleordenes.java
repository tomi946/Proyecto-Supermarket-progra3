package com.mycompany.proyectoprogra.entitys;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "detalleordenes")
@NamedQueries({
    @NamedQuery(name = "Detalleordenes.findAll", query = "SELECT d FROM Detalleordenes d"),
    @NamedQuery(name = "Detalleordenes.findByIdOrden", query = "SELECT d FROM Detalleordenes d WHERE d.detalleordenesPK.idOrden = :idOrden"),
    @NamedQuery(name = "Detalleordenes.findByIdProducto", query = "SELECT d FROM Detalleordenes d WHERE d.detalleordenesPK.idProducto = :idProducto"),
    @NamedQuery(name = "Detalleordenes.findByTotalVenta", query = "SELECT d FROM Detalleordenes d WHERE d.totalVenta = :totalVenta"),
    @NamedQuery(name = "Detalleordenes.findByCantidad", query = "SELECT d FROM Detalleordenes d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Detalleordenes.findByDescuento", query = "SELECT d FROM Detalleordenes d WHERE d.descuento = :descuento"),
    @NamedQuery(name = "Detalleordenes.findByGanancia", query = "SELECT d FROM Detalleordenes d WHERE d.ganancia = :ganancia")
})
public class Detalleordenes implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected DetalleordenesPK detalleordenesPK;

    @Basic(optional = false)
    @Column(name = "total_venta")
    private float totalVenta;

    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;

    @Basic(optional = false)
    @Column(name = "descuento")
    private float descuento;

    @Basic(optional = false)
    @Column(name = "ganancia")
    private float ganancia;

    @JoinColumn(name = "id_orden", referencedColumnName = "id_orden", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ordenes ordenes;

    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Productos productos;

    public Detalleordenes() {
    }

    public Detalleordenes(DetalleordenesPK detalleordenesPK) {
        this.detalleordenesPK = detalleordenesPK;
    }

    public Detalleordenes(DetalleordenesPK detalleordenesPK, float totalVenta, int cantidad, float descuento, float ganancia) {
        this.detalleordenesPK = detalleordenesPK;
        this.totalVenta = totalVenta;
        this.cantidad = cantidad;
        this.descuento = descuento;
        this.ganancia = ganancia;
    }

    public Detalleordenes(long idOrden, long idProducto) {
        this.detalleordenesPK = new DetalleordenesPK(idOrden, idProducto);
    }

    public DetalleordenesPK getDetalleordenesPK() {
        return detalleordenesPK;
    }

    public void setDetalleordenesPK(DetalleordenesPK detalleordenesPK) {
        this.detalleordenesPK = detalleordenesPK;
    }

    public float getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(float totalVenta) {
        this.totalVenta = totalVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public float getGanancia() {
        return ganancia;
    }

    public void setGanancia(float ganancia) {
        this.ganancia = ganancia;
    }

    public Ordenes getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(Ordenes ordenes) {
        this.ordenes = ordenes;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    @Override
    public int hashCode() {
        return (detalleordenesPK != null ? detalleordenesPK.hashCode() : 0);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Detalleordenes)) {
            return false;
        }
        Detalleordenes other = (Detalleordenes) object;
        return (this.detalleordenesPK != null && this.detalleordenesPK.equals(other.detalleordenesPK));
    }

    @Override
    public String toString() {
        return "com.mycompany.proyectoprogra.entitys.Detalleordenes[ detalleordenesPK=" + detalleordenesPK + " ]";
    }
}
