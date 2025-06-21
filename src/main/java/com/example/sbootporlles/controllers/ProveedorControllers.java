package com.example.sbootporlles.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/proveedores")
public class ProveedorControllers {
    @Autowired
    private ProveedorService proveedorService;

    @GetMapping ("/GetAll")
    public ArrayList<ProveedorModel> listarproveedor() {
        return this.proveedorService.listarProveedores();
    }
    @PostMapping("/crear")
    public Map<String, Object> nuevoProveedor(@RequestBody ProveedorModel obj) {
        ProveedorModel guardado = this.proveedorService.crearProveedor(obj);
        Map<String, Object> response = new HashMap<>();
        response.put("isSuccess", true);
        response.put("proveedor", guardado);
        return response;
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