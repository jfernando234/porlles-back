package com.example.sbootporlles.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sbootporlles.models.ProveedorModel;
import com.example.sbootporlles.repositories.ProveedorRepository;
@Service
public class ProveedorService {
    @Autowired
    ProveedorRepository proveedorRepository;
    public ArrayList<ProveedorModel> listarProveedores(){
        return (ArrayList<ProveedorModel>) proveedorRepository.findAll();
    }
    public ProveedorModel crearProveedor(ProveedorModel proveedor){
        return proveedorRepository.save(proveedor);
    }
    public ProveedorModel actulizarProveedor(ProveedorModel obj,int id){
        ProveedorModel e = proveedorRepository.findById(id).get();
        e.setNombre(obj.getNombre());
        e.setPais(obj.getPais());
        e.setContacto(obj.getContacto());
        e.setCorreoElectronico(obj.getCorreoElectronico());
        e.setTelefono(obj.getTelefono());
        e.setEstado(obj.getEstado());
        return e;
    }
    public boolean eliminarProveedor(int id){
        try {
            proveedorRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
