package com.example.demo.auth.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokensDTO {

    private final String accessToken;

    private final String refreshToken;

}
