package com.example.sbootporlles.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Especificaciones")
public class EspecificacionesModels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int idEspecificacion;
    @Column
    private String Procesador;
    @Column
    private String RAM;
    @Column
    private String Almacenamiento;
    @Column
    private String Pantalla;
    @Column
    private String GPU;
    @Column
    private String SistemaOperativo;
    @Column
    private String Bateria;
    @Column
    private String Puertos;

    public int getIdEspecificacion() {
        return idEspecificacion;
    }
    public void setIdEspecificacion(int idEspecificacion) {
        this.idEspecificacion = idEspecificacion;
    }
    public String getProcesador() {
        return Procesador;
    }
    public void setProcesador(String procesador) {
        Procesador = procesador;
    }
    public String getRAM() {
        return RAM;
    }
    public void setRAM(String rAM) {
        RAM = rAM;
    }
    public String getAlmacenamiento() {
        return Almacenamiento;
    }
    public void setAlmacenamiento(String almacenamiento) {
        Almacenamiento = almacenamiento;
    }
    public String getPantalla() {
        return Pantalla;
    }
    public void setPantalla(String pantalla) {
        Pantalla = pantalla;
    }
    public String getGPU() {
        return GPU;
    }
    public void setGPU(String gPU) {
        GPU = gPU;
    }
    public String getSistemaOperativo() {
        return SistemaOperativo;
    }
    public void setSistemaOperativo(String sistemaOperativo) {
        SistemaOperativo = sistemaOperativo;
    }
    public String getBateria() {
        return Bateria;
    }
    public void setBateria(String bateria) {
        Bateria = bateria;
    }
    public String getPuertos() {
        return Puertos;
    }
    public void setPuertos(String puertos) {
        Puertos = puertos;
    }
    
}
