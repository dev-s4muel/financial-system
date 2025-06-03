package com.sgm.financial_system.service.impl;


import com.sgm.financial_system.dto.jwt.JwtPayloadDto;
import com.sgm.financial_system.dto.login.LoginRequestDto;
import com.sgm.financial_system.exceptions.InvalidCredentialsException;
import com.sgm.financial_system.model.auth.User;
import com.sgm.financial_system.repository.UserRepository;
import com.sgm.financial_system.service.AuthService;
import com.sgm.financial_system.util.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public String authenticate(LoginRequestDto loginRequest) {
        log.info("Iniciando autenticação para o e-mail: {}", loginRequest.getEmail());
        // Verifica se o usuário existe
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> {
                    log.warn("Usuário não encontrado para o e-mail: {}", loginRequest.getEmail());
                    return new InvalidCredentialsException();
                });
        log.info("Usuário encontrado no banco de dados: {}", user.getEmail());

        // Verifica a senha
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            log.error("Senha inválida para o e-mail: {}", loginRequest.getEmail());
            throw new InvalidCredentialsException();
        }
        log.info("Senha validada com sucesso para o e-mail: {}", loginRequest.getEmail());

        // Gera o token JWT incluindo o papel do usuário
        JwtPayloadDto jwtPayloadDto = new JwtPayloadDto(
                user.getEmail(),
                user.getRole()
        );
        String token = jwtUtil.generateToken(jwtPayloadDto);
        log.info("Token JWT gerado com sucesso para o e-mail: {}", loginRequest.getEmail());

        return token;
    }
}