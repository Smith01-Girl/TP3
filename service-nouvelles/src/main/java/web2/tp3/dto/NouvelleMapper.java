package web2.tp3.dto;

import web2.tp3.model.Nouvelle;

public class NouvelleMapper {

    //Entite Nouvelle vers son DTO
    public static NouvelleDTO toDTO(Nouvelle n) {
        NouvelleDTO dto = new NouvelleDTO();
        dto.setId(n.getId());
        dto.setTitre(n.getTitre());
        dto.setSrc(n.getSrc());
        dto.setDate(n.getDate());
        dto.setResume(n.getResume());
        dto.setTexte(n.getTexte());
        return dto;
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
