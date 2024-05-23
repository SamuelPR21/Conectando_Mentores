package com.example.crud.roles.CRUD_ROLES.security;

import com.example.crud.roles.CRUD_ROLES.Service.Implementaciones.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuthTokenFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);


    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;



    //Valida el token y extrae el username del dueño del token
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // Extrae el JWT de la solicitud
            String jwt = ParseJwt(request);

            // Valida el JWT
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                //Trabaja con el usuario
                String username = jwtUtils.getUserNameFromJwtToken(jwt);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // Crea una autenticación basada en el usuario
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails,
                                null,
                                userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Establece la autenticación en el contexto de seguridad
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }catch(Exception e){
                logger.error("Cannot set user authentication: {}",e);

            }
        // Continúa con la cadena de filtros
        filterChain.doFilter(request, response);
    }

    private String ParseJwt(HttpServletRequest request){

        // Extrae el JWT de las cookies de la solicitud
        return jwtUtils.getJwtFromCookies(request);
    }

}


