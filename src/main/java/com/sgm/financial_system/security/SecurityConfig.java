package com.sgm.financial_system.security;

import com.sgm.financial_system.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.sgm.financial_system.model.auth.RoleEnum.ADMIN;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    public static final String SECURITY = "bearerAuth";
    public static final String URI_REGISTER = "api/auth/register";
    public static final String URI_LOGIN = "api/auth/login";
    public static final String URI_USER_ROLE = "/api/users/**";
    public static final String URI_GROW_DIARY_REGISTER = "api/diary/register";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, URI_REGISTER).permitAll()
                        .requestMatchers(HttpMethod.POST, URI_LOGIN).permitAll()
                        .requestMatchers(HttpMethod.POST, URI_GROW_DIARY_REGISTER).permitAll()
                        .requestMatchers(URI_USER_ROLE).hasRole(ADMIN.name())
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Bean para codificar senhas com BCrypt
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}