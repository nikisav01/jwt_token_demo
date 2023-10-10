package com.example.demo.auth;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlgorithmConfig {

    @Bean
    public Algorithm algorithm() {
        return Algorithm.HMAC256("Secret".getBytes());
    }

}
