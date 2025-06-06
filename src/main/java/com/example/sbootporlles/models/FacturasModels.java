package com.example.sbootporlles.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Facturas")
public class FacturasModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int idFactura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private PedidosModels idpedido;

    @Column
    private LocalDate fechaEmision;

    @Column
    private double montoTotal;

    @Column
    private String metodoPago;

    // Getters y setters

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public PedidosModels getPedido() {
        return idpedido;
    }

    public void setPedido(PedidosModels pedido) {
        this.idpedido = pedido;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
}
