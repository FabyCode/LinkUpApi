package com.linkup.api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.linkup.api.model.Emprendedor;
import com.linkup.api.model.Mentor;
import com.linkup.api.model.Usuario;
import com.linkup.api.repository.EmprendedorRepository;
import com.linkup.api.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.auth0.jwt.algorithms.Algorithm;

@Service
public class TokenService {
    public String generarTokenEmprendedor(Emprendedor emprendedor) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            return JWT.create()
                    .withIssuer("LinkUp")
                    .withSubject(emprendedor.getPrincipalId())
                    .withExpiresAt(expirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error al generar el token", exception);
        }
    }

    public String generarTokenMentor(Mentor mentor) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            return JWT.create()
                    .withIssuer("LinkUp")
                    .withSubject(mentor.getPrincipalId())
                    .withExpiresAt(expirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error al generar el token", exception);
        }
    }

    public String getSubject(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            DecodedJWT jwt = JWT.require(algorithm)
                    .withIssuer("LinkUp")
                    .build()
                    .verify(token);
            return jwt.getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido", exception);
        }
    }

    public boolean validateToken(String token, Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWT.require(algorithm)
                    .withIssuer("LinkUp")
                    .withSubject(usuario.getPrincipalId())  // se obtiene el principalId de cualquier usuario (Mentor o Emprendedor)
                    .build()
                    .verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    private Instant expirationDate() {
        return LocalDateTime.now().plusDays(5).toInstant(ZoneOffset.of("-05:00"));
    }
}
