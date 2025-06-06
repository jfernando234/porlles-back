package com.example.sbootporlles.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sbootporlles.models.RolesModels;

public interface RolRepository extends JpaRepository<RolesModels, Long> {
    Optional<RolesModels> findByNombre(String nombre);
}