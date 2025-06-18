package com.example.sbootporlles.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sbootporlles.models.RolesModels;
import com.example.sbootporlles.models.UsuariosModels;
import com.example.sbootporlles.repositories.RolRepository;
import com.example.sbootporlles.repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;



@Service
public class UsuarioServiceImp implements UsuarioService { 

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private RolRepository rolRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

     @Override
    public UsuariosModels registrarUsuario(UsuariosModels usuario, String nombreRol) {
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        usuario.setHabilitado(true);
        RolesModels rol = rolRepo.findByNombre(nombreRol).orElseThrow();
        usuario.getRoles().add(rol);
        return usuarioRepo.save(usuario);
    }

    @Override
    public Optional<UsuariosModels> obtenerPorNombreUsuario(String nombreUsuario) {
        return usuarioRepo.findByNombreUsuario(nombreUsuario);
    }
}
