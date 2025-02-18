package com.codewithtabai.spring.bibliotheque.services;

import com.codewithtabai.spring.bibliotheque.entities.Magazine;
import com.codewithtabai.spring.bibliotheque.repositories.MagazineRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class MagazineService {

    @Autowired
    private MagazineRepository magazineRepository;

    public Magazine ajouterMagazine(Magazine magazine) {
        return magazineRepository.save(magazine);
    }

    public Magazine modifierMagazine(Magazine magazine) {
        return magazineRepository.save(magazine);
    }

    public void supprimerMagazine(Long magazineId) {
        magazineRepository.deleteById(magazineId);
    }

    public Magazine getMagazineById(Long magazineId) {
        return magazineRepository.findById(magazineId).orElse(null);
    }

    public List<Magazine> getAllMagazines() {
        return magazineRepository.findAll();
    }

    // Méthodes supplémentaires si besoin
}
