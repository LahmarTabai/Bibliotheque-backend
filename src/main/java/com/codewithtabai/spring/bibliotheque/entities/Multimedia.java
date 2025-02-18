package com.codewithtabai.spring.bibliotheque.entities;



import jakarta.persistence.*;

@Entity
@Table(name = "multimedia")
@PrimaryKeyJoinColumn(name = "DOC_ID")
public class Multimedia extends Document {

    @Column(name = "TYPE_MULTIMEDIA", nullable = false, length = 3)
    private String typeMultimedia;  // "CD" ou "DVD"

    @Column(name = "DUREE_TOTALE")
    private Long dureeTotale;

    // ----- GETTERS & SETTERS -----
    public String getTypeMultimedia() {
        return typeMultimedia;
    }

    public void setTypeMultimedia(String typeMultimedia) {
        this.typeMultimedia = typeMultimedia;
    }

    public Long getDureeTotale() {
        return dureeTotale;
    }

    public void setDureeTotale(Long dureeTotale) {
        this.dureeTotale = dureeTotale;
    }
}
