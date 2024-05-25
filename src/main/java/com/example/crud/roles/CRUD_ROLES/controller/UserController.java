package com.example.crud.roles.CRUD_ROLES.controller;

import com.example.crud.roles.CRUD_ROLES.model.Users;
import com.example.crud.roles.CRUD_ROLES.repository.UserRespository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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


    @PatchMapping("/users/editar/{id}")
    public Users editarUsuarios(@PathVariable Integer id, @RequestBody Map<String, Object> users){
        Optional<Users> usersExistente = userRespository.findById(id);
        if (usersExistente.isPresent()){
            Users userActual = usersExistente.get();

            if (users.containsKey("username")) {
                userActual.setUsername((String) users.get("username"));
            }
            if (users.containsKey("user_name")) {
                userActual.setUser_name((String) users.get("user_name"));
            }
            if (users.containsKey("user_apellido")) {
                userActual.setUser_apellido((String) users.get("user_apellido"));
            }

            return userRespository.save(userActual);
        }
        return null;
    }

    @GetMapping("/users/{id}")
    public Users obtenerUsuariosPorId(@PathVariable Integer id){
        return userRespository.findById(id).orElse(null);
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> elminarUsuarios(@PathVariable Integer id){
        if (userRespository.existsById(id)) {
            userRespository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
