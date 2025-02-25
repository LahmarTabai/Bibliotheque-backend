package com.codewithtabai.spring.bibliotheque.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) 
            .authorizeHttpRequests(authz -> authz
                // On autorise librement l'accès à /api/utilisateurs/authentifier
                .requestMatchers("/api/utilisateurs/authentifier").permitAll()
                // Autoriser la création de compte ?
                .requestMatchers(HttpMethod.POST, "/api/utilisateurs").permitAll()
                
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // Autoriser un USER à modifier son propre compte, etc. => au choix
                .requestMatchers(HttpMethod.PUT, "/api/utilisateurs/{id}").hasAnyRole("ADMIN", "USER")
                // Ou tu peux affiner en rajoutant du @PreAuthorize dans le controller
                
             // *** Autoriser POST /api/utilisateurs/{id}/changePassword ***
                .requestMatchers(HttpMethod.POST, "/api/utilisateurs/{id}/changePassword").hasAnyRole("ADMIN","USER")

                // Les autres endpoints de /api/utilisateurs réservés à ADMIN
                .requestMatchers("/api/utilisateurs/**").hasRole("ADMIN")
                
             // Autoriser GET sur /api/documents si tu veux que tout le monde puisse les voir
                .requestMatchers(HttpMethod.GET, "/api/documents").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/documents/recommendations").hasRole("USER")


                // En revanche, POST/PUT/DELETE sur /api/documents => ADMIN
                .requestMatchers(HttpMethod.POST, "/api/documents").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/documents/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/documents/**").hasRole("ADMIN")
                
                // Emprunts :
                // GET/POST/PUT => hasRole("USER") ou hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/emprunts/**").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.POST, "/api/emprunts/**").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.PUT, "/api/emprunts/**").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.DELETE, "/api/emprunts/**").hasRole("ADMIN")
                
               
                
                
                
                
                

                // Autoriser d'autres endpoints publics si besoin (livres ?)
                .requestMatchers(HttpMethod.GET, "/api/livres", "/api/magazines").permitAll()

                .anyRequest().authenticated()
            )
            // Important : enlever httpBasic()
            //.httpBasic(Customizer.withDefaults())
            .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

         return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
