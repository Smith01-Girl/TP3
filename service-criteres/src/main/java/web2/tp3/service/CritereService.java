package web2.tp3.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import web2.tp3.dto.NouvelleDTO;
import web2.tp3.dto.UserDTO;
import web2.tp3.model.Critere;
import web2.tp3.model.TypeCritere;
import web2.tp3.repository.CritereRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CritereService {

    private final CritereRepository critereRepository;
    private final RestClient restClient;

    public CritereService(CritereRepository critereRepository, RestClient.Builder builder) {
        this.critereRepository = critereRepository;
        // On construit le client HTTP
        this.restClient = builder.build();
    }

    public Critere createCritere(Critere critere) {
        // 1. Appel REST pour récupérer le nom de l'utilisateur
        try {
            UserDTO user = restClient.get()
                    .uri("http://service-utilisateurs:8080/api/users/" + critere.getIdUtilisateur())
                    .retrieve()
                    .body(UserDTO.class);

            if (user != null) {
                critere.setUtilisateurNom(user.getNom());
            }
        } catch (Exception e) {
            System.out.println("Impossible de contacter service-utilisateurs : " + e.getMessage());
            critere.setUtilisateurNom("Inconnu");
        }

        return critereRepository.save(critere);
    }

    public List<Critere> getAllCriteres() {
        return critereRepository.findAll();
    }

    public void deleteCritere(Long id) {
        critereRepository.deleteById(id);
        // Ici, on pourrait appeler service-nouvelles pour notifier, si demandé
    }

    // Le fameux endpoint complexe qui appelle le service-nouvelles
    public List<NouvelleDTO> getNouvellesFiltrees(Long critereId) {
        Critere critere = critereRepository.findById(critereId)
                .orElseThrow(() -> new RuntimeException("Critère introuvable"));

        // 1. Récupérer TOUTES les nouvelles via REST
        List<NouvelleDTO> nouvelles;
        try {
            nouvelles = restClient.get()
                    .uri("http://service-nouvelles:8082/nouvelles")
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<NouvelleDTO>>() {});
        } catch (Exception e) {
            System.out.println("Service nouvelles indisponible");
            return List.of(); // Retourne vide si le service est down
        }

        if (nouvelles == null) return List.of();

        // 2. Filtrage Java (Logique du TP2)
        if (critere.getType() == TypeCritere.LONGUEUR) {
            return nouvelles.stream()
                    .sorted((a, b) -> (a.getTexte().length() + a.getResume().length()) - (b.getTexte().length() + b.getResume().length()))
                    .collect(Collectors.toList());
        } else if (critere.getType() == TypeCritere.CHRONOLOGIE) {
            return nouvelles.stream()
                    .sorted((a, b) -> b.getDate().compareTo(a.getDate()))
                    .collect(Collectors.toList());
        } else if (critere.getType() == TypeCritere.RECHERCHE && critere.getRecherche() != null) {
            String motCle = critere.getRecherche().toLowerCase();
            return nouvelles.stream()
                    .filter(n -> n.getTitre().toLowerCase().contains(motCle) || n.getTexte().toLowerCase().contains(motCle))
                    .collect(Collectors.toList());
        }

        return nouvelles;
    }
}