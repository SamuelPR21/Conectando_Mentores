package com.example.crud.roles.CRUD_ROLES.repository;

import com.example.crud.roles.CRUD_ROLES.model.ERole;
import com.example.crud.roles.CRUD_ROLES.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRespository extends JpaRepository<Roles, Integer> {
    Roles findByName(ERole name);

}
