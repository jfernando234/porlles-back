package com.example.sbootporlles.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.sbootporlles.models.UsuariosModels;
import com.example.sbootporlles.services.UsuarioService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String correoElectronico) throws UsernameNotFoundException {
        UsuariosModels usuario = usuarioService
            .obtenerPorCorreoElectronico(correoElectronico)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no existe con correo: " + correoElectronico));
        return new UserDetailsImpl(usuario);
    }
}
