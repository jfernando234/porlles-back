package com.example.sbootporlles.dto;

import java.time.LocalDateTime;

public class DireccionResponseDTO {
    private Long id;
    private String direccion;
    private String ciudad;
    private String departamento;
    private String codigoPostal;
    private String referencia;
    private Boolean esPrincipal;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;

    // Constructores
    public DireccionResponseDTO() {}

    public DireccionResponseDTO(Long id, String direccion, String ciudad, String departamento, 
                               String codigoPostal, String referencia, Boolean esPrincipal,
                               LocalDateTime fechaCreacion, LocalDateTime fechaModificacion) {
        this.id = id;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.departamento = departamento;
        this.codigoPostal = codigoPostal;
        this.referencia = referencia;
        this.esPrincipal = esPrincipal;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Boolean getEsPrincipal() {
        return esPrincipal;
    }

    public void setEsPrincipal(Boolean esPrincipal) {
        this.esPrincipal = esPrincipal;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDateTime fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
}
