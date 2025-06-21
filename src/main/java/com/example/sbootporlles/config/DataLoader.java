package com.example.sbootporlles.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.sbootporlles.models.RolesModels;
import com.example.sbootporlles.models.UsuariosModels;
import com.example.sbootporlles.repositories.RolRepository;
import com.example.sbootporlles.repositories.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class DataLoader implements CommandLineRunner {    @Autowired
    private UsuarioRepository usuariosRepository;
    
    @Autowired
    private RolRepository rolesRepository;
    
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void run(String... args) throws Exception {
        // Crear roles si no existen
        createRoleIfNotExists("USER");
        createRoleIfNotExists("ADMIN");
        
        // Crear usuarios predefinidos si no existen
        createUserIfNotExists(
            "cliente",      
            "cliente",      
            "Cliente",    
            "",             
            "CLIENTE"      
        );
        
        createUserIfNotExists(
            "empleado",
            "empleado",        
            "Empleado",     
            "",             
            "EMPLEADO"      
        );
        
        System.out.println("✅ Usuarios predefinidos creados correctamente:");
        System.out.println("   👤 Cliente - Email: cliente, Password: cliente");
        System.out.println("   🔧 Admin - Email: admin, Password: admin");
    }
    
    private void createRoleIfNotExists(String roleName) {
        Optional<RolesModels> existingRole = rolesRepository.findByNombre(roleName);
        if (existingRole.isEmpty()) {
            RolesModels role = new RolesModels();
            role.setNombre(roleName);
            rolesRepository.save(role);
            System.out.println("🔑 Rol creado: " + roleName);
        }
    }
    
    private void createUserIfNotExists(String email, String password, String nombre, String apellidos, String rol) {
        Optional<UsuariosModels> existingUser = usuariosRepository.findByCorreoElectronico(email);
        if (existingUser.isEmpty()) {
            UsuariosModels user = new UsuariosModels();
            user.setCorreoElectronico(email);
            user.setContrasena(passwordEncoder.encode(password));
            user.setNombre(nombre);
            user.setApellidos(apellidos);
            user.setRol(rol);
            
            usuariosRepository.save(user);
            System.out.println("👤 Usuario creado: " + email + " (" + rol + ")");
        } else {
            System.out.println("ℹ️  Usuario ya existe: " + email);
        }
    }
}
