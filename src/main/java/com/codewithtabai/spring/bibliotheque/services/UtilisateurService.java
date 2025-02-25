package com.codewithtabai.spring.bibliotheque.services;

import com.codewithtabai.spring.bibliotheque.dto.AuthenticationResponse;
import com.codewithtabai.spring.bibliotheque.dto.ChangePasswordRequest;
import com.codewithtabai.spring.bibliotheque.entities.Utilisateur;
import com.codewithtabai.spring.bibliotheque.repositories.UtilisateurRepository;
import com.codewithtabai.spring.bibliotheque.utils.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    
    @Autowired
    private JwtUtils jwtUtils; 

    /**
     * Crée un nouvel utilisateur en hachant systématiquement son mot de passe.
     * @param user l'utilisateur à créer (mot de passe en clair).
     * @return l'utilisateur créé (avec mot de passe haché).
     * @throws RuntimeException si l'email est déjà utilisé.
     */
    public Utilisateur ajouterUtilisateur(Utilisateur user) {
        // Vérifier email unique
        if (utilisateurRepository.findByUserEmail(user.getUserEmail()) != null) {
            throw new RuntimeException("Email déjà utilisé");
        }

        // Forcer le rôle à "USER"
        user.setRole("USER");

        // Hacher le mot de passe avant de l'enregistrer
        String motDePasseClair = user.getPassword();
        String hash = BCrypt.hashpw(motDePasseClair, BCrypt.gensalt());
        user.setPassword(hash);

        // Marquer qu'il a déjà changé son mot de passe (= true)
        // si tu veux qu'il *n'*ait *pas* à le changer à la première connexion
        user.setPasswordChanged(true);

        return utilisateurRepository.save(user);
    }


    /**
     * Authentifier un utilisateur : vérifie si l'email existe,
     * compare le mot de passe en clair avec le hash stocké.
     * @param email email de l'utilisateur
     * @param motDePasseEnClair mot de passe en clair
     * @return l'objet Utilisateur si OK, sinon null
     */
//    public Utilisateur authentifier(String email, String motDePasseEnClair) {
//        Utilisateur user = utilisateurRepository.findByUserEmail(email);
//        if (user == null) {
//            return null; // aucun user avec cet email
//        }
//
//        // Vérifier le hash
//        String hashEnBase = user.getPassword();
//        boolean match = BCrypt.checkpw(motDePasseEnClair, hashEnBase);
//        if (!match) {
//            return null; // mot de passe incorrect
//        }
//
//        return user; // authentification OK
//    }

    /**
     * Modifier un utilisateur. 
     * Si on veut changer le mot de passe, on doit re-hacher.
     * Sinon, on laisse tel quel.
     */
    public Utilisateur modifierUtilisateur(Utilisateur updatedUser) {
        // Récupérer l'utilisateur en base
        Optional<Utilisateur> opt = utilisateurRepository.findById(updatedUser.getUserId());
        if (opt.isEmpty()) {
            throw new RuntimeException("Utilisateur introuvable");
        }

        Utilisateur existingUser = opt.get();

        // Mettre à jour les champs simples
        existingUser.setUserNom(updatedUser.getUserNom());
        existingUser.setUserPrenom(updatedUser.getUserPrenom());
        existingUser.setUserEmail(updatedUser.getUserEmail());
        existingUser.setUserTel(updatedUser.getUserTel());
        existingUser.setRole(updatedUser.getRole());

        // Vérifier si le mot de passe a changé
        // 1) Si updatedUser.getPassword() est différent de "", on suppose qu'on veut changer
        // 2) Ou tu peux faire une autre logique (ex. un champ dédié "nouveauMotDePasse")
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            // Re-hachage
            String hash = BCrypt.hashpw(updatedUser.getPassword(), BCrypt.gensalt());
            existingUser.setPassword(hash);
            existingUser.setPasswordChanged(true); // on marque qu'il a changé
        }

        return utilisateurRepository.save(existingUser);
    }

    /**
     * Supprimer un utilisateur par son ID.
     */
    public void supprimerUtilisateur(Long userId) {
        utilisateurRepository.deleteById(userId);
    }

    /**
     * Récupérer un utilisateur par ID.
     */
    public Utilisateur getUtilisateurById(Long userId) {
        return utilisateurRepository.findById(userId).orElse(null);
    }
    
    public List<Utilisateur> rechercheParNomOuPrenom(String term) {
        // On appelle la méthode du repository
        return utilisateurRepository.findByUserNomContainingIgnoreCaseOrUserPrenomContainingIgnoreCase(term, term);
    }


    /**
     * Récupérer la liste de tous les utilisateurs.
     */
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    /**
     * Récupérer un utilisateur par email.
     */
    public Utilisateur getUtilisateurByEmail(String email) {
        return utilisateurRepository.findByUserEmail(email);
    }
    
    public AuthenticationResponse authentifier(String email, String motDePasseClair) {
        Utilisateur user = utilisateurRepository.findByUserEmail(email);
        if (user == null) {
            throw new RuntimeException("Utilisateur non trouvé pour l'email : " + email);
        }
        // Vérification du mot de passe avec BCrypt
        boolean match = BCrypt.checkpw(motDePasseClair, user.getPassword());
        if (!match) {
            throw new RuntimeException("Mot de passe incorrect");
        }
        // Si passwordChanged est false, cela signifie que l'utilisateur utilise le mot de passe par défaut
        boolean mustChangePassword = (user.getPasswordChanged() == null || !user.getPasswordChanged());

        // GÉNÉRER LE TOKEN
        // On suppose que tu as un champ "role" dans l'entité Utilisateur (ex: user.getRole())
        String token = jwtUtils.generateToken(user.getUserEmail(), user.getRole());
        
        // On retourne la réponse contenant l'utilisateur, mustChangePassword et maintenant le token
        return new AuthenticationResponse(user, mustChangePassword, token);
    }

    
 // Méthode pour changer le mot de passe
    public Utilisateur changePassword(Long userId, ChangePasswordRequest request) {
        Optional<Utilisateur> opt = utilisateurRepository.findById(userId);
        if (opt.isEmpty()) {
            throw new RuntimeException("Utilisateur non trouvé");
        }
        Utilisateur user = opt.get();
        // Vérifier l'ancien mot de passe
        if (!BCrypt.checkpw(request.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Ancien mot de passe incorrect");
        }
        // Vérifier que le nouveau mot de passe et la confirmation correspondent
        if (!request.getNewPassword().equals(request.getConfirmNewPassword())) {
            throw new RuntimeException("Le nouveau mot de passe et sa confirmation ne correspondent pas");
        }
        // Hacher le nouveau mot de passe
        String newHash = BCrypt.hashpw(request.getNewPassword(), BCrypt.gensalt());
        user.setPassword(newHash);
        user.setPasswordChanged(true);
        return utilisateurRepository.save(user);
    }
    
    
    
}
