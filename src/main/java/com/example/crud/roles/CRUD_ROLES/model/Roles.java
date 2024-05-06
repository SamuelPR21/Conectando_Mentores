package com.example.crud.roles.CRUD_ROLES.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rol_id;

    @Column(name = "rol_name")
    private String rolName;

}
