package web2.tp3.service;


import org.springframework.web.reactive.function.client.WebClient;
import web2.tp3.dto.CritereDTO;
import web2.tp3.dto.NouvelleDTO;
import web2.tp3.dto.StatPopulariteDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;


@Service
public class StatistiqueService {

    private final WebClient webClient;

    public StatistiqueService(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    private final Logger logger = LoggerFactory.getLogger(StatistiqueService.class);

    public List<NouvelleDTO> getNouvelles() {
        return webClient.get()
                .uri("http://service-nouvelles:8082/nouvelles")
                .retrieve()
                .bodyToFlux(NouvelleDTO.class)
                .collectList()
                .block();
    }

    public List<CritereDTO> getCriteres() {
        return webClient.get()
                .uri("http://service-criteres:8083/criteres")
                .retrieve()
                .bodyToFlux(CritereDTO.class)
                .collectList()
                .block();
    }


    public int nombreNouvelle() {
        logger.info("Get le nombre nouvelle depuis statistique");
        return getNouvelles().size();
    }

    public StatPopulariteDTO nouvelleMoinsPopulaire() {
        logger.info("Get la nouvelle la moins populaire depuis statistique");
        return getNouvelles().stream()
                .min(Comparator.comparingInt(NouvelleDTO::getPopularite)) //Compare pour la moins populaire (min)
                .map(nouvelle -> new StatPopulariteDTO(nouvelle.getTitre(), nouvelle.getPopularite())) //Creer objet StatPopularite
                .orElse(new StatPopulariteDTO("Aucune nouvelle", 0)); //Si vide
    }

    public StatPopulariteDTO nouvellePlusPopulaire() {
        logger.info("Get la nouvelle la plus populaire depuis statistique");
        return getNouvelles().stream()
                .max(Comparator.comparingInt(NouvelleDTO::getPopularite)) //Compare pour la plus populaire (max)
                .map(nouvelle -> new StatPopulariteDTO(nouvelle.getTitre(), nouvelle.getPopularite())) //Creer objet StatPopularite
                .orElse(new StatPopulariteDTO("Aucune nouvelle", 0)); //Si vide
    }

    public int nouvellePlusCourte() {
        logger.info("Get la nouvelle la plus courte depuis statistique");
        int courte = Integer.MAX_VALUE;
        for (NouvelleDTO nou : getNouvelles()) {
            int taille = nou.getResume().length() + nou.getTexte().length();
            if (courte > taille) {
                courte = taille;
            }
        }
        return courte;
    }

    public int nouvellePlusLongue() {
        logger.info("Get la nouvelle la longue depuis statistique");
        int longue = 0;
        for (NouvelleDTO nou : getNouvelles()) {
            int taille = nou.getResume().length() + nou.getTexte().length();
            if (longue < taille) {
                longue = taille;
            }
        }
        return longue;
    }

    public int nombreCritere() {
        logger.info("Get le nombre de critere depuis statistique");
        return getCriteres().size();
    }

    public Long nombreNouvelleCourte() {
        logger.info("Get le nombre de nouvelle courte depuis statistique");
        return getNouvelles().stream()
                .filter((nouvelle)->((nouvelle.getTexte().length() + nouvelle.getResume().length())<=275)).count();
    }

    public LocalDate getDateNouvelleRecente() {
        logger.info("Get la date de la nouvelle la plus recente depuis statistique");
        return getNouvelles().stream()
                .map(NouvelleDTO::getDate)
                .max(LocalDate::compareTo) //max = plus recente
                .orElse(null);
    }

    public LocalDate getDateNouvelleVieille() {
        logger.info("Get la date de la nouvelle la plus vieille depuis statistique");
        return getNouvelles().stream()
                .map(NouvelleDTO::getDate)
                .min(LocalDate::compareTo) //min = plus ancienne
                .orElse(null);
    }
}
