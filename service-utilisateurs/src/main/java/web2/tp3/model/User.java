package web2.tp3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Le nom est requis")
    @Column(nullable = false)
    private String nom;


    @Email(message = "Veuillez entrer un e-mail valide svp!")
    @NotBlank(message = "Un e-mail est requis")
    @Column(unique = true, nullable = false)
    private String email;

    @Size(min = 4, message = "Le mot de passe doit contenir au moins 4 caracteres")
    @NotBlank(message = "Le mot de passe est requis")
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Un role est requis")
    @Column(nullable = false)
    private UserRole role;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private LocalDate inscriptionDate;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int articleCount = 0;

}
