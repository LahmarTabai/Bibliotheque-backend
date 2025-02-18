package com.codewithtabai.spring.bibliotheque.entities;



import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "journaux")
@PrimaryKeyJoinColumn(name = "DOC_ID")
public class Journal extends Document {

    @Column(name = "DATE_PUBLICATION_SPECIFIQUE", nullable = false)
    private LocalDateTime datePublicationSpecifique;

    // ----- GETTERS & SETTERS -----
    public LocalDateTime getDatePublicationSpecifique() {
        return datePublicationSpecifique;
    }

    public void setDatePublicationSpecifique(LocalDateTime datePublicationSpecifique) {
        this.datePublicationSpecifique = datePublicationSpecifique;
    }
}
