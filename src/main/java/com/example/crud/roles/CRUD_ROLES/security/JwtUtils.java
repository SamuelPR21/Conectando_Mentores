package com.example.crud.roles.CRUD_ROLES.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.crud.roles.CRUD_ROLES.Service.Implementaciones.UserDetailsImpl;
import io.jsonwebtoken.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;
import org.springframework.web.util.WebUtils;

import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.springframework.security.config.Elements.JWT;


@Component
public class JwtUtils {

    private  static  String SECRET_KEY = "el_samu";
    private  static Algorithm ALGORITH = Algorithm.HMAC256(SECRET_KEY);

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${pw.app.jwtSecret}")
    private String jwtSecret;

    @Value("${pw.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Value("${pw.app.jwtCookieName}")
    private String jwtCookie;

    //Obtener el token de las solictudes  Http
    public String getJwtFromCookies(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, jwtCookie);
        if (cookie != null) {
            return cookie.getValue();
        } else {
            return null;
        }
    }


    //Para iniciar sesion
    public String createUser(String username){
        return com.auth0.jwt.JWT.create()
                .withSubject(username)
                .withIssuer("USER")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)))
                .sign(ALGORITH);

    }

    public String createAdmin(String username){
        return com.auth0.jwt.JWT.create()
                .withSubject(username)
                .withIssuer("ADMIN")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)))
                .sign(ALGORITH);

    }

    //Genera un cookie con el token con informacion del usuario
    public ResponseCookie generateJwtCookie(UserDetailsImpl userPrincipal) {
        String jwt = generateTokenFromUsername(userPrincipal.getUsername());
        ResponseCookie cookie = ResponseCookie.from(jwtCookie, jwt).path("/api").maxAge(24 * 60 * 60).httpOnly(true).build();
        return cookie;
    }


    //Extrae el nombre de usuario del token JWT
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }


    //Evalua si un token es valido
    public boolean validateJwtToken(String jwt) {

        try {
            com.auth0.jwt.JWT.require(ALGORITH)
                    .build()
                    .verify(jwt);

            return true;
        }catch (JWTVerificationException e){
            return false;
        }

    }

    //Para saber a que usuario le corresponde el token
    public String generateTokenFromUsername(String jwt) {
        return com.auth0.jwt.JWT.require(ALGORITH)
                .build()
                .verify(jwt)
                .getSubject();
    }


    //Crear una cookie vacía para eliminar el token JWT
    public ResponseCookie getCleanJwtCookie() {
        ResponseCookie cookie = ResponseCookie.from(jwtCookie, null).path("/api").build();
        return cookie;
    }

}
