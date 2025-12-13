package web2.tp3.service;

import org.springframework.stereotype.Component;
import web2.tp3.model.UserDTO;

import java.time.LocalDate;
import java.util.regex.Pattern;

@Component
public class ValidationUser {
    public String valider(UserDTO user) {

        if (user == null) {
            return "L'utilisateur ne peut pas être null.";
        }


        if (user.getNom() == null || user.getNom().isBlank()) {
            return "Le nom est requis.";
        }


        if (user.getEmail() == null || user.getEmail().isBlank()) {
            return "L'email est requis.";
        }
        if (!isValidEmail(user.getEmail())) {
            return "L'email est invalide.";
        }


        if (user.getPassword() == null || user.getPassword().isBlank()) {
            return "Le mot de passe est requis.";
        }
        if (user.getPassword().length() < 4) {
            return "Le mot de passe doit contenir au moins 4 caractères.";
        }


        if (user.getRole() == null) {
            return "Le rôle est requis.";
        }


        if (user.getDateNaissance() == null) {
            return "La date de naissance est requise.";
        }
        if (user.getDateNaissance().isAfter(LocalDate.now())) {
            return "La date de naissance doit être dans le passé.";
        }

        // Aucun problème
        return "";
    }

    private boolean isValidEmail(String email) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return Pattern.matches(regex, email);
    }
}
