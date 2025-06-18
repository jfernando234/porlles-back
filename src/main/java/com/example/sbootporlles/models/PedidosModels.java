package com.example.sbootporlles.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Pedidos")
public class PedidosModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private int idPedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private ClientesModels idcliente;

    @Column
    private LocalDate fechaPedido;

    @Column
    private String estado;

    @Column
    private double total;

    // Getters y setters

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public ClientesModels getCliente() {
        return idcliente;
    }

    public void setCliente(ClientesModels cliente) {
        this.idcliente = cliente;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
