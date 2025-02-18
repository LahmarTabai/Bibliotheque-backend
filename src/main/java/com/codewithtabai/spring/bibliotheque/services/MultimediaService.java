package com.codewithtabai.spring.bibliotheque.services;

import com.codewithtabai.spring.bibliotheque.entities.Multimedia;
import com.codewithtabai.spring.bibliotheque.repositories.MultimediaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MultimediaService {

    @Autowired
    private MultimediaRepository multimediaRepository;

    public Multimedia ajouterMultimedia(Multimedia multimedia) {
        return multimediaRepository.save(multimedia);
    }

    public Multimedia modifierMultimedia(Multimedia multimedia) {
        return multimediaRepository.save(multimedia);
    }

    public void supprimerMultimedia(Long multimediaId) {
        multimediaRepository.deleteById(multimediaId);
    }

    public Multimedia getMultimediaById(Long multimediaId) {
        return multimediaRepository.findById(multimediaId).orElse(null);
    }

    public List<Multimedia> getAllMultimedias() {
        return multimediaRepository.findAll();
    }

    // Méthodes supplémentaires si besoin
}
