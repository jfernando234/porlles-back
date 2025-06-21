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
    @Column(name = "id") // Cambiar a 'id' que es más estándar
    private Long id;
    
    @Column(name = "nombre")
    private String nombre;

    // Constructor por defecto
    public RolesModels() {}

    // Constructor con parámetros
    public RolesModels(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
