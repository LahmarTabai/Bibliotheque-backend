package com.codewithtabai.spring.bibliotheque.dto;

public class AuthRequest {
    private String email;
    private String motDePasse;

    public AuthRequest() {
    }

    public AuthRequest(String email, String motDePasse) {
        this.email = email;
        this.motDePasse = motDePasse;
    }

    // Getters & Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
