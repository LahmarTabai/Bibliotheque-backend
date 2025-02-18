package com.codewithtabai.spring.bibliotheque.entities;



import jakarta.persistence.*;

@Entity
@Table(name = "magazines")
@PrimaryKeyJoinColumn(name = "DOC_ID")
public class Magazine extends Document {

    // Dans ta base : FREQUENCE_PUBLICATION (enum), NUMERO_PARUTION (bigint), EDITEUR (varchar)
    @Column(name = "FREQUENCE_PUBLICATION", nullable = false, length = 50)
    private String frequencePublication;

    @Column(name = "NUMERO_PARUTION")
    private Long numeroParution;

    @Column(name = "EDITEUR", length = 128)
    private String editeur;

    // ----- GETTERS & SETTERS -----
    public String getFrequencePublication() {
        return frequencePublication;
    }

    public void setFrequencePublication(String frequencePublication) {
        this.frequencePublication = frequencePublication;
    }

    public Long getNumeroParution() {
        return numeroParution;
    }

    public void setNumeroParution(Long numeroParution) {
        this.numeroParution = numeroParution;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }
}
