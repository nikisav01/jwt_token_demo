package com.example.demo.auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.demo.auth.controller.dto.TokensDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {

    private static final Integer EXPIRATION_TIME = 1000 * 60 * 10;

    private static final String BEARER_PREFIX = "Bearer ";

    private final Algorithm algorithm;

    public DecodedJWT getDecodedJWT(String token) throws AccessDeniedException {
        DecodedJWT decodedJWT;
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            decodedJWT = verifier.verify(token);
        } catch (Exception e) {
            throw new AccessDeniedException("Invalid token.");
        }
        return decodedJWT;
    }

    public TokensDTO createToken(UserDetails userDetails) {
        String accessToken = JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .withClaim("roles", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .sign(algorithm);
        String refreshToken = JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME * 6))
                .sign(algorithm);
        return new TokensDTO(accessToken, refreshToken);
    }

}
