package com.example.crud.roles.CRUD_ROLES.controller;

import com.example.crud.roles.CRUD_ROLES.model.Users;
import com.example.crud.roles.CRUD_ROLES.repository.UserRespository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private  UserRespository userRespository;

    @GetMapping("/users")
    public List<Users> obtenerTodosLosUsuarios(){
        return (List<Users>) userRespository.findAll();
    }

    @PostMapping("/users/nuevo")
    public Users agregarUsers(@RequestBody Users users){
        return userRespository.save(users);
    }

    @PatchMapping("/users/editar/{id}")
    public Users editarUsuarios(@PathVariable Integer id, @RequestBody Users users){
        Optional<Users> usersExistente = userRespository.findById(id);
        if (usersExistente.isPresent()){
            Users userActual = usersExistente.get();
            userActual.setUsername(users.getUsername());
            userActual.setUser_password(users.getUser_password());
            userActual.setUser_name(users.getUser_name());
            userActual.setUser_apellido(users.getUser_apellido());
            return userRespository.save(userActual);
        }
        return null;
    }

    @GetMapping("/users/{id}")
    public Users obtenerUsuariosPorId(@PathVariable Integer id){
        return userRespository.findById(id).orElse(null);
    }


    @DeleteMapping("/users/{id}")
    public void elminarUsuarios(@PathVariable Integer id){
        userRespository.deleteById(id);
    }


}
