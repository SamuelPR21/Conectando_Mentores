package com.example.crud.roles.CRUD_ROLES.model;

import jakarta.persistence.OneToMany;

public class User_Rol {
    private int id;

    @OneToMany
    private Users userId;

    @OneToMany
    private Roles roleId;
}
