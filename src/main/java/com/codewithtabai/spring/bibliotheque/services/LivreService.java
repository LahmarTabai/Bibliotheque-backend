package com.codewithtabai.spring.bibliotheque.services;

import com.codewithtabai.spring.bibliotheque.entities.Livre;
import com.codewithtabai.spring.bibliotheque.repositories.LivreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;




@Service
public class LivreService {

    @Autowired
    private LivreRepository livreRepository;

    public Livre ajouterLivre(Livre livre) {
        return livreRepository.save(livre);
    }

    public Livre modifierLivre(Livre livre) {
        return livreRepository.save(livre);
    }

    public void supprimerLivre(Long livreId) {
        livreRepository.deleteById(livreId);
    }

    public Livre getLivreById(Long livreId) {
        return livreRepository.findById(livreId).orElse(null);
    }

    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }

    // Méthodes supplémentaires si tu en avais (ex: recherche par genre, etc.)
    // Par ex: List<Livre> findByGenreLitteraire(String genre) { ... }
}
