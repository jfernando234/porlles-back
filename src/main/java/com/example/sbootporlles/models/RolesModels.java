package com.example.sbootporlles.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class RolesModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrol") // Cambiar a 'id' que es más estándar
    private Long idrol;
    
    @Column(name = "nombre")
    private String nombre;

    // Constructor por defecto
    public RolesModels() {}

    // Constructor con parámetros
    public RolesModels(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return idrol;
    }
    
    public void setId(Long id) {
        this.idrol = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {
        return "RolesModels{" +
                "id=" + idrol +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
