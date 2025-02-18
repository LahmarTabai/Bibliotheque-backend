package com.codewithtabai.spring.bibliotheque.services;

import com.codewithtabai.spring.bibliotheque.entities.Journal;
import com.codewithtabai.spring.bibliotheque.repositories.JournalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;




@Service
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    public Journal ajouterJournal(Journal journal) {
        return journalRepository.save(journal);
    }

    public Journal modifierJournal(Journal journal) {
        return journalRepository.save(journal);
    }

    public void supprimerJournal(Long journalId) {
        journalRepository.deleteById(journalId);
    }

    public Journal getJournalById(Long journalId) {
        return journalRepository.findById(journalId).orElse(null);
    }

    public List<Journal> getAllJournaux() {
        return journalRepository.findAll();
    }

    // Méthodes supplémentaires selon tes besoins
}
