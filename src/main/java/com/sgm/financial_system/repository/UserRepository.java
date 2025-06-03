package com.sgm.financial_system.repository;

import com.sgm.financial_system.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}