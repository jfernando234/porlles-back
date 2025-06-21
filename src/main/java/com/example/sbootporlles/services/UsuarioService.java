package com.example.sbootporlles.services;

import java.util.Optional;

import com.example.sbootporlles.models.UsuariosModels;

public interface UsuarioService {
    UsuariosModels registrarUsuario(UsuariosModels usuario, String nombreRol);
    Optional<UsuariosModels> obtenerPorNombreUsuario(String nombreUsuario);
    Optional<UsuariosModels> obtenerPorCorreoElectronico(String correoElectronico);
}
