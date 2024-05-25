package com.example.crud.roles.CRUD_ROLES.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;

public class addCorsMappings {
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/")
                .allowedOrigins("*") // Permitir desde cualquier origen
                .allowedMethods("*"); // Permitir todos los métodos HTTP
    }
}
