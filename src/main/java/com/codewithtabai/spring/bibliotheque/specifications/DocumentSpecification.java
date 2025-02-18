package com.codewithtabai.spring.bibliotheque.specifications;

import com.codewithtabai.spring.bibliotheque.entities.Document;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DocumentSpecification {

    public static Specification<Document> searchByCriteria(
            String titre,
            String auteur,
            String description,
            String docType,
            LocalDateTime datePublicationFrom,
            LocalDateTime datePublicationTo
    ) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (titre != null && !titre.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("docTitre"), "%" + titre + "%"));
            }
            if (auteur != null && !auteur.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("docAuteur"), "%" + auteur + "%"));
            }
            if (description != null && !description.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("docDescription"), "%" + description + "%"));
            }
            if (docType != null && !docType.trim().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("docType"), docType));
            }
            if (datePublicationFrom != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("docDatePublication"), datePublicationFrom));
            }
            if (datePublicationTo != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("docDatePublication"), datePublicationTo));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
