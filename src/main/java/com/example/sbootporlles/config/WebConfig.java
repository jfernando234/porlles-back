package com.example.sbootporlles.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        
        WebMvcConfigurer.super.addCorsMappings(registry);

         registry.addMapping("/**") // Acepta todas las rutas
                        .allowedOrigins("http://localhost:4200") // Permite el frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite los métodos HTTP
                        .allowedHeaders("X-Requested-With", "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"); // Permite los encabezados
    }
    
}
