package com.example.sbootporlles.services;

import com.example.sbootporlles.dto.DireccionDTO;
import com.example.sbootporlles.models.DireccionesModels;
import com.example.sbootporlles.models.UsuariosModels;
import com.example.sbootporlles.repositories.DireccionesRepository;
import com.example.sbootporlles.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DireccionesService {

    @Autowired
    private DireccionesRepository direccionesRepository;    @Autowired
    private UsuarioRepository usuariosRepository;

    // Obtener todas las direcciones de un usuario
    public List<DireccionDTO> obtenerDireccionesPorUsuario(Long usuarioId) {
        List<DireccionesModels> direcciones = direccionesRepository.findByUsuarioIdOrderByFechaCreacionDesc(usuarioId);
        return direcciones.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // Obtener dirección principal de un usuario
    public Optional<DireccionDTO> obtenerDireccionPrincipal(Long usuarioId) {
        Optional<DireccionesModels> direccion = direccionesRepository.findByUsuarioIdAndEsPrincipalTrue(usuarioId);
        return direccion.map(this::convertirADTO);
    }

    // Crear nueva dirección
    @Transactional
    public DireccionDTO crearDireccion(Long usuarioId, DireccionDTO direccionDTO) {
        Optional<UsuariosModels> usuarioOpt = usuariosRepository.findById(usuarioId);
        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado con ID: " + usuarioId);
        }

        UsuariosModels usuario = usuarioOpt.get();
        DireccionesModels direccion = new DireccionesModels();
          direccion.setUsuario(usuario);
        direccion.setDireccion(direccionDTO.getDireccion());
        direccion.setDepartamento(direccionDTO.getDepartamento());
        direccion.setProvincia(direccionDTO.getProvincia());
        direccion.setDistrito(direccionDTO.getDistrito());
        direccion.setAvenidaCalleJiron(direccionDTO.getAvenidaCalleJiron());
        direccion.setNumero(direccionDTO.getNumero());
        direccion.setCodigoPostal(direccionDTO.getCodigoPostal());
        direccion.setReferencia(direccionDTO.getReferencia());
        direccion.setEsPrincipal(direccionDTO.getEsPrincipal() != null ? direccionDTO.getEsPrincipal() : false);

        // Si esta dirección se marca como principal, desmarcar las demás
        if (direccion.getEsPrincipal()) {
            direccionesRepository.actualizarTodasDireccionesNoPrincipales(usuarioId);
        }

        // Si es la primera dirección del usuario, hacerla principal automáticamente
        if (!direccionesRepository.existsByUsuarioId(usuarioId)) {
            direccion.setEsPrincipal(true);
        }

        DireccionesModels direccionGuardada = direccionesRepository.save(direccion);
        return convertirADTO(direccionGuardada);
    }

    // Actualizar dirección existente
    @Transactional
    public DireccionDTO actualizarDireccion(Long usuarioId, Long direccionId, DireccionDTO direccionDTO) {
        Optional<DireccionesModels> direccionOpt = direccionesRepository.findById(direccionId);
        
        if (direccionOpt.isEmpty()) {
            throw new RuntimeException("Dirección no encontrada con ID: " + direccionId);
        }        DireccionesModels direccion = direccionOpt.get();
        
        // Verificar que la dirección pertenece al usuario
        if (direccion.getUsuario().getId() != usuarioId) {
            throw new RuntimeException("La dirección no pertenece al usuario especificado");
        }        // Actualizar campos
        direccion.setDireccion(direccionDTO.getDireccion());
        direccion.setDepartamento(direccionDTO.getDepartamento());
        direccion.setProvincia(direccionDTO.getProvincia());
        direccion.setDistrito(direccionDTO.getDistrito());
        direccion.setAvenidaCalleJiron(direccionDTO.getAvenidaCalleJiron());
        direccion.setNumero(direccionDTO.getNumero());
        direccion.setCodigoPostal(direccionDTO.getCodigoPostal());
        direccion.setReferencia(direccionDTO.getReferencia());

        // Manejar dirección principal
        if (direccionDTO.getEsPrincipal() != null && direccionDTO.getEsPrincipal()) {
            direccionesRepository.actualizarTodasDireccionesNoPrincipales(usuarioId);
            direccion.setEsPrincipal(true);
        }

        DireccionesModels direccionActualizada = direccionesRepository.save(direccion);
        return convertirADTO(direccionActualizada);
    }

    // Eliminar dirección
    @Transactional
    public void eliminarDireccion(Long usuarioId, Long direccionId) {
        Optional<DireccionesModels> direccionOpt = direccionesRepository.findById(direccionId);
        
        if (direccionOpt.isEmpty()) {
            throw new RuntimeException("Dirección no encontrada con ID: " + direccionId);
        }        DireccionesModels direccion = direccionOpt.get();
        
        // Verificar que la dirección pertenece al usuario
        if (direccion.getUsuario().getId() != usuarioId) {
            throw new RuntimeException("La dirección no pertenece al usuario especificado");
        }

        boolean eraPrincipal = direccion.getEsPrincipal();
        direccionesRepository.delete(direccion);

        // Si se eliminó la dirección principal, hacer principal a otra dirección
        if (eraPrincipal) {
            List<DireccionesModels> direccionesRestantes = direccionesRepository.findByUsuarioIdOrderByFechaCreacionDesc(usuarioId);
            if (!direccionesRestantes.isEmpty()) {
                DireccionesModels nuevaPrincipal = direccionesRestantes.get(0);
                nuevaPrincipal.setEsPrincipal(true);
                direccionesRepository.save(nuevaPrincipal);
            }
        }
    }

    // Establecer dirección como principal
    @Transactional
    public DireccionDTO establecerComoPrincipal(Long usuarioId, Long direccionId) {
        Optional<DireccionesModels> direccionOpt = direccionesRepository.findById(direccionId);
        
        if (direccionOpt.isEmpty()) {
            throw new RuntimeException("Dirección no encontrada con ID: " + direccionId);
        }        DireccionesModels direccion = direccionOpt.get();
        
        // Verificar que la dirección pertenece al usuario
        if (direccion.getUsuario().getId() != usuarioId) {
            throw new RuntimeException("La dirección no pertenece al usuario especificado");
        }

        // Desmarcar todas las direcciones como principales
        direccionesRepository.actualizarTodasDireccionesNoPrincipales(usuarioId);
        
        // Marcar esta dirección como principal
        direccion.setEsPrincipal(true);
        DireccionesModels direccionActualizada = direccionesRepository.save(direccion);
        
        return convertirADTO(direccionActualizada);
    }

    // Verificar si un usuario tiene direcciones
    public boolean usuarioTieneDirecciones(Long usuarioId) {
        return direccionesRepository.existsByUsuarioId(usuarioId);
    }

    // Contar direcciones de un usuario
    public long contarDireccionesPorUsuario(Long usuarioId) {
        return direccionesRepository.countByUsuarioId(usuarioId);
    }    // Método auxiliar para convertir entidad a DTO
    private DireccionDTO convertirADTO(DireccionesModels direccion) {
        DireccionDTO dto = new DireccionDTO();
        dto.setId(direccion.getId());
        dto.setDireccion(direccion.getDireccion());
        dto.setDepartamento(direccion.getDepartamento());
        dto.setProvincia(direccion.getProvincia());
        dto.setDistrito(direccion.getDistrito());
        dto.setAvenidaCalleJiron(direccion.getAvenidaCalleJiron());
        dto.setNumero(direccion.getNumero());
        dto.setCodigoPostal(direccion.getCodigoPostal());
        dto.setReferencia(direccion.getReferencia());
        dto.setEsPrincipal(direccion.getEsPrincipal());
        return dto;
    }
}
