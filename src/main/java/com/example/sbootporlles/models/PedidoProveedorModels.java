package com.example.sbootporlles.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "PedidoProveedor")
public class PedidoProveedorModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int idPedidoProveedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private ProveedorModel idproveedor;

    @Column
    private LocalDate fechaPedido;

    @Column
    private LocalDate fechaEntregaEstimada;

    @Column
    private String estado;

    @Column
    private String incoterm;

    // Getters y setters

    public int getIdPedidoProveedor() {
        return idPedidoProveedor;
    }

    public void setIdPedidoProveedor(int idPedidoProveedor) {
        this.idPedidoProveedor = idPedidoProveedor;
    }

    public ProveedorModel getProveedor() {
        return idproveedor;
    }

    public void setProveedor(ProveedorModel proveedor) {
        this.idproveedor = proveedor;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public LocalDate getFechaEntregaEstimada() {
        return fechaEntregaEstimada;
    }

    public void setFechaEntregaEstimada(LocalDate fechaEntregaEstimada) {
        this.fechaEntregaEstimada = fechaEntregaEstimada;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIncoterm() {
        return incoterm;
    }

    public void setIncoterm(String incoterm) {
        this.incoterm = incoterm;
    }
}
