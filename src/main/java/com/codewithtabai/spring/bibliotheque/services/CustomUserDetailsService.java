package com.codewithtabai.spring.bibliotheque.services;

import com.codewithtabai.spring.bibliotheque.entities.Utilisateur;
import com.codewithtabai.spring.bibliotheque.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
         Utilisateur utilisateur = utilisateurRepository.findByUserEmail(email);
         if (utilisateur == null) {
              throw new UsernameNotFoundException("Utilisateur non trouvé avec l'email : " + email);
         }
         // Crée une autorité basée sur le rôle stocké (exemple : "ADMIN" devient "ROLE_ADMIN")
         return new User(
             utilisateur.getUserEmail(),
             utilisateur.getPassword(),
             Collections.singleton(new SimpleGrantedAuthority("ROLE_" + utilisateur.getRole()))
         );
    }
}
