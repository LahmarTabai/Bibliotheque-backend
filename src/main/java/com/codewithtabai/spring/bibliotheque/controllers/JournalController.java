package com.codewithtabai.spring.bibliotheque.controllers;



import com.codewithtabai.spring.bibliotheque.entities.Journal;
import com.codewithtabai.spring.bibliotheque.services.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/journaux")
@CrossOrigin(origins = "http://localhost:4200")
public class JournalController {

    @Autowired
    private JournalService journalService;

    // Récupérer la liste de tous les journaux
    @GetMapping
    public List<Journal> getAllJournaux() {
        return journalService.getAllJournaux();
    }

    // Récupérer un journal par son ID
    @GetMapping("/{id}")
    public Journal getJournalById(@PathVariable("id") Long id) {
        return journalService.getJournalById(id);
    }

    // Ajouter un journal
    @PostMapping
    public Journal ajouterJournal(@RequestBody Journal journal) {
        return journalService.ajouterJournal(journal);
    }

    // Modifier un journal
    @PutMapping("/{id}")
    public Journal modifierJournal(@PathVariable("id") Long id, @RequestBody Journal journal) {
        journal.setDocId(id);
        return journalService.modifierJournal(journal);
    }

    // Supprimer un journal
    @DeleteMapping("/{id}")
    public void supprimerJournal(@PathVariable("id") Long id) {
        journalService.supprimerJournal(id);
    }
}
