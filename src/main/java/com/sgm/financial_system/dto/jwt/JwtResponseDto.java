package com.sgm.financial_system.dto.jwt;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtResponseDto {

    private String token;
    private String type = "Bearer";

    public JwtResponseDto(String token) {
        this.token = token;
    }
}