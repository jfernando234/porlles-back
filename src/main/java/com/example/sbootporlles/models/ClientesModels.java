package com.example.sbootporlles.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Clientes")
public class ClientesModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCliente;

    @Column
    private String nombre;

    @Column
    private String rucDni;

    @Column
    private String direccion;

    @Column
    private String correoElectronico;

    @Column
    private String telefono;

    @Column
    private LocalDate fechaRegistro;

    // Getters y setters

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRucDni() {
        return rucDni;
    }

    public void setRucDni(String rucDni) {
        this.rucDni = rucDni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
