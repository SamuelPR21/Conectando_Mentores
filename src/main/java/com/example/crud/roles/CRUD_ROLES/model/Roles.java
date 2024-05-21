package com.example.crud.roles.CRUD_ROLES.model;

import jakarta.persistence.*;


@Entity
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private  Integer id;

    @Enumerated(EnumType.STRING)
    private  ERole name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}
