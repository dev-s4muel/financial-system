package com.sgm.financial_system.model.auth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"createdAt\"")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "\"updatedAt\"")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres.")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "O e-mail deve ser válido.")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres.")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "O CPF é obrigatório.")
    @Size(min = 11, max = 11, message = "CPF inválido. Use o formato 00000000000.")
    @Column(nullable = false)
    private String cpf;

    @NotBlank(message = "O telefone é obrigatório.")
    @Pattern(regexp = "^[1-9]{2}9[0-9]{8}$", message = "O telefone deve estar no formato DDD + número (ex: 31912345678)")
    @Column(name = "\"cellPhone\"")
    private String cellPhone;

    @NotNull(message = "A data de nascimento é obrigatória.")
    @Past(message = "A data de nascimento deve ser uma data no passado.")
    @Column(nullable = false,name = "\"birthDate\"")
    private LocalDate birthDate;

    @Enumerated(EnumType.ORDINAL)
    private RoleEnum role;

    public User(LocalDate birthDate, String cellPhone, String cpf, String password, String email, String name) {
        this.role = RoleEnum.USER;
        this.birthDate = birthDate;
        this.cellPhone = cellPhone;
        this.cpf = cpf;
        this.password = password;
        this.email = email;
        this.name = name;
        this.updatedAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }
}