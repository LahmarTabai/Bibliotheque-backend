package com.codewithtabai.spring.bibliotheque.dto;

import com.codewithtabai.spring.bibliotheque.entities.Utilisateur;

public class AuthenticationResponse {
    private Utilisateur utilisateur;
    private boolean mustChangePassword;

    public AuthenticationResponse(Utilisateur utilisateur, boolean mustChangePassword) {
        this.utilisateur = utilisateur;
        this.mustChangePassword = mustChangePassword;
    }

    // Getters & Setters
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public boolean isMustChangePassword() {
        return mustChangePassword;
    }

    public void setMustChangePassword(boolean mustChangePassword) {
        this.mustChangePassword = mustChangePassword;
    }
}
