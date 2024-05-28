package com.example.crud.roles.CRUD_ROLES.controller;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.crud.roles.CRUD_ROLES.Service.Implementaciones.UserDetailsImpl;
import com.example.crud.roles.CRUD_ROLES.Service.Implementaciones.UserDetailsServiceImpl;
import com.example.crud.roles.CRUD_ROLES.Service.request.LoginRequest;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/auth")
public class SignController  {

    @Autowired
    private  AuthenticationManager authenticationManager;

    @Autowired
    private UserRespository userRespository;

    @Autowired
    private RolesRespository rolesRespository;

    @Autowired
    private  UserDetailsServiceImpl userDetailsService;

    @Autowired
    private  JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @PostMapping("/signup/user")

    public ResponseEntity<?> regitroUser(@Valid @RequestBody SingupRequest singupRequest) {

        if (userRespository.existsByUsername(singupRequest.getUsername())){
            return  ResponseEntity.badRequest().body("Erro: Username esta tomado");
        }

       //Crear nueva cuenta usuario

        Users user = new Users(
                singupRequest.getUsername(),
                passwordEncoder.encode(singupRequest.getPassword()),
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

        return ResponseEntity.status(HttpStatus.CREATED).body("Creado");

    }
    @PostMapping("/generateToken")
        public ResponseEntity<Map> authenticateAndGetToken(@RequestBody LoginRequest loginRequest) { Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getUser_password()));
        if (authentication.isAuthenticated()) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
            Long userId = ((UserDetailsImpl) userDetails).getId();
            String username = userDetails.getUsername();
            String token = jwtUtils.generateToken(loginRequest.getUsername());

            String rol = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(", "));


            // Crear un mapa para almacenar el token y el ID del usuario
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("userId", userId.toString());
            response.put("Rol", rol);
            response.put("Username", username);

            return ResponseEntity.ok(response);


        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }

    }


    //Registro de Admin
    @PostMapping("/signup/admin")
    public  ResponseEntity<?> registerAdmin(@Valid @RequestBody SingupRequest singupRequest){

        if (userRespository.existsByUsername(singupRequest.getUsername())){
            return  ResponseEntity.badRequest().body("Error: Username esta tomado");
        }



        //Crear nueva cuenta usuario

        Users user = new Users(
                singupRequest.getUsername(),
                passwordEncoder.encode(singupRequest.getPassword()),
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


}
