package com.example.crud.roles.CRUD_ROLES.config;

import com.example.crud.roles.CRUD_ROLES.Service.Implementaciones.UserDetailsServiceImpl;
import com.example.crud.roles.CRUD_ROLES.security.AuthEntryPointJwt;
import com.example.crud.roles.CRUD_ROLES.security.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
@EnableWebSecurity
public class SecurityConfig {



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/login/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/v1/users").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PATCH,"/api/v1/users/editar/{id}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/v1/users/{id}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/users/{id}").hasAuthority("ADMIN")

                        //Por revisar
                        .requestMatchers(HttpMethod.POST, "/documents/enviable").hasAuthority("USER")
                        .requestMatchers(HttpMethod.GET, "/documents/downland").hasAuthority("USER")
//                        .requestMatchers(HttpMethod.POST, "/api/login/user").hasAuthority("USER")
//                        .requestMatchers(HttpMethod.GET, "/api/login/admin").hasAuthority("ADMIN")

                        .anyRequest()
                        .authenticated());

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticatioJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
       return http.build();

    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt authEntryPointJwt;

    @Bean
    public AuthTokenFilter authenticatioJwtTokenFilter(){
        return new AuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/")
//                .allowedOrigins("*") // Permitir desde cualquier origen
//                .allowedMethods("*"); // Permitir todos los métodos HTTP
//    }
}
