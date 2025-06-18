package com.example.sbootporlles.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Inventario")
public class InventarioModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_inventario   ;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private ProductoModel id_producto;

    @Column
    private int stock;

    @Column
    private String ubicacion;

    @Column
    private LocalDate fechaRegistro;

    public int getID_inventario() {
        return ID_inventario;
    }

    public void setID_inventario(int iD_inventario) {
        ID_inventario = iD_inventario;
    }

    public ProductoModel getId_producto() {
        return id_producto;
    }

    public void setId_producto(ProductoModel id_producto) {
        this.id_producto = id_producto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
}
