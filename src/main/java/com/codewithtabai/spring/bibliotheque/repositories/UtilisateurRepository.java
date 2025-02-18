package com.codewithtabai.spring.bibliotheque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithtabai.spring.bibliotheque.entities.Utilisateur;

import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    default Utilisateur ajouterUtilisateur(Utilisateur u) {
        return save(u);
    }

    default Utilisateur modifierUtilisateur(Utilisateur u) {
        return save(u);
    }

    default void supprimerUtilisateur(Long userId) {
        deleteById(userId);
    }

    default Utilisateur getUtilisateurById(Long userId) {
        return findById(userId).orElse(null);
    }

    default List<Utilisateur> getAllUtilisateurs() {
        return findAll();
    }

    // Recherche par email
    Utilisateur findByUserEmail(String email);

    // Eventuellement, tu peux rajouter d'autres méthodes selon ton besoin
    
 // Dans UtilisateurRepository
    Utilisateur findByUserEmailAndPassword(String userEmail, String password);

}
