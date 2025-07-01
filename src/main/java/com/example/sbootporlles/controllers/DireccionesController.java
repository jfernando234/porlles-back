package com.example.sbootporlles.controllers;

import com.example.sbootporlles.dto.DireccionDTO;
import com.example.sbootporlles.models.UsuariosModels;
import com.example.sbootporlles.repositories.UsuarioRepository;
import com.example.sbootporlles.services.DireccionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/direcciones")
@CrossOrigin(origins = "*")
public class DireccionesController {    @Autowired
    private DireccionesService direccionesService;

    @Autowired
    private UsuarioRepository usuarioRepository;    // Obtener todas las direcciones del usuario autenticado
    @GetMapping
    public ResponseEntity<Map<String, Object>> obtenerDirecciones() {
        Map<String, Object> response = new HashMap<>();
        try {
            Long usuarioId = obtenerUsuarioIdAutenticado();
            List<DireccionDTO> direcciones = direccionesService.obtenerDireccionesPorUsuario(usuarioId);
            
            response.put("success", true);
            response.put("data", direcciones);
            
            if (direcciones.isEmpty()) {
                response.put("message", "No tienes direcciones registradas aún");
                response.put("hasAddresses", false);
            } else {
                response.put("message", "Direcciones obtenidas exitosamente");
                response.put("hasAddresses", true);
                response.put("totalAddresses", direcciones.size());
            }
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error al obtener las direcciones: " + e.getMessage());
            response.put("hasAddresses", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }    // Obtener la dirección principal del usuario autenticado
    @GetMapping("/principal")
    public ResponseEntity<Map<String, Object>> obtenerDireccionPrincipal() {
        Map<String, Object> response = new HashMap<>();
        try {
            Long usuarioId = obtenerUsuarioIdAutenticado();
            Optional<DireccionDTO> direccionPrincipal = direccionesService.obtenerDireccionPrincipal(usuarioId);
            
            if (direccionPrincipal.isPresent()) {
                response.put("success", true);
                response.put("data", direccionPrincipal.get());
                response.put("message", "Dirección principal obtenida exitosamente");
                response.put("hasPrincipalAddress", true);
                return ResponseEntity.ok(response);
            } else {
                response.put("success", true);
                response.put("data", null);
                response.put("message", "Aún no cuentas con una dirección principal. Agrega tu primera dirección.");
                response.put("hasPrincipalAddress", false);
                return ResponseEntity.ok(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error al obtener la dirección principal: " + e.getMessage());
            response.put("hasPrincipalAddress", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Crear nueva dirección
    @PostMapping
    public ResponseEntity<Map<String, Object>> crearDireccion(@RequestBody DireccionDTO direccionDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            Long usuarioId = obtenerUsuarioIdAutenticado();
            DireccionDTO nuevaDireccion = direccionesService.crearDireccion(usuarioId, direccionDTO);
            
            response.put("success", true);
            response.put("data", nuevaDireccion);
            response.put("message", "Dirección creada exitosamente");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error al crear la dirección: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // Actualizar dirección existente
    @PutMapping("/{direccionId}")
    public ResponseEntity<Map<String, Object>> actualizarDireccion(
            @PathVariable Long direccionId, 
            @RequestBody DireccionDTO direccionDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            Long usuarioId = obtenerUsuarioIdAutenticado();
            DireccionDTO direccionActualizada = direccionesService.actualizarDireccion(usuarioId, direccionId, direccionDTO);
            
            response.put("success", true);
            response.put("data", direccionActualizada);
            response.put("message", "Dirección actualizada exitosamente");
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error al actualizar la dirección: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // Eliminar dirección
    @DeleteMapping("/{direccionId}")
    public ResponseEntity<Map<String, Object>> eliminarDireccion(@PathVariable Long direccionId) {
        Map<String, Object> response = new HashMap<>();
        try {
            Long usuarioId = obtenerUsuarioIdAutenticado();
            direccionesService.eliminarDireccion(usuarioId, direccionId);
            
            response.put("success", true);
            response.put("message", "Dirección eliminada exitosamente");
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error al eliminar la dirección: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // Establecer dirección como principal
    @PutMapping("/{direccionId}/principal")
    public ResponseEntity<Map<String, Object>> establecerComoPrincipal(@PathVariable Long direccionId) {
        Map<String, Object> response = new HashMap<>();
        try {
            Long usuarioId = obtenerUsuarioIdAutenticado();
            DireccionDTO direccionPrincipal = direccionesService.establecerComoPrincipal(usuarioId, direccionId);
            
            response.put("success", true);
            response.put("data", direccionPrincipal);
            response.put("message", "Dirección establecida como principal exitosamente");
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error al establecer dirección como principal: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }    // Verificar si el usuario tiene direcciones registradas
    @GetMapping("/verificar")
    public ResponseEntity<Map<String, Object>> verificarDirecciones() {
        Map<String, Object> response = new HashMap<>();
        try {
            Long usuarioId = obtenerUsuarioIdAutenticado();
            boolean tieneDirecciones = direccionesService.usuarioTieneDirecciones(usuarioId);
            long cantidadDirecciones = direccionesService.contarDireccionesPorUsuario(usuarioId);
            
            response.put("success", true);
            response.put("tieneDirecciones", tieneDirecciones);
            response.put("cantidadDirecciones", cantidadDirecciones);
            
            if (tieneDirecciones) {
                response.put("message", "El usuario tiene " + cantidadDirecciones + " direccion(es) registrada(s)");
            } else {
                response.put("message", "El usuario aún no cuenta con direcciones registradas");
            }
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error al verificar las direcciones: " + e.getMessage());
            response.put("tieneDirecciones", false);
            response.put("cantidadDirecciones", 0);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Método auxiliar para obtener el ID del usuario autenticado
    private Long obtenerUsuarioIdAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Usuario no autenticado");
        }
        
        // Obtener el email del usuario autenticado
        String email = authentication.getName();
        
        // Buscar el usuario por email
        Optional<UsuariosModels> usuarioOpt = usuarioRepository.findByCorreoElectronico(email);
        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado con email: " + email);
        }
        
        return usuarioOpt.get().getId();
    }
}
