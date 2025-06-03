package com.sgm.financial_system.service.impl;

import com.sgm.financial_system.dto.user.UserDto;
import com.sgm.financial_system.exceptions.EmailAlreadyRegisteredException;
import com.sgm.financial_system.model.auth.User;
import com.sgm.financial_system.repository.UserRepository;
import com.sgm.financial_system.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public void registerUser(UserDto userDTO) {
        log.info("Iniciando o registro do usuário com e-mail: {}", userDTO.getEmail());

        User user = new User(
                userDTO.getBirthDate(),
                userDTO.getCellPhone(),
                userDTO.getCpf(),
                userDTO.getPassword(),
                userDTO.getEmail(),
                userDTO.getName()
        );

        // Verifica se o e-mail já está cadastrado
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            log.warn("Tentativa de registro com e-mail já cadastrado: {}", user.getEmail());
            throw new EmailAlreadyRegisteredException();
        }

        // Criptografa a senha
        log.info("Criptografando a senha para o e-mail: {}", user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Salva o usuário no banco de dados
        userRepository.save(user);
        log.info("Usuário com e-mail {} registrado com sucesso!", user.getEmail());
    }
}