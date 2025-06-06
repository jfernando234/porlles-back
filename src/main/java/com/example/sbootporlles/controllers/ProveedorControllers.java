package com.example.sbootporlles.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sbootporlles.models.ProveedorModel;
import com.example.sbootporlles.services.ProveedorService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/Proveedores")
public class ProveedorControllers {
    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public ArrayList<ProveedorModel> listarproveedor() {
        return this.proveedorService.listarProveedores();
    }
    @GetMapping("/crear")
    public ProveedorModel nuevoProveedor(@RequestBody ProveedorModel obj) {
        System.out.println(obj.getNombre()+"::");
        return this.proveedorService.crearProveedor(obj);
    }
    @PutMapping(path = "/{id}")
    public ProveedorModel actualizarProveedor(@RequestBody ProveedorModel request, @PathVariable("id") int id) {
        //TODO: process PUT request
        return this.proveedorService.actulizarProveedor(request, id);
    }
    @DeleteMapping(path = "/{id}")
    public String eliminarProveedor(@PathVariable("id") int id) {
        boolean eliminado = this.proveedorService.eliminarProveedor(id);
        if (eliminado) {
            return "Proveedor eliminado correctamente";
        } else {
            return "Error al eliminar el proveedor";
        }
    }

}