package com.example.crud.roles.CRUD_ROLES.Service.Implementaciones;

import com.example.crud.roles.CRUD_ROLES.model.Users;
import com.example.crud.roles.CRUD_ROLES.repository.UserRespository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRespository userRespository;

    @Autowired
    PasswordEncoder encoder;

    public UserDetailsServiceImpl(UserRespository userRespository, PasswordEncoder encoder) {
        this.userRespository = userRespository;
        this.encoder = encoder;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRespository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
            users.getRoles().size();
            return UserDetailsImpl.build(users);

    }

    public String addUser(Users users){
        users.setUser_password(encoder.encode(users.getUser_password()));
        userRespository.save(users);
        return "User añadido satisfactoriamente";
    }



}
