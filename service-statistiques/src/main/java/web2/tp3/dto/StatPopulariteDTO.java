package web2.tp3.dto;

import lombok.Getter;

@Getter
public class StatPopulariteDTO {
    private String titre;
    private int popularite;

    public StatPopulariteDTO(String titre, int popularite) {
        this.titre = titre;
        this.popularite = popularite;
    }

}
