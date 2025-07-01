package com.example.sbootporlles.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sbootporlles.config.TokenUtils;
import com.example.sbootporlles.dto.LoginRequest;
import com.example.sbootporlles.dto.RegistroRequest;
import com.example.sbootporlles.models.UsuariosModels;
import com.example.sbootporlles.services.UsuarioService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            System.out.println("Login endpoint called: " + request.getNombreUsuario());
            
            // Buscar usuario por correo electrónico
            Optional<UsuariosModels> userOpt = usuarioService.obtenerPorCorreoElectronico(request.getNombreUsuario());
            
            if (userOpt.isPresent()) {
                UsuariosModels usuario = userOpt.get();
                
                // Verificar contraseña
                if (passwordEncoder.matches(request.getContrasena(), usuario.getContrasena())) {
                    
                    // Generar token JWT
                    String token = TokenUtils.createToken(usuario.getNombre(), usuario.getCorreoElectronico());
                    
                    // Respuesta con token JWT
                    Map<String, Object> response = new HashMap<>();
                    response.put("token", token);
                    response.put("id", usuario.getId());
                    response.put("nombreCompleto", usuario.getNombreCompleto());
                    response.put("correoElectronico", usuario.getCorreoElectronico());
                    response.put("nombre", usuario.getNombre());
                    response.put("apellidos", usuario.getApellidos());
                    response.put("rol", usuario.getRol());
                    response.put("fechaRegistro", usuario.getFechaRegistro());

                    return ResponseEntity.ok(response);
                }
            }
            
        } catch (Exception e) {
            System.err.println("Error en login: " + e.getMessage());
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error interno del servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
        
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", "Usuario o contraseña incorrectos");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @PostMapping("/registrar")
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
            
            // Generar token JWT para el usuario recién registrado
            String token = TokenUtils.createToken(user.getNombre(), user.getCorreoElectronico());
            
            // Devolver la información del usuario con token JWT
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("id", user.getId());
            response.put("nombreCompleto", user.getNombreCompleto());
            response.put("correoElectronico", user.getCorreoElectronico());
            response.put("nombre", user.getNombre());
            response.put("apellidos", user.getApellidos());
            response.put("tipoDocumento", user.getTipoDocumento());
            response.put("numeroDocumento", user.getNumeroDocumento());
            response.put("celular", user.getCelular());
            response.put("rol", user.getRol());
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
        return "Endpoint de prueba funcionando con JWT";
    }
}
