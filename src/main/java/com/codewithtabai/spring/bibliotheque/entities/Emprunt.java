package com.codewithtabai.spring.bibliotheque.entities;



import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "emprunt")
public class Emprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPRUNT_ID")
    private Long empruntId;

    // Relation ManyToOne vers Utilisateur
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    // Relation ManyToOne vers Document
    @Column(name = "DOC_ID", nullable = false)
    private Long docId;

    @Column(name = "DATE_EMPRUNT", nullable = false)
    private LocalDateTime dateEmprunt;

    @Column(name = "DATE_RETOUR")
    private LocalDateTime dateRetour;

    @Column(name = "DATE_ECHEANCE", nullable = false)
    private LocalDateTime dateEcheance;

    @Column(name = "FRAIS_RETARD", precision = 10, scale = 2)
    private BigDecimal fraisRetard;

    @Column(name = "STATUS", length = 128)
    private String status;

    // ----- GETTERS & SETTERS -----

    public Long getEmpruntId() {
        return empruntId;
    }

    public void setEmpruntId(Long empruntId) {
        this.empruntId = empruntId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public LocalDateTime getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDateTime dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDateTime getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(LocalDateTime dateRetour) {
        this.dateRetour = dateRetour;
    }

    public LocalDateTime getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(LocalDateTime dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public BigDecimal getFraisRetard() {
        return fraisRetard;
    }

    public void setFraisRetard(BigDecimal fraisRetard) {
        this.fraisRetard = fraisRetard;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

