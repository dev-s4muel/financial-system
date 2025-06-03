package com.sgm.financial_system.controller.auth;

import com.sgm.financial_system.dto.jwt.JwtResponseDto;
import com.sgm.financial_system.dto.login.LoginRequestDto;
import com.sgm.financial_system.dto.user.UserDto;
import com.sgm.financial_system.service.AuthService;
import com.sgm.financial_system.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> createNewAccount(@Valid @RequestBody UserDto userDto) {
        log.info("Tentativa de registro para o email: {}", userDto.getEmail());
        userService.registerUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody @Valid LoginRequestDto loginRequest) {
        log.info("Tentativa de login para o email: {}", loginRequest.getEmail());
        String token = authService.authenticate(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new JwtResponseDto(token));
    }
}
