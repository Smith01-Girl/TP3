//package web2.tp3.model;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import jakarta.persistence.*;
//import lombok.*;
//import org.springframework.data.annotation.Id;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@EqualsAndHashCode
//@Data
//public class Critere {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long idCritere;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)      // Protection BD
//    private TypeCritere type;
//
//    @Column(nullable = false)      // Protection BD
//    private Long idUtilisateur;
//
//    //private Long idNouvelle;
//
//    private String utilisateurNom;
//
//    private String recherche;
//
//    @Column(nullable = false)
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime creeQuand;
//
//
//    public Critere(TypeCritere type) {this.type=type;}
//    public Critere(TypeCritere type,Long idUtilisateur) {this.type=type; this.idUtilisateur=idUtilisateur;}
//
//}
