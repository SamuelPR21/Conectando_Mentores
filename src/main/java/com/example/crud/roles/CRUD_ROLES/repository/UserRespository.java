package com.example.crud.roles.CRUD_ROLES.repository;

import com.example.crud.roles.CRUD_ROLES.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<Users, Integer> {



}
