package com.example.sbootporlles.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.sbootporlles.models.UsuariosModels;

public interface UsuarioRepository extends JpaRepository<UsuariosModels, Long> {
    
    // Buscar por correo electrónico (identificador principal)
    Optional<UsuariosModels> findByCorreoElectronico(String correoElectronico);
    
    // Buscar por número de documento
    Optional<UsuariosModels> findByNumeroDocumento(String numeroDocumento);
    
    // Buscar por celular
    Optional<UsuariosModels> findByCelular(String celular);
    
    // Buscar por nombre y apellidos
    @Query("SELECT u FROM UsuariosModels u WHERE u.nombre = :nombre AND u.apellidos = :apellidos")
    Optional<UsuariosModels> findByNombreAndApellidos(@Param("nombre") String nombre, @Param("apellidos") String apellidos);
}