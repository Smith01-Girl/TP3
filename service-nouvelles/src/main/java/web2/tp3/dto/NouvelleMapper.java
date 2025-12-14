package web2.tp3.dto;

import web2.tp3.model.Nouvelle;

public class NouvelleMapper {

    //Entite Nouvelle vers son DTO
    public static NouvelleDTO toDTO(Nouvelle n) {
        return new NouvelleDTO(
                n.getTitre(),
                n.getSrc(),
                n.getDate(),
                n.getResume(),
                n.getTexte()
        );
    }
    //Entite DTO vers l'entite Nouvelle
    public static Nouvelle toEntity(NouvelleDTO dto) {
        Nouvelle n = new Nouvelle();
        n.setTitre(dto.getTitre());
        n.setSrc(dto.getSrc());
        n.setDate(dto.getDate());
        n.setResume(dto.getResume());
        n.setTexte(dto.getTexte());
        return n;
    }
}
