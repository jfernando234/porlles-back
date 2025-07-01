package com.example.sbootporlles.dto;

public class DireccionDTO {
    private Long id;
    private String direccion;
    private String departamento;
    private String provincia;
    private String distrito;
    private String avenidaCalleJiron;
    private String numero;
    private String codigoPostal;
    private String referencia;
    private Boolean esPrincipal;

    // Constructores
    public DireccionDTO() {}

    public DireccionDTO(String direccion, String departamento, String provincia, String distrito,
                       String avenidaCalleJiron, String numero, String codigoPostal, 
                       String referencia, Boolean esPrincipal) {
        this.direccion = direccion;
        this.departamento = departamento;
        this.provincia = provincia;
        this.distrito = distrito;
        this.avenidaCalleJiron = avenidaCalleJiron;
        this.numero = numero;
        this.codigoPostal = codigoPostal;
        this.referencia = referencia;
        this.esPrincipal = esPrincipal;
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

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getAvenidaCalleJiron() {
        return avenidaCalleJiron;
    }

    public void setAvenidaCalleJiron(String avenidaCalleJiron) {
        this.avenidaCalleJiron = avenidaCalleJiron;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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

    // Método helper para obtener la dirección completa
    public String getDireccionCompleta() {
        StringBuilder sb = new StringBuilder();
        
        if (avenidaCalleJiron != null && !avenidaCalleJiron.trim().isEmpty()) {
            sb.append(avenidaCalleJiron);
            
            if (numero != null && !numero.trim().isEmpty()) {
                sb.append(" ").append(numero);
            }
        }
        
        if (distrito != null && !distrito.trim().isEmpty()) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(distrito);
        }
        
        if (provincia != null && !provincia.trim().isEmpty()) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(provincia);
        }
        
        if (departamento != null && !departamento.trim().isEmpty()) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(departamento);
        }
        
        return sb.toString();
    }
}
