package com.example.crud.roles.CRUD_ROLES.controller;

import com.example.crud.roles.CRUD_ROLES.Service.Implementaciones.UserDetailsImpl;
import com.example.crud.roles.CRUD_ROLES.Service.request.LoginRequest;
import com.example.crud.roles.CRUD_ROLES.response.UserInfoResponse;
import com.example.crud.roles.CRUD_ROLES.security.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/login")
public class LonginController {

    @Autowired
    private  final AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    public LonginController(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
   }

    @PostMapping("/user")
    public ResponseEntity<Void> loginUser(@RequestBody LoginRequest loginRequest) {

        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getUser_password());

        Authentication authentication= this.authenticationManager.authenticate(login);

        System.out.println(authentication.getPrincipal());

        String jwt = this.jwtUtils.createUser(loginRequest.getUsername());

        return  ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();

    }


    @PostMapping("/admin")
    public ResponseEntity<Void> loginAdmin(@RequestBody LoginRequest loginRequest) {

        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getUser_password());

        Authentication authentication= this.authenticationManager.authenticate(login);

        System.out.println(authentication.getPrincipal());

        String jwt = this.jwtUtils.createAdmin(loginRequest.getUsername());

        return  ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();

    }

}
