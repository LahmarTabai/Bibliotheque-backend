package com.codewithtabai.spring.bibliotheque.dto;

public class DocumentTypeStats {
    private String docType;
    private Long totalEmprunts;

    public DocumentTypeStats(String docType, Long totalEmprunts) {
        this.docType = docType;
        this.totalEmprunts = totalEmprunts;
    }

    // Getters et Setters

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public Long getTotalEmprunts() {
        return totalEmprunts;
    }

    public void setTotalEmprunts(Long totalEmprunts) {
        this.totalEmprunts = totalEmprunts;
    }
}
