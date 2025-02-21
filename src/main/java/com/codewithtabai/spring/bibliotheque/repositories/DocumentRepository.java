package com.codewithtabai.spring.bibliotheque.repositories;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

import com.codewithtabai.spring.bibliotheque.entities.Document;
import com.codewithtabai.spring.bibliotheque.entities.Utilisateur;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long>, JpaSpecificationExecutor<Document> {

    // --- Méthodes CRUD explicites ---
    default Document ajouterDocument(Document doc) {
        return save(doc);
    }

    default Document modifierDocument(Document doc) {
        return save(doc);
    }

    default void supprimerDocument(Long docId) {
        deleteById(docId);
    }

    default Document getDocumentById(Long docId) {
        return findById(docId).orElse(null);
    }

    default List<Document> getAllDocuments() {
        return findAll();
    }
    
    List<Document> findByDocQuantiteDispoGreaterThan(Long value);


    // --- Quelques méthodes de recherche utiles (exemples) ---
    // Recherche par titre contenant un mot-clé
    List<Document> findByDocTitreContaining(String titre);

    // Recherche par auteur contenant un mot-clé
    List<Document> findByDocAuteurContaining(String auteur);

    // Recherche par type exact
    List<Document> findByDocType(String docType);

    // Ou avec une requête JPQL
    @Query("SELECT d FROM Document d WHERE d.docDescription LIKE %:desc%")
    List<Document> rechercherParDescriptionContenant(@Param("desc") String desc);
    
    
    // Méthode pour les recommandations personnalisées
    @Query("SELECT d FROM Document d " +
           "WHERE d.docQuantiteDispo > 0 " +
           "AND d.docType IN (" +
           "   SELECT DISTINCT d2.docType " +
           "   FROM Emprunt e, Document d2 " +
           "   WHERE e.docId = d2.docId AND e.userId = :userId" +
           ") " +
           "AND d.docId NOT IN (" +
           "   SELECT e.docId FROM Emprunt e WHERE e.userId = :userId" +
           ")")
    List<Document> findRecommendationsForUser(@Param("userId") Long userId);
    
    
   


}
