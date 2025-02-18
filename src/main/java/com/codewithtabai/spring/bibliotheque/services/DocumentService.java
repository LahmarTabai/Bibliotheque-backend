package com.codewithtabai.spring.bibliotheque.services;

import com.codewithtabai.spring.bibliotheque.entities.Document;
import com.codewithtabai.spring.bibliotheque.repositories.DocumentRepository;
import com.codewithtabai.spring.bibliotheque.specifications.DocumentSpecification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;




@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    /**
     * Ajouter un nouveau document
     */
    public Document ajouterDocument(Document doc) {
        // Exemple de règle de gestion éventuelle :
        // if (doc.getDocQuantiteDispo() > doc.getDocQuantite()) { ... }
        return documentRepository.save(doc);
    }

    /**
     * Modifier un document existant
     */
    public Document modifierDocument(Document doc) {
        // Vérifier que le document existe en base, etc. si nécessaire
        return documentRepository.save(doc);
    }

    /**
     * Supprimer un document par son ID
     */
    public void supprimerDocument(Long docId) {
        documentRepository.deleteById(docId);
    }

    /**
     * Récupérer un document par son ID
     */
    public Document getDocumentById(Long docId) {
        return documentRepository.findById(docId).orElse(null);
    }

    /**
     * Récupérer la liste de tous les documents
     */
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    /**
     * Méthode pour décrémenter la quantité disponible d'un document
     * quand on effectue un emprunt.
     */
    public void decrementerQuantiteDispo(Long docId) {
        Document doc = getDocumentById(docId);
        if (doc != null && doc.getDocQuantiteDispo() > 0) {
            doc.setDocQuantiteDispo(doc.getDocQuantiteDispo() - 1);
            documentRepository.save(doc);
        }
    }

    /**
     * Méthode pour incrémenter la quantité disponible d'un document
     * quand on effectue un retour.
     */
    public void incrementerQuantiteDispo(Long docId) {
        Document doc = getDocumentById(docId);
        if (doc != null && doc.getDocQuantiteDispo() < doc.getDocQuantite()) {
            doc.setDocQuantiteDispo(doc.getDocQuantiteDispo() + 1);
            documentRepository.save(doc);
        }
    }

    // ---------- Méthodes de recherche supplémentaires ----------
    public List<Document> rechercherParTitre(String titre) {
        return documentRepository.findByDocTitreContaining(titre);
    }

    public List<Document> rechercherParAuteur(String auteur) {
        return documentRepository.findByDocAuteurContaining(auteur);
    }

    public List<Document> rechercherParType(String type) {
        return documentRepository.findByDocType(type);
    }
    
    public List<Document> rechercherParDescription(String desc) {
        return documentRepository.rechercherParDescriptionContenant(desc);
    }
    
    public List<Document> rechercherDocumentsAvances(
            String titre,
            String auteur,
            String description,
            String docType,
            LocalDateTime datePublicationFrom,
            LocalDateTime datePublicationTo
    ) {
        Specification<Document> spec = DocumentSpecification.searchByCriteria(
                titre, auteur, description, docType, datePublicationFrom, datePublicationTo);
        return documentRepository.findAll(spec);
    }
    
    
    public List<Document> getRecommendationsForUser(Long userId) {
        return documentRepository.findRecommendationsForUser(userId);
    }


    
}
