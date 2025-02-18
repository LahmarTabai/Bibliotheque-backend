package com.codewithtabai.spring.bibliotheque.dto;

public class UserEmpruntStats {
    private Long userId;
    private String userNom;
    private String userPrenom;
    private Long totalEmprunts;

    public UserEmpruntStats(Long userId, String userNom, String userPrenom, Long totalEmprunts) {
        this.userId = userId;
        this.userNom = userNom;
        this.userPrenom = userPrenom;
        this.totalEmprunts = totalEmprunts;
    }

    // Getters et Setters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserNom() {
        return userNom;
    }

    public void setUserNom(String userNom) {
        this.userNom = userNom;
    }

    public String getUserPrenom() {
        return userPrenom;
    }

    public void setUserPrenom(String userPrenom) {
        this.userPrenom = userPrenom;
    }

    public Long getTotalEmprunts() {
        return totalEmprunts;
    }

    public void setTotalEmprunts(Long totalEmprunts) {
        this.totalEmprunts = totalEmprunts;
    }
}
