package com.codewithtabai.spring.bibliotheque.controllers;



import com.codewithtabai.spring.bibliotheque.dto.AuthRequest;
import com.codewithtabai.spring.bibliotheque.dto.AuthenticationResponse;
import com.codewithtabai.spring.bibliotheque.entities.Utilisateur;
import com.codewithtabai.spring.bibliotheque.services.UtilisateurService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;





import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
@CrossOrigin(origins = "http://localhost:4200")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    // GET /api/utilisateurs
    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    // GET /api/utilisateurs/{id}
    @GetMapping("/{id}")
    public Utilisateur getUtilisateurById(@PathVariable("id") Long id) {
        return utilisateurService.getUtilisateurById(id);
    }

    // POST /api/utilisateurs
    @PostMapping
    public Utilisateur ajouterUtilisateur(@RequestBody Utilisateur user) {
        return utilisateurService.ajouterUtilisateur(user);
    }

    // PUT /api/utilisateurs/{id}
    @PutMapping("/{id}")
    public Utilisateur modifierUtilisateur(@PathVariable("id") Long id, @RequestBody Utilisateur user) {
        user.setUserId(id);
        return utilisateurService.modifierUtilisateur(user);
    }

    // DELETE /api/utilisateurs/{id}
    @DeleteMapping("/{id}")
    public void supprimerUtilisateur(@PathVariable("id") Long id) {
        utilisateurService.supprimerUtilisateur(id);
    }

    // GET /api/utilisateurs/byEmail?email=xxx
    @GetMapping("/byEmail")
    public Utilisateur getUtilisateurByEmail(@RequestParam("email") String email) {
        return utilisateurService.getUtilisateurByEmail(email);
    }
    
 // Endpoint pour l'authentification
    @PostMapping("/authentifier")
    public AuthenticationResponse authentifier(@RequestBody AuthRequest authRequest) {
        return utilisateurService.authentifier(authRequest.getEmail(), authRequest.getMotDePasse());
    }
}
