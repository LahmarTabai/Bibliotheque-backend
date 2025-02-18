package com.codewithtabai.spring.bibliotheque.controllers;



import com.codewithtabai.spring.bibliotheque.dto.DocumentTypeStats;
import com.codewithtabai.spring.bibliotheque.dto.UserEmpruntStats;
import com.codewithtabai.spring.bibliotheque.entities.Emprunt;
import com.codewithtabai.spring.bibliotheque.services.EmpruntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emprunts")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpruntController {

    @Autowired
    private EmpruntService empruntService;

    // GET /api/emprunts
    @GetMapping
    public List<Emprunt> getAllEmprunts() {
        return empruntService.getAllEmprunts();
    }

    // GET /api/emprunts/{id}
    @GetMapping("/{id}")
    public Emprunt getEmpruntById(@PathVariable("id") Long id) {
        return empruntService.getEmpruntById(id);
    }

    // POST /api/emprunts/emprunter
    @PostMapping("/emprunter")
    public Emprunt emprunterDocument(@RequestBody Emprunt emprunt) {
        return empruntService.emprunterDocument(emprunt);
    }

    // PUT /api/emprunts/retourner/{id}
    @PutMapping("/retourner/{id}")
    public Emprunt retournerDocument(@PathVariable("id") Long empruntId) {
        return empruntService.retournerDocument(empruntId);
    }

    // DELETE /api/emprunts/{id}
    @DeleteMapping("/{id}")
    public void supprimerEmprunt(@PathVariable("id") Long id) {
        empruntService.supprimerEmprunt(id);
    }

    // Recherche par userId
    // GET /api/emprunts/utilisateur/{userId}
    @GetMapping("/utilisateur/{userId}")
    public List<Emprunt> getEmpruntsParUtilisateur(@PathVariable Long userId) {
        return empruntService.getEmpruntsParUtilisateur(userId);
    }

    // Recherche par docId
    // GET /api/emprunts/document/{docId}
    @GetMapping("/document/{docId}")
    public List<Emprunt> getEmpruntsParDocument(@PathVariable Long docId) {
        return empruntService.getEmpruntsParDocument(docId);
    }
    
 // Endpoint pour les statistiques : nombre d'emprunts par utilisateur
    @GetMapping("/stats/users")
    public List<UserEmpruntStats> getUserEmpruntStats() {
        return empruntService.getEmpruntsStatsByUser();
    }

    // Endpoint pour les statistiques : nombre d'emprunts par type de document
    @GetMapping("/stats/docTypes")
    public List<DocumentTypeStats> getDocTypeEmpruntStats() {
        return empruntService.getEmpruntsStatsByDocType();
    }
}
