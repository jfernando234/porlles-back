package com.example.sbootporlles.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sbootporlles.models.RolesModels;
import com.example.sbootporlles.models.UsuariosModels;
import com.example.sbootporlles.repositories.RolRepository;
import com.example.sbootporlles.repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import jakarta.annotation.PostConstruct;



@Service
public class UsuarioServiceImp implements UsuarioService { 

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private RolRepository rolRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        // Crear roles básicos si no existen
        try {
            if (rolRepo.findByNombre("USER").isEmpty()) {
                RolesModels userRole = new RolesModels("USER");
                rolRepo.save(userRole);
                System.out.println("Rol USER creado");
            }
            
            if (rolRepo.findByNombre("ADMIN").isEmpty()) {
                RolesModels adminRole = new RolesModels("ADMIN");
                rolRepo.save(adminRole);
                System.out.println("Rol ADMIN creado");
            }
        } catch (Exception e) {
            System.err.println("Error al inicializar roles: " + e.getMessage());
        }
    }    @Override
    public UsuariosModels registrarUsuario(UsuariosModels usuario, String nombreRol) {
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        usuario.setHabilitado(true);
        
        // Validar que los campos obligatorios estén presentes
        if (usuario.getCorreoElectronico() == null || usuario.getCorreoElectronico().isEmpty()) {
            throw new RuntimeException("El correo electrónico es obligatorio");
        }
        
        if (usuario.getNombre() == null || usuario.getNombre().isEmpty()) {
            throw new RuntimeException("El nombre es obligatorio");
        }
        
        if (usuario.getApellidos() == null || usuario.getApellidos().isEmpty()) {
            throw new RuntimeException("Los apellidos son obligatorios");
        }
        
        // Buscar el rol o crear uno por defecto
        RolesModels rol = rolRepo.findByNombre(nombreRol)
            .orElseGet(() -> {
                // Si no existe el rol, crear uno nuevo
                RolesModels nuevoRol = new RolesModels(nombreRol);
                return rolRepo.save(nuevoRol);
            });
        usuario.getRoles().add(rol);
        return usuarioRepo.save(usuario);
    }    @Override
    public Optional<UsuariosModels> obtenerPorNombreUsuario(String nombreUsuario) {
        // Buscar por correo electrónico (que es el campo que usamos como identificador)
        return usuarioRepo.findByCorreoElectronico(nombreUsuario);
    }

    @Override
    public Optional<UsuariosModels> obtenerPorCorreoElectronico(String correoElectronico) {
        return usuarioRepo.findByCorreoElectronico(correoElectronico);
    }
}
