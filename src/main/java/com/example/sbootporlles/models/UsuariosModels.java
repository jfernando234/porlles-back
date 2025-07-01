package com.example.sbootporlles.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "Usuarios")
public class UsuariosModels {    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String nombre;
    
    @Column
    private String apellidos;
    
    @Column(name = "tipo_documento")
    private String tipoDocumento;
    
    @Column(name = "numero_documento")
    private String numeroDocumento;
    
    @Column(name = "correo_electronico")
    private String correoElectronico;
    
    @Column
    private String celular;
      @Column
    private String contrasena;
    
    @Column
    private String rol;
    
    @Column
    private Boolean habilitado;
    
    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "usuario_roles",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<RolesModels> roles = new HashSet<>();    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<DireccionesModels> direcciones = new HashSet<>();    @PrePersist
    protected void onCreate() {
        fechaRegistro = LocalDateTime.now();
        if (rol == null || rol.trim().isEmpty()) {
            rol = "CLIENTE";
        }
        if (habilitado == null) {
            habilitado = true;
        }
    }// Getters y Setters en orden lógico
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
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

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Set<RolesModels> getRoles() {
        return roles;
    }

    public void setRoles(Set<RolesModels> roles) {
        this.roles = roles;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }    // Métodos de compatibilidad para mantener el código existente
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

    public String getNombreUsuario() {
        return correoElectronico;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.correoElectronico = nombreUsuario;
    }

    public String getCorreo() {
        return correoElectronico;
    }

    public void setCorreo(String correo) {
        this.correoElectronico = correo;
    }

    public Set<DireccionesModels> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(Set<DireccionesModels> direcciones) {
        this.direcciones = direcciones;
    }
}
