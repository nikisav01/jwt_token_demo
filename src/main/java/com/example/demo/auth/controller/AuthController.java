package com.example.demo.auth.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.auth.controller.dto.LoginDTO;
import com.example.demo.auth.controller.dto.TokensDTO;
import com.example.demo.auth.service.JwtService;
import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/api/login")
    public TokensDTO login(@RequestBody LoginDTO loginDTO) {
        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(loginDTO.getUsername());

            String passwordEncoded = passwordEncoder.encode("password");
            if (!passwordEncoder.matches(loginDTO.getPassword(), passwordEncoded)) {
                throw new AuthenticationException();
            }
        } catch (Exception ex) {
            throw new AuthenticationException();
        }
        return jwtService.createToken(userDetails);
    }

    @PostMapping("/api/tokens/refresh")
    public TokensDTO refreshTokens(@RequestBody TokensDTO tokensDTO) {
        UserDetails userDetails;
        try {
            DecodedJWT decodedJWT = jwtService.getDecodedJWT(tokensDTO.getRefreshToken());
            userDetails = userDetailsService.loadUserByUsername(decodedJWT.getSubject());
        } catch (Exception ex) {
            throw new AuthenticationException("Refresh token is invalid");
        }
        return jwtService.createToken(userDetails);
    }

    @GetMapping("/api/admin/greeting")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String greetingAdmin() {
        String username = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return "Hello, " + username;
    }

    @GetMapping("/api/greeting")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String greetingUser() {
        String username = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return "Hello, " + username;
    }

}
