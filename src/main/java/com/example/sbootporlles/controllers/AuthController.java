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
import com.example.sbootporlles.models.RolesModels;
import com.example.sbootporlles.models.UsuariosModels;
import com.example.sbootporlles.services.UsuarioService;


@RestController
@RequestMapping("/login")
public class AuthController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/inicio")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        System.out.println("Login endpoint called: " + request.getNombreUsuario());
        Optional<UsuariosModels> userOpt = usuarioService.obtenerPorNombreUsuario(request.getNombreUsuario());
        if (userOpt.isPresent()) {
            UsuariosModels usuario = userOpt.get();
            if (new BCryptPasswordEncoder().matches(request.getContrasena(), usuario.getContrasena())) {
                List<String> roles = usuario.getRoles().stream()
                    .map(RolesModels::getNombre)
                    .collect(Collectors.toList());

                Map<String, Object> response = new HashMap<>();
                response.put("usuario", usuario.getNombreUsuario());
                response.put("roles", roles);
                response.put("mensaje", "Login exitoso");

                return ResponseEntity.ok(response);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody UsuariosModels nuevo) {
        UsuariosModels user = usuarioService.registrarUsuario(nuevo, "CLIENTE");
        return ResponseEntity.ok(user);
    }
    @GetMapping("/prueba")
    public String prueba() {
        return "ok";
    }
}
