//package web2.tp3.service;
//
//import web2.tp3.model.Nouvelle;
//import web2.tp3.model.StatPopularite;
//import web2.tp3.service.StatistiqueService;
//import web2.tp3.service.CritereService;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.Collection;
//import java.util.Comparator;
//
//
//
//@Service
//public class StatistiqueService {
//
//    @Autowired
//    private NouvelleService nouvelleService;
//    @Autowired
//    private CritereService critereService;
//
//    private final Logger logger = LoggerFactory.getLogger(StatistiqueService.class);
//
//    public Collection<Nouvelle> getNouvelles() {
//        return nouvelleService.getAllNouvelles();
//    }
//
//    public int nombreNouvelle() {
//        logger.info("Get le nombre nouvelle depuis statistique");
//        return nouvelleService.getAllNouvelles().size();
//    }
//
//    public StatPopularite nouvelleMoinsPopulaire() {
//        logger.info("Get la nouvelle la moins populaire depuis statistique");
//        return getNouvelles().stream()
//                .min(Comparator.comparingInt(Nouvelle::getPopularite)) //Compare pour la moins populaire (min)
//                .map(nouvelle -> new StatPopularite(nouvelle.getTitre(), nouvelle.getPopularite())) //Creer objet StatPopularite
//                .orElse(new StatPopularite("Aucune nouvelle", 0)); //Si vide
//    }
//
//    public StatPopularite nouvellePlusPopulaire() {
//        logger.info("Get la nouvelle la plus populaire depuis statistique");
//        return getNouvelles().stream()
//                .max(Comparator.comparingInt(Nouvelle::getPopularite)) //Compare pour la plus populaire (max)
//                .map(nouvelle -> new StatPopularite(nouvelle.getTitre(), nouvelle.getPopularite())) //Creer objet StatPopularite
//                .orElse(new StatPopularite("Aucune nouvelle", 0)); //Si vide
//    }
//
//    public int nouvellePlusCourte() {
//        logger.info("Get la nouvelle la plus courte depuis statistique");
//        int courte = Integer.MAX_VALUE;
//        for (Nouvelle nou : getNouvelles()) {
//            int taille = nou.getResume().length() + nou.getTexte().length();
//            if (courte > taille) {
//                courte = taille;
//            }
//        }
//        return courte;
//    }
//
//    public int nouvellePlusLongue() {
//        logger.info("Get la nouvelle la longue depuis statistique");
//        int longue = 0;
//        for (Nouvelle nou : getNouvelles()) {
//            int taille = nou.getResume().length() + nou.getTexte().length();
//            if (longue < taille) {
//                longue = taille;
//            }
//        }
//        return longue;
//    }
//
//    public int nombreCritere() {
//        logger.info("Get le nombre de critere depuis statistique");
//        return critereService.getAllCriteres().size();
//    }
//
//    public Long nombreNouvelleCourte() {
//        logger.info("Get le nombre de nouvelle courte depuis statistique");
//        return getNouvelles().stream()
//                .filter((nouvelle)->((nouvelle.getTexte().length() + nouvelle.getResume().length())<=275)).count();
//    }
//
//    public LocalDate getDateNouvelleRecente() {
//        logger.info("Get la date de la nouvelle la plus recente depuis statistique");
//        return getNouvelles().stream()
//                .map(Nouvelle::getDate)
//                .max(LocalDate::compareTo) //max = plus recente
//                .orElse(null);
//    }
//
//    public LocalDate getDateNouvelleVieille() {
//        logger.info("Get la date de la nouvelle la plus vieille depuis statistique");
//        return getNouvelles().stream()
//                .map(Nouvelle::getDate)
//                .min(LocalDate::compareTo) //min = plus ancienne
//                .orElse(null);
//    }
//}
