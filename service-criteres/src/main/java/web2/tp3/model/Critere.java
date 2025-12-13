package web2.tp3.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Critere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCritere;

    @Enumerated(EnumType.STRING)
    private TypeCritere type;

    private Long idUtilisateur; // On garde juste l'ID, pas l'objet User complet
    private String utilisateurNom; // On stocke le nom pour éviter de le demander à chaque fois
    private String recherche; // Mot clé si type RECHERCHE

    private LocalDateTime creeQuand;

    @PrePersist
    public void prePersist() {
        this.creeQuand = LocalDateTime.now();
    }
}