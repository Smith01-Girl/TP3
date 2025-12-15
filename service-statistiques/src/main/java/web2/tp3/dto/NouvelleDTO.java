package web2.tp3.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NouvelleDTO {
    private Long id;
    private String titre;
    private String resume;
    private String texte;
    private int popularite;
    private LocalDate date;

}
