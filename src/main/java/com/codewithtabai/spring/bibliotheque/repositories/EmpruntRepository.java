package com.codewithtabai.spring.bibliotheque.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.codewithtabai.spring.bibliotheque.dto.DocumentTypeStats;
import com.codewithtabai.spring.bibliotheque.dto.UserEmpruntStats;
import com.codewithtabai.spring.bibliotheque.entities.Emprunt;

import java.util.List;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

    default Emprunt ajouterEmprunt(Emprunt e) {
        return save(e);
    }

    default Emprunt modifierEmprunt(Emprunt e) {
        return save(e);
    }

    default void supprimerEmprunt(Long id) {
        deleteById(id);
    }

    default Emprunt getEmpruntById(Long id) {
        return findById(id).orElse(null);
    }

    default List<Emprunt> getAllEmprunts() {
        return findAll();
    }
    
    // On utilise la convenance Spring Data
    List<Emprunt> findByStatus(String status);


    // Possibles recherches par userId, docId, etc.
    List<Emprunt> findByUserId(Long userId);
    List<Emprunt> findByDocId(Long docId);
    
 // Statistiques : Nombre d'emprunts par utilisateur
    @Query("SELECT new com.codewithtabai.spring.bibliotheque.dto.UserEmpruntStats(e.userId, u.userNom, u.userPrenom, COUNT(e)) " +
           "FROM Emprunt e, Utilisateur u " +
           "WHERE e.userId = u.userId " +
           "GROUP BY e.userId, u.userNom, u.userPrenom " +
           "ORDER BY COUNT(e) DESC")
    List<UserEmpruntStats> countEmpruntsByUser();

    // Statistiques : Nombre d'emprunts par type de document
    @Query("SELECT new com.codewithtabai.spring.bibliotheque.dto.DocumentTypeStats(d.docType, COUNT(e)) " +
           "FROM Emprunt e, Document d " +
           "WHERE e.docId = d.docId " +
           "GROUP BY d.docType " +
           "ORDER BY COUNT(e) DESC")
    List<DocumentTypeStats> countEmpruntsByDocType();
    
    List<Emprunt> findByUserIdAndDocIdAndStatus(Long userId, Long docId, String status);
    
    List<Emprunt> findByUserIdAndStatus(Long userId, String status);
    



}
