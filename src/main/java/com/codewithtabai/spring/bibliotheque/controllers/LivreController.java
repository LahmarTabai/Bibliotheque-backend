package com.codewithtabai.spring.bibliotheque.controllers;


import com.codewithtabai.spring.bibliotheque.entities.Livre;
import com.codewithtabai.spring.bibliotheque.services.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livres")
@CrossOrigin(origins = "http://localhost:4200")
public class LivreController {

    @Autowired
    private LivreService livreService;

    // GET /api/livres
    @GetMapping
    public List<Livre> getAllLivres() {
        return livreService.getAllLivres();
    }

    // GET /api/livres/{id}
    @GetMapping("/{id}")
    public Livre getLivreById(@PathVariable("id") Long id) {
        return livreService.getLivreById(id);
    }

    // POST /api/livres
    @PostMapping
    public Livre ajouterLivre(@RequestBody Livre livre) {
        return livreService.ajouterLivre(livre);
    }

    // PUT /api/livres/{id}
    @PutMapping("/{id}")
    public Livre modifierLivre(@PathVariable("id") Long id, @RequestBody Livre livre) {
        livre.setDocId(id);
        return livreService.modifierLivre(livre);
    }

    // DELETE /api/livres/{id}
    @DeleteMapping("/{id}")
    public void supprimerLivre(@PathVariable("id") Long id) {
        livreService.supprimerLivre(id);
    }

    // Méthodes supplémentaires si besoin (ex: recherche par genre littéraire, etc.)
}
