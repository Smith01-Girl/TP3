package web2.tp3.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NouvelleDTO {

    private Long id;
    private String titre;
    private String src;
    private LocalDate date;
    private String resume;
    private String texte;

    public NouvelleDTO() {}

    public NouvelleDTO(String titre, String src, LocalDate date, String resume, String texte) {
        this.titre = titre;
        this.src = src;
        this.date = date;
        this.resume = resume;
        this.texte = texte;
    }

}
