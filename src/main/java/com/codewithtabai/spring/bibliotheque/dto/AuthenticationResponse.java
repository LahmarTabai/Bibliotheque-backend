package com.codewithtabai.spring.bibliotheque.dto;

import com.codewithtabai.spring.bibliotheque.entities.Utilisateur;

public class AuthenticationResponse {
    private Utilisateur utilisateur;
    private boolean mustChangePassword;
    private String token; // nouveau

    public AuthenticationResponse(Utilisateur utilisateur, boolean mustChangePassword) {
        this.utilisateur = utilisateur;
        this.mustChangePassword = mustChangePassword;
    }

    public AuthenticationResponse(Utilisateur utilisateur, boolean mustChangePassword, String token) {
        this.utilisateur = utilisateur;
        this.mustChangePassword = mustChangePassword;
        this.token = token;
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
    
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
