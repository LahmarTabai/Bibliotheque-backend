package com.codewithtabai.spring.bibliotheque.services;



import com.codewithtabai.spring.bibliotheque.dto.DocumentTypeStats;
import com.codewithtabai.spring.bibliotheque.dto.UserEmpruntStats;
import com.codewithtabai.spring.bibliotheque.entities.Document;
import com.codewithtabai.spring.bibliotheque.entities.Emprunt;
import com.codewithtabai.spring.bibliotheque.repositories.DocumentRepository;
import com.codewithtabai.spring.bibliotheque.repositories.EmpruntRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class EmpruntService {

    @Autowired
    private EmpruntRepository empruntRepository;

    @Autowired
    private DocumentService documentService; 
    // On peut injecter DocumentService plutôt que DocumentRepository
    // pour réutiliser la logique de décrémentation/incrémentation

    @Autowired
    private DocumentRepository documentRepository;

    /**
     * Créer un nouvel emprunt (emprunter un document)
     */
    public Emprunt emprunterDocument(Emprunt emprunt) {
        // 1. Vérifier si le document existe et est dispo
        Document doc = documentService.getDocumentById(emprunt.getDocId());
        if (doc == null) {
            throw new RuntimeException("Document introuvable");
        }
        if (doc.getDocQuantiteDispo() == 0) {
            throw new RuntimeException("Plus d'exemplaires disponibles");
        }

        // 2. Décrémenter la quantité disponible
        documentService.decrementerQuantiteDispo(doc.getDocId());

        // 3. Définir la date d'emprunt si besoin
        if (emprunt.getDateEmprunt() == null) {
            emprunt.setDateEmprunt(LocalDateTime.now());
        }

        // 4. Sauvegarder l'emprunt
        Emprunt nouveau = empruntRepository.save(emprunt);

        return nouveau;
    }

    /**
     * Retourner un document (clôturer l'emprunt)
     */
    public Emprunt retournerDocument(Long empruntId) {
        // 1. Récupérer l'emprunt
        Emprunt emprunt = empruntRepository.findById(empruntId).orElse(null);
        if (emprunt == null) {
            throw new RuntimeException("Emprunt introuvable");
        }

        // 2. Définir la date de retour
        emprunt.setDateRetour(LocalDateTime.now());

        // 3. Calcul des frais de retard si la dateRetour > dateEcheance
        if (emprunt.getDateRetour().isAfter(emprunt.getDateEcheance())) {
            // Exemple de calcul : 1.50 € par jour de retard
            long joursDeRetard = Duration.between(emprunt.getDateEcheance(), emprunt.getDateRetour()).toDays();
            BigDecimal frais = BigDecimal.valueOf(joursDeRetard * 1.50);
            emprunt.setFraisRetard(frais);
        }

        // 4. Marquer le statut comme "Cloturee" (optionnel)
        emprunt.setStatus("Cloturee");

        // 5. Incrémenter la quantité disponible du document
        documentService.incrementerQuantiteDispo(emprunt.getDocId());

        // 6. Sauvegarder les modifications
        Emprunt maj = empruntRepository.save(emprunt);
        return maj;
    }

    // ---------- Méthodes CRUD ou “classiques” ----------

    public Emprunt getEmpruntById(Long id) {
        return empruntRepository.findById(id).orElse(null);
    }

    public List<Emprunt> getAllEmprunts() {
        return empruntRepository.findAll();
    }

    public Emprunt modifierEmprunt(Emprunt e) {
        // Parfois, on ne souhaite pas qu'un emprunt soit modifié directement,
        // mais si c'est nécessaire, on le fait via save().
        return empruntRepository.save(e);
    }

    public void supprimerEmprunt(Long id) {
        empruntRepository.deleteById(id);
    }

    // ---------- Méthodes de recherche supplémentaires ----------

    public List<Emprunt> getEmpruntsParUtilisateur(Long userId) {
        return empruntRepository.findByUserId(userId);
    }

    public List<Emprunt> getEmpruntsParDocument(Long docId) {
        return empruntRepository.findByDocId(docId);
    }
    
    // ---------- statistiques ----------
    
    
    // Méthode pour récupérer les statistiques des emprunts par utilisateur
    public List<UserEmpruntStats> getEmpruntsStatsByUser() {
        return empruntRepository.countEmpruntsByUser();
    }

    // Méthode pour récupérer les statistiques des emprunts par type de document
    public List<DocumentTypeStats> getEmpruntsStatsByDocType() {
        return empruntRepository.countEmpruntsByDocType();
    }

}
