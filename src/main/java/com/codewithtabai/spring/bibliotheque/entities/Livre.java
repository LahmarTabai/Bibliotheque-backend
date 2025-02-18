package com.codewithtabai.spring.bibliotheque.entities;



import jakarta.persistence.*;

@Entity
@Table(name = "livres")
@PrimaryKeyJoinColumn(name = "DOC_ID")
public class Livre extends Document {

    @Column(name = "NB_PAGES")
    private Long nbPages;

    @Column(name = "GENRE_LITTERAIRE", length = 128)
    private String genreLitteraire;

    // ----- GETTERS & SETTERS -----
    public Long getNbPages() {
        return nbPages;
    }

    public void setNbPages(Long nbPages) {
        this.nbPages = nbPages;
    }

    public String getGenreLitteraire() {
        return genreLitteraire;
    }

    public void setGenreLitteraire(String genreLitteraire) {
        this.genreLitteraire = genreLitteraire;
    }
}
