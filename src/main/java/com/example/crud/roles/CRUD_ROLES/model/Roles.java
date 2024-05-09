package com.example.crud.roles.CRUD_ROLES.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@IdClass(User_Rol.class)
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rolId;

    @Column(nullable = false, length = 20)
    private String rolName;


    @Column(name = "granted_date", nullable = false, columnDefinition = "TIMESTAMP" )
    private LocalDate grantedDate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private Users users;
}
