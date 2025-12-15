package web2.tp3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Nouvelle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Un titre est requis")
    @Size(min = 4, max = 75, message = "Le titre doit être entre 4 et 75 caractères")
    @Column(unique = true, nullable = false)
    private String titre;

    @Column(nullable = false)
    private String src;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate date;

    @Size(max = 300, message = "Le résumé doit être moins de 300 caractères")
    @Column(nullable = false)
    private String resume;

    @Size(max = 250, message = "Le texte doit être moins de 150 caractères")
    @Column(nullable = false)
    private String texte;

    @Column(nullable = false)
    private Integer popularite = 0;

    public Nouvelle(String titre, String src, LocalDate date, String resume, String texte) {
        this.titre = titre;
        this.src = src;
        this.date = date;
        this.resume = resume;
        this.texte = texte;
    }
}
