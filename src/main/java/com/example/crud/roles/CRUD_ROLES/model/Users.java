package com.example.crud.roles.CRUD_ROLES.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "users",
        uniqueConstraints ={
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class Users {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int user_id;

    @NotBlank
    @Column(unique = true)
    private String username;

    @NotBlank
    private String user_password;



    private String user_name;
    private String user_apellido;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> roles = new HashSet<>();

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Documentos> documentos = new HashSet<>();


    // Constructor con parámetros username y password


    public Users(String username, String user_password, String user_name, String user_apellido) {
        this.username = username;
        this.user_password = user_password;
        this.user_name = user_name;
        this.user_apellido = user_apellido;
    }

    public Users() {

    }

//    public Set<Documentos> getDocumentos() {
//        return documentos;
//    }
//
//    public void setDocumentos(Set<Documentos> documentos) {
//        this.documentos = documentos;
//    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }



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

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }
}
