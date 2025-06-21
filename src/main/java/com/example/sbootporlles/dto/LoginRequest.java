package com.example.sbootporlles.dto;

public class LoginRequest {
    private String nombreUsuario; // Puede ser nombreCompleto o correoElectronico
    private String contrasena;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    // Métodos adicionales para mayor flexibilidad
    public String getCorreoElectronico() {
        return nombreUsuario;
    }
    
    public String getNombreCompleto() {
        return nombreUsuario;
    }
}
