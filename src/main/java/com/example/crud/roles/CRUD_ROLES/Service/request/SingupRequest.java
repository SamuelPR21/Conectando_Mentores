package com.example.crud.roles.CRUD_ROLES.Service.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class SingupRequest {
    @NotBlank
    @Size(min = 3, max = 15)
    private String username;


    //para le Asigna el role
    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotBlank
    private String user_name;
    @NotBlank

    private String user_apellido;


    //Metodos
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_apellido() {
        return user_apellido;
    }

    public void setUser_apellido(String user_apellido) {
        this.user_apellido = user_apellido;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
