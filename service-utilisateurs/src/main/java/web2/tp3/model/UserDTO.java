package web2.tp3.model;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserDTO {

       @NotBlank(message = "Le nom est requis")
       private String nom;

       @Email(message = "Email invalide")
       @NotBlank(message = "L'email est requis")
       private String email;

       @Size(min = 4, message = "Le mot de passe doit contenir au moins 4 caractères")
       private String password;

       @NotNull(message = "Le rôle est requis")
       private UserRole role;

       @NotNull(message = "La date de naissance est requise")
       @Past(message = "La date de naissance doit être dans le passé")
       private LocalDate dateNaissance;

       private LocalDateTime created;
}
