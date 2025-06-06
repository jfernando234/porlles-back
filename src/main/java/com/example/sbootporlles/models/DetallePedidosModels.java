package com.example.sbootporlles.models;

import jakarta.persistence.*;

@Entity
@Table(name = "DetallePedidos")
public class DetallePedidosModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int idDetalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private PedidosModels idpedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private ProductoModel idproducto;

    @Column
    private int cantidad;

    @Column
    private double precioUnitario;

    // Getters y setters

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public PedidosModels getPedido() {
        return idpedido;
    }

    public void setPedido(PedidosModels pedido) {
        this.idpedido = pedido;
    }

    public ProductoModel getProducto() {
        return idproducto;
    }

    public void setProducto(ProductoModel producto) {
        this.idproducto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
