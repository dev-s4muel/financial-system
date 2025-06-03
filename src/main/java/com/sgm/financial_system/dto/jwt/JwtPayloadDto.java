package com.sgm.financial_system.dto.jwt;

import com.sgm.financial_system.model.auth.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtPayloadDto {
    private String email;
    private RoleEnum role;
}