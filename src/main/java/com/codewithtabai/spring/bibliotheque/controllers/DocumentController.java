package com.codewithtabai.spring.bibliotheque.controllers;



import com.codewithtabai.spring.bibliotheque.entities.Document;
import com.codewithtabai.spring.bibliotheque.services.DocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
@CrossOrigin(origins = "http://localhost:4200") // autorise les requêtes depuis n'importe quel domaine (pour Angular)
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    // GET /api/documents
    @GetMapping
    public List<Document> getAllDocuments() {
        return documentService.getAllDocuments();
    }

    // GET /api/documents/{id}
    @GetMapping("/{id}")
    public Document getDocumentById(@PathVariable("id") Long docId) {
        return documentService.getDocumentById(docId);
    }

    // POST /api/documents
    @PostMapping
    public Document ajouterDocument(@RequestBody Document doc) {
        return documentService.ajouterDocument(doc);
    }

    // PUT /api/documents/{id}
    @PutMapping("/{id}")
    public Document modifierDocument(@PathVariable("id") Long docId, @RequestBody Document doc) {
        doc.setDocId(docId);
        return documentService.modifierDocument(doc);
    }

    // DELETE /api/documents/{id}
    @DeleteMapping("/{id}")
    public void supprimerDocument(@PathVariable("id") Long docId) {
        documentService.supprimerDocument(docId);
    }

    // Rechercher par titre
    // GET /api/documents/rechercheParTitre?titre=xxx
    @GetMapping("/rechercheParTitre")
    public List<Document> rechercherParTitre(@RequestParam("titre") String titre) {
        return documentService.rechercherParTitre(titre);
    }

    // Rechercher par auteur
    // GET /api/documents/rechercheParAuteur?auteur=xxx
    @GetMapping("/rechercheParAuteur")
    public List<Document> rechercherParAuteur(@RequestParam("auteur") String auteur) {
        return documentService.rechercherParAuteur(auteur);
    }

    // Rechercher par type
    // GET /api/documents/rechercheParType?type=xxx
    @GetMapping("/rechercheParType")
    public List<Document> rechercherParType(@RequestParam("type") String type) {
        return documentService.rechercherParType(type);
    }
    
    @GetMapping("/rechercheParDescription")
    public List<Document> rechercherParDescription(@RequestParam("desc") String desc) {
        return documentService.rechercherParDescription(desc);
    }
    
 // Endpoint pour la recherche avancée
    @GetMapping("/search")
    public List<Document> rechercherDocumentsAvances(
            @RequestParam(required = false) String titre,
            @RequestParam(required = false) String auteur,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String docType,
            @RequestParam(required = false) String dateFrom,
            @RequestParam(required = false) String dateTo
    ) {
        // On suppose que les dates sont envoyées au format "yyyy-MM-dd HH:mm:ss" ou "yyyy-MM-dd"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd[ HH:mm:ss]");
        LocalDateTime datePublicationFrom = (dateFrom != null && !dateFrom.trim().isEmpty()) 
                ? LocalDateTime.parse(dateFrom, formatter) : null;
        LocalDateTime datePublicationTo = (dateTo != null && !dateTo.trim().isEmpty()) 
                ? LocalDateTime.parse(dateTo, formatter) : null;
                
        return documentService.rechercherDocumentsAvances(titre, auteur, description, docType, datePublicationFrom, datePublicationTo);
    }
    
    
 // Endpoint pour récupérer les recommandations pour un utilisateur
    @GetMapping("/recommendations")
    public List<Document> getRecommendations(@RequestParam("userId") Long userId) {
        return documentService.getRecommendationsForUser(userId);
    }


}
