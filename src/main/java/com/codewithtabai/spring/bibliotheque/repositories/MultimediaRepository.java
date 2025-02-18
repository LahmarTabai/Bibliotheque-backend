package com.codewithtabai.spring.bibliotheque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithtabai.spring.bibliotheque.entities.Multimedia;

import java.util.List;

public interface MultimediaRepository extends JpaRepository<Multimedia, Long> {

    default Multimedia ajouterMultimedia(Multimedia multimedia) {
        return save(multimedia);
    }

    default Multimedia modifierMultimedia(Multimedia multimedia) {
        return save(multimedia);
    }

    default void supprimerMultimedia(Long id) {
        deleteById(id);
    }

    default Multimedia getMultimediaById(Long id) {
        return findById(id).orElse(null);
    }

    default List<Multimedia> getAllMultimedias() {
        return findAll();
    }

    // Méthodes supplémentaires si besoin
    // ...
}
