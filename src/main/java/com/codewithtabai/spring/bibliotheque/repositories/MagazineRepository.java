package com.codewithtabai.spring.bibliotheque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithtabai.spring.bibliotheque.entities.Magazine;

import java.util.List;

public interface MagazineRepository extends JpaRepository<Magazine, Long> {

    default Magazine ajouterMagazine(Magazine magazine) {
        return save(magazine);
    }

    default Magazine modifierMagazine(Magazine magazine) {
        return save(magazine);
    }

    default void supprimerMagazine(Long id) {
        deleteById(id);
    }

    default Magazine getMagazineById(Long id) {
        return findById(id).orElse(null);
    }

    default List<Magazine> getAllMagazines() {
        return findAll();
    }

    // Méthodes supplémentaires si besoin
    // ...
}
