package com.example.crud.roles.CRUD_ROLES.Service;

import com.example.crud.roles.CRUD_ROLES.model.Roles;
import com.example.crud.roles.CRUD_ROLES.model.Users;
import com.example.crud.roles.CRUD_ROLES.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


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
                .roles("ROLE_ADMIN", "ROLE_USER")
                .accountLocked(users.getLocked())
                .disabled(users.getDisabled())
                .build();
    }

    // Método para asignar roles a un usuario
    public void assignRolesToUser(String username, List<Roles> roles) {
        Users user = userRespository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario: " + username + " no encontrado"));
        user.getRoles().addAll(roles);
        userRespository.save(user);
    }

}
