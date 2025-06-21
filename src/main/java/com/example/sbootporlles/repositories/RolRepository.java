package com.example.sbootporlles.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sbootporlles.models.RolesModels;

@Repository
public interface RolRepository extends JpaRepository<RolesModels, Long> {
    Optional<RolesModels> findByNombre(String nombre);
}