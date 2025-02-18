package com.codewithtabai.spring.bibliotheque.entities;



import jakarta.persistence.*;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "USER_NOM", nullable = false, length = 128)
    private String userNom;

    @Column(name = "USER_PRENOM", nullable = false, length = 128)
    private String userPrenom;

    @Column(name = "USER_EMAIL", nullable = false, length = 128, unique = true)
    private String userEmail;

    @Column(name = "USER_TEL", length = 15)
    private String userTel;

    // Dans ta table c'est un enum('ADMIN','USER'), on va le stocker comme String
    @Column(name = "ROLE", length = 10)
    private String role;

    @Column(name = "PASSWORD", nullable = false, length = 128)
    private String password;

    @Column(name = "PASSWORD_CHANGED")
    private Boolean passwordChanged;

    // ----- GETTERS & SETTERS -----

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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getPasswordChanged() {
        return passwordChanged;
    }

    public void setPasswordChanged(Boolean passwordChanged) {
        this.passwordChanged = passwordChanged;
    }
}
