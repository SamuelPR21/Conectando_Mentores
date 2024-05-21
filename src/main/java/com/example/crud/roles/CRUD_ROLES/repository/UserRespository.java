package com.example.crud.roles.CRUD_ROLES.repository;

import com.example.crud.roles.CRUD_ROLES.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRespository extends JpaRepository<Users, Integer> {

    Optional <Users> findByUsername(String username);

    Boolean existsByUsername(String username);


}
