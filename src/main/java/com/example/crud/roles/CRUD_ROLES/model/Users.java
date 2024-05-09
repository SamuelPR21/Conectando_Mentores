package com.example.crud.roles.CRUD_ROLES.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class Users {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int user_id;

    @Column(unique = true)
    private String username;
    private String user_password;
    private String user_name;
    private String user_apellido;

    @Column( columnDefinition = "BOOLEAN")
    private Boolean locked;

    @Column( columnDefinition = "BOOLEAN")
    private Boolean disabled;

    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<Roles> roles;
}
