package com.codewithtabai.spring.bibliotheque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithtabai.spring.bibliotheque.entities.Livre;

import java.util.List;

public interface LivreRepository extends JpaRepository<Livre, Long> {

    // --- Méthodes CRUD explicites ---
    default Livre ajouterLivre(Livre livre) {
        return save(livre);
    }

    default Livre modifierLivre(Livre livre) {
        return save(livre);
    }

    default void supprimerLivre(Long id) {
        deleteById(id);
    }

    default Livre getLivreById(Long id) {
        return findById(id).orElse(null);
    }

    default List<Livre> getAllLivres() {
        return findAll();
    }

    // --- Exemples de méthodes de recherche spécifiques ---
    List<Livre> findByGenreLitteraire(String genreLitteraire);
    
    // etc. si besoin
}
