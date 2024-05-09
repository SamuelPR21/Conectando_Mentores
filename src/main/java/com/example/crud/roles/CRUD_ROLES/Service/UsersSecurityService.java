package com.example.crud.roles.CRUD_ROLES.Service;

import com.example.crud.roles.CRUD_ROLES.model.Users;
import com.example.crud.roles.CRUD_ROLES.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UsersSecurityService implements  UserDetailsService {

    private final UserRespository userRespository;

    @Autowired
    public UsersSecurityService(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users users =  userRespository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("Usuario: "+ username +" no encontrado"));


        return User.builder()
                .username(users.getUsername())
                .password(users.getUser_password())
                .roles("ADMIN")
                .accountLocked(users.getLocked())
                .disabled(users.getDisabled())
                .build();
    }
}
