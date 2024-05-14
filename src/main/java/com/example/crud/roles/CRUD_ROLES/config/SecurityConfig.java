package com.example.crud.roles.CRUD_ROLES.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //.csrf(AbstractHttpConfigurer::disable)
                //Para cada cosa se requiere autenticacion
                .authorizeHttpRequests(customizeRequests -> {
                            customizeRequests
                                    .requestMatchers(HttpMethod.POST, "/documents/**").permitAll()
                                    .requestMatchers(HttpMethod.GET, "/api/**").hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.POST, "/api/**").hasAnyRole("ADMIN", "CUSTOMER")
                                    //.requestMatchers(HttpMethod.POST, "/documents/**").hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.PATCH, "/api/**").hasRole("ADMIN")
                                    .anyRequest()
                                    .authenticated();
                        }
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
