package com.codewithtabai.spring.bibliotheque.controllers;



import com.codewithtabai.spring.bibliotheque.entities.Multimedia;
import com.codewithtabai.spring.bibliotheque.services.MultimediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/multimedia")
@CrossOrigin(origins = "http://localhost:4200")
public class MultimediaController {

    @Autowired
    private MultimediaService multimediaService;

    // Récupérer la liste de tous les documents Multimédia
    @GetMapping
    public List<Multimedia> getAllMultimedias() {
        return multimediaService.getAllMultimedias();
    }

    // Récupérer un document Multimédia par son ID
    @GetMapping("/{id}")
    public Multimedia getMultimediaById(@PathVariable("id") Long id) {
        return multimediaService.getMultimediaById(id);
    }

    // Ajouter un document Multimédia
    @PostMapping
    public Multimedia ajouterMultimedia(@RequestBody Multimedia multimedia) {
        return multimediaService.ajouterMultimedia(multimedia);
    }

    // Modifier un document Multimédia
    @PutMapping("/{id}")
    public Multimedia modifierMultimedia(@PathVariable("id") Long id, @RequestBody Multimedia multimedia) {
        multimedia.setDocId(id);
        return multimediaService.modifierMultimedia(multimedia);
    }

    // Supprimer un document Multimédia
    @DeleteMapping("/{id}")
    public void supprimerMultimedia(@PathVariable("id") Long id) {
        multimediaService.supprimerMultimedia(id);
    }
}
