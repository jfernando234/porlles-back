package com.example.sbootporlles.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sbootporlles.models.ProveedorModel;

@Repository
public interface ProveedorRepository extends JpaRepository<ProveedorModel, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, para buscar proveedores por nombre o país

}
