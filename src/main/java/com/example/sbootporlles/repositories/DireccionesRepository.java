package com.example.sbootporlles.repositories;

import com.example.sbootporlles.models.DireccionesModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DireccionesRepository extends JpaRepository<DireccionesModels, Long> {
    
    // Encontrar todas las direcciones de un usuario
    List<DireccionesModels> findByUsuarioId(Long usuarioId);
    
    // Encontrar todas las direcciones de un usuario ordenadas por fecha de creación
    List<DireccionesModels> findByUsuarioIdOrderByFechaCreacionDesc(Long usuarioId);
    
    // Encontrar la dirección principal de un usuario
    Optional<DireccionesModels> findByUsuarioIdAndEsPrincipalTrue(Long usuarioId);
    
    // Verificar si un usuario tiene direcciones
    boolean existsByUsuarioId(Long usuarioId);
    
    // Contar direcciones de un usuario
    long countByUsuarioId(Long usuarioId);
    
    // Actualizar todas las direcciones de un usuario para que no sean principales
    @Modifying
    @Query("UPDATE DireccionesModels d SET d.esPrincipal = false WHERE d.usuario.id = :usuarioId")
    void actualizarTodasDireccionesNoPrincipales(@Param("usuarioId") Long usuarioId);
    
    // Buscar direcciones por usuario y texto en la dirección
    List<DireccionesModels> findByUsuarioIdAndDireccionContainingIgnoreCase(Long usuarioId, String texto);
}
