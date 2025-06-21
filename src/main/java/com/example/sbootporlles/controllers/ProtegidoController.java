package com.example.sbootporlles.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sbootporlles.services.UsuarioService;

@RestController
@RequestMapping("/api/protegido")
public class ProtegidoController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/perfil")
    public ResponseEntity<?> obtenerPerfil(Principal principal) {
        try {
            String correoElectronico = principal.getName();
            
            return usuarioService.obtenerPorCorreoElectronico(correoElectronico)
                .map(usuario -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("id", usuario.getId());
                    response.put("nombre", usuario.getNombre());
                    response.put("apellidos", usuario.getApellidos());
                    response.put("correoElectronico", usuario.getCorreoElectronico());
                    response.put("rol", usuario.getRol());
                    response.put("fechaRegistro", usuario.getFechaRegistro());
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
                
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error al obtener perfil del usuario");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/test")
    public ResponseEntity<?> test(Principal principal) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Acceso autorizado con JWT");
        response.put("usuario", principal.getName());
        return ResponseEntity.ok(response);
    }
}
