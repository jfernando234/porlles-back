package com.example.sbootporlles.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "Roles")
public class RolesModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  idrol;
    @Column
    private String nombre;

    public long getIdrol() {
        return idrol;
    }
    public void setIdrol(long idrol) {
        this.idrol = idrol;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
