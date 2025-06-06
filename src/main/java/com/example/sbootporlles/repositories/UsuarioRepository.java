package com.example.sbootporlles.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sbootporlles.models.UsuariosModels;

public interface UsuarioRepository extends JpaRepository<UsuariosModels, Long> {
    Optional<UsuariosModels> findByNombreUsuario(String nombreUsuario);
}