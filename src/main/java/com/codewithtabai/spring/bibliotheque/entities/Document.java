package com.codewithtabai.spring.bibliotheque.entities;



import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
@Inheritance(strategy = InheritanceType.JOINED)  // Pour gérer l’héritage avec sous-tables
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DOC_ID")
    private Long docId;

    @Column(name = "DOC_TITRE", nullable = false, length = 128)
    private String docTitre;

    @Column(name = "DOC_AUTEUR", length = 128)
    private String docAuteur;

    @Column(name = "DOC_DESCRIPTION", columnDefinition = "TEXT")
    private String docDescription;

    @Column(name = "DOC_FICHE_TECHNIQUE", columnDefinition = "TEXT")
    private String docFicheTechnique;

    @Column(name = "DOC_DATE_PUBLICATION")
    private LocalDateTime docDatePublication;

    @Column(name = "DOC_QUANTITE", nullable = false)
    private Long docQuantite;

    @Column(name = "DOC_QUANTITE_DISPO", nullable = false)
    private Long docQuantiteDispo;

    // On peut mapper l'énumération, mais il y a un accent "Multimédia" côté BDD.
    // Pour rester strictement identique à ta BDD, on va stocker la valeur brute (String).
    @Column(name = "DOC_TYPE", nullable = false, length = 20)
    private String docType;

    // ----- GETTERS & SETTERS -----

    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public String getDocTitre() {
        return docTitre;
    }

    public void setDocTitre(String docTitre) {
        this.docTitre = docTitre;
    }

    public String getDocAuteur() {
        return docAuteur;
    }

    public void setDocAuteur(String docAuteur) {
        this.docAuteur = docAuteur;
    }

    public String getDocDescription() {
        return docDescription;
    }

    public void setDocDescription(String docDescription) {
        this.docDescription = docDescription;
    }

    public String getDocFicheTechnique() {
        return docFicheTechnique;
    }

    public void setDocFicheTechnique(String docFicheTechnique) {
        this.docFicheTechnique = docFicheTechnique;
    }

    public LocalDateTime getDocDatePublication() {
        return docDatePublication;
    }

    public void setDocDatePublication(LocalDateTime docDatePublication) {
        this.docDatePublication = docDatePublication;
    }

    public Long getDocQuantite() {
        return docQuantite;
    }

    public void setDocQuantite(Long docQuantite) {
        this.docQuantite = docQuantite;
    }

    public Long getDocQuantiteDispo() {
        return docQuantiteDispo;
    }

    public void setDocQuantiteDispo(Long docQuantiteDispo) {
        this.docQuantiteDispo = docQuantiteDispo;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }
}
