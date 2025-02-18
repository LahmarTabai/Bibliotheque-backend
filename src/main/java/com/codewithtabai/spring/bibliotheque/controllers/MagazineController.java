package com.codewithtabai.spring.bibliotheque.controllers;



import com.codewithtabai.spring.bibliotheque.entities.Magazine;
import com.codewithtabai.spring.bibliotheque.services.MagazineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/magazines")
//@CrossOrigin(origins = "*")
@CrossOrigin(origins = "http://localhost:4200")
public class MagazineController {

    @Autowired
    private MagazineService magazineService;

    // Récupérer la liste de tous les magazines
    @GetMapping
    public List<Magazine> getAllMagazines() {
        return magazineService.getAllMagazines();
    }

    // Récupérer un magazine par son ID
    @GetMapping("/{id}")
    public Magazine getMagazineById(@PathVariable("id") Long id) {
        return magazineService.getMagazineById(id);
    }

    // Ajouter un magazine
    @PostMapping
    public Magazine ajouterMagazine(@RequestBody Magazine magazine) {
        return magazineService.ajouterMagazine(magazine);
    }

    // Modifier un magazine
    @PutMapping("/{id}")
    public Magazine modifierMagazine(@PathVariable("id") Long id, @RequestBody Magazine magazine) {
        magazine.setDocId(id);
        return magazineService.modifierMagazine(magazine);
    }

    // Supprimer un magazine
    @DeleteMapping("/{id}")
    public void supprimerMagazine(@PathVariable("id") Long id) {
        magazineService.supprimerMagazine(id);
    }
}
