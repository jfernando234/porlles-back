package com.example.sbootporlles.dto;

public class RegistroRequest {
    private String correoElectronico;
    private String nombre;
    private String apellidos;
    private String tipoDocumento;
    private String numeroDocumento;
    private String celular;
    private String contrasena;
    private String rol; // Campo opcional, por defecto será "CLIENTE"

    // Constructor vacío
    public RegistroRequest() {}    // Constructor con parámetros
    public RegistroRequest(String correoElectronico, String nombre, String apellidos, 
                          String tipoDocumento, String numeroDocumento, String celular, String contrasena) {
        this.correoElectronico = correoElectronico;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.celular = celular;
        this.contrasena = contrasena;
        this.rol = "CLIENTE"; // Rol por defecto
    }

    // Getters y Setters
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getContrasena() {
        return contrasena;
    }    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    // Métodos de compatibilidad
    public String getNombreCompleto() {
        return (nombre != null ? nombre : "") + " " + (apellidos != null ? apellidos : "");
    }

    public void setNombreCompleto(String nombreCompleto) {
        if (nombreCompleto != null && !nombreCompleto.trim().isEmpty()) {
            String[] partes = nombreCompleto.trim().split(" ", 2);
            this.nombre = partes[0];
            this.apellidos = partes.length > 1 ? partes[1] : "";
        }
    }
}
