package com.example.crud.roles.CRUD_ROLES.controller;


import java.util.HashSet;
import java.util.Set;

import com.example.crud.roles.CRUD_ROLES.Service.request.SingupRequest;
import com.example.crud.roles.CRUD_ROLES.model.ERole;
import com.example.crud.roles.CRUD_ROLES.model.Roles;
import com.example.crud.roles.CRUD_ROLES.model.Users;
import com.example.crud.roles.CRUD_ROLES.repository.RolesRespository;
import com.example.crud.roles.CRUD_ROLES.repository.UserRespository;
import com.example.crud.roles.CRUD_ROLES.security.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/auth")
public class SignController {


    @Autowired
    UserRespository userRespository;

    @Autowired
    RolesRespository rolesRespository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;




    @PostMapping("/signup/user")

    public ResponseEntity<?> regitroUser(@Valid @RequestBody SingupRequest singupRequest) {

        if (userRespository.existsByUsername(singupRequest.getUsername())){
            return  ResponseEntity.badRequest().body("Erro: Username esta tomado");
        }




       //Crear nueva cuenta usuario

        Users user = new Users(
                singupRequest.getUsername(),
                encoder.encode(singupRequest.getPassword()),
                singupRequest.getUser_name(),
                singupRequest.getUser_apellido()
        );

        Set <String> strRoles = singupRequest.getRole();
        Set <Roles> roles = new HashSet<>();

        if (strRoles == null){

            Roles userRole = rolesRespository.findByName(ERole.ROLE_USER);
            if (userRole == null){
                userRole = new Roles();
                userRole.setName(ERole.ROLE_USER);
                rolesRespository.save(userRole);
            }
            roles.add(userRole);


        }else {

            strRoles.forEach(role -> {
                switch (role){
                    default:
                        Roles userRole = rolesRespository.findByName(ERole.ROLE_USER);
                        if (userRole != null){
                            new RuntimeException("Error: Rol no encontrado");
                        }
                        roles.add(userRole);
                }
                    });

        }

        user.setRoles(roles);
        userRespository.save(user);

        return ResponseEntity.ok("User registered successfully!");

    }


    @PostMapping("/signup/admin")
    public  ResponseEntity<?> registerAdmin(@Valid @RequestBody SingupRequest singupRequest){

        if (userRespository.existsByUsername(singupRequest.getUsername())){
            return  ResponseEntity.badRequest().body("Error: Username esta tomado");
        }


        if (singupRequest.getPassword() != "123456" ){
            return  ResponseEntity.badRequest().body("No es un admin");
        }

        //Crear nueva cuenta usuario

        Users user = new Users(
                singupRequest.getUsername(),
                encoder.encode(singupRequest.getPassword()),
                singupRequest.getUser_name(),
                singupRequest.getUser_apellido()
        );
        Set <String> strRoles = singupRequest.getRole();
        Set <Roles> roles = new HashSet<>();

        if (strRoles == null){

            Roles userRole = rolesRespository.findByName(ERole.ROLE_ADMIN);
            if (userRole == null){
                userRole = new Roles();
                userRole.setName(ERole.ROLE_ADMIN);
                rolesRespository.save(userRole);
            }
            roles.add(userRole);

        }else {

            strRoles.forEach(role -> {
                switch (role){
                    default:
                        Roles userRole = rolesRespository.findByName(ERole.ROLE_ADMIN);
                        if (userRole != null){
                            new RuntimeException("Error: Rol no encontrado");
                        }
                        roles.add(userRole);
                }
            });

        }

        user.setRoles(roles);
        userRespository.save(user);


        return  ResponseEntity.ok("User registered successfully!");

    }

    //Salir de sesion
    @PostMapping("/logout")
    public ResponseEntity<?> cerraUsuario(){
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("Salio de sesion");
    }

}
