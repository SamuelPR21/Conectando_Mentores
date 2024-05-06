package com.example.crud.roles.CRUD_ROLES.model;

import jakarta.persistence.*;

@Entity
@Table(name = "permisos")
public class Permisos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int permiso_id;
    private String permiso_name;
}
