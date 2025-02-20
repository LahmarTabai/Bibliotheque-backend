package com.codewithtabai.spring.bibliotheque.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http
            .csrf(csrf -> csrf.disable()) // Désactive CSRF pour simplifier (à adapter en production)
            .authorizeHttpRequests(authz -> authz
                // Permettre l'accès public à l'authentification
                .requestMatchers("/api/utilisateurs/authentifier").permitAll()
                // Par exemple, restreindre les autres endpoints des utilisateurs aux ADMIN
                .requestMatchers("/api/utilisateurs/**").hasRole("ADMIN")
                // Autoriser l'accès aux autres endpoints (à ajuster selon tes besoins)
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults()); // Utilise l'authentification HTTP Basic pour la simplicité
         return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
