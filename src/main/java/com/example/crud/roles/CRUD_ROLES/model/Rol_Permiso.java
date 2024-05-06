package com.example.crud.roles.CRUD_ROLES.model;

import jakarta.persistence.OneToMany;

public class Rol_Permiso {
    private int id;

    @OneToMany
    private Users userPermiso;

    @OneToMany
    private Roles roleId;
}
