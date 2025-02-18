package com.codewithtabai.spring.bibliotheque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithtabai.spring.bibliotheque.entities.Journal;

import java.util.List;

public interface JournalRepository extends JpaRepository<Journal, Long> {

    default Journal ajouterJournal(Journal journal) {
        return save(journal);
    }

    default Journal modifierJournal(Journal journal) {
        return save(journal);
    }

    default void supprimerJournal(Long id) {
        deleteById(id);
    }

    default Journal getJournalById(Long id) {
        return findById(id).orElse(null);
    }

    default List<Journal> getAllJournaux() {
        return findAll();
    }

    // Exemples de méthodes spéciales, si tu en as besoin
    // ...
}
