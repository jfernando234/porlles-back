package com.example.sbootporlles.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sbootporlles.dto.LoginRequest;
import com.example.sbootporlles.dto.RegistroRequest;
import com.example.sbootporlles.models.RolesModels;
import com.example.sbootporlles.models.UsuariosModels;
import com.example.sbootporlles.services.UsuarioService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UsuarioService usuarioService;    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        System.out.println("Login endpoint called: " + request.getNombreUsuario());
        Optional<UsuariosModels> userOpt = usuarioService.obtenerPorNombreUsuario(request.getNombreUsuario());
        if (userOpt.isPresent()) {
            UsuariosModels usuario = userOpt.get();
            if (new BCryptPasswordEncoder().matches(request.getContrasena(), usuario.getContrasena())) {                // Devolver información del usuario en formato consistente
                Map<String, Object> response = new HashMap<>();
                response.put("id", usuario.getId());
                response.put("nombreCompleto", usuario.getNombreCompleto());
                response.put("correoElectronico", usuario.getCorreoElectronico());
                response.put("nombre", usuario.getNombre());
                response.put("apellidos", usuario.getApellidos());
                response.put("rol", usuario.getRol()); // Usar el rol real del usuario
                response.put("rolId", 1); // ID por defecto
                response.put("fechaRegistro", usuario.getFechaRegistro());

                return ResponseEntity.ok(response);
            }
        }
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", "Usuario o contraseña incorrectos");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody RegistroRequest request) {
        try {
            System.out.println("Registro endpoint called: " + request.getNombre() + " " + request.getApellidos());
              // Crear el modelo de usuario con todos los campos
            UsuariosModels nuevo = new UsuariosModels();
            nuevo.setCorreoElectronico(request.getCorreoElectronico());
            nuevo.setNombre(request.getNombre());
            nuevo.setApellidos(request.getApellidos());
            nuevo.setTipoDocumento(request.getTipoDocumento());
            nuevo.setNumeroDocumento(request.getNumeroDocumento());
            nuevo.setCelular(request.getCelular());
            nuevo.setContrasena(request.getContrasena());
            // El rol se establece automáticamente en el @PrePersist como "CLIENTE"
            
            UsuariosModels user = usuarioService.registrarUsuario(nuevo, "CLIENTE");
            
            // Devolver la información del usuario en el mismo formato que el login
            Map<String, Object> response = new HashMap<>();
            response.put("id", user.getId());
            response.put("nombreCompleto", user.getNombreCompleto());
            response.put("correoElectronico", user.getCorreoElectronico());
            response.put("nombre", user.getNombre());
            response.put("apellidos", user.getApellidos());
            response.put("tipoDocumento", user.getTipoDocumento());
            response.put("numeroDocumento", user.getNumeroDocumento());
            response.put("celular", user.getCelular());
            response.put("rol", user.getRol()); // Usar el rol del usuario
            response.put("rolId", 1); // ID por defecto para rol USER
            response.put("fechaRegistro", user.getFechaRegistro());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error al registrar usuario: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
    @GetMapping("/prueba")
    public String prueba() {
        return "ok";
    }
}
