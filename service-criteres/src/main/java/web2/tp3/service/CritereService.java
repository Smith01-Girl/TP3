//package web2.tp3.service;
//
//import web2.tp3.model.Critere;
//import web2.tp3.model.TypeCritere;
//import web2.tp3.model.Nouvelle;
//import web2.tp3.model.User;
//
//import web2.tp3.repository.CritereRepository;
//import web2.tp3.service.ValidationCritere;
//import web2.tp3.service.UserService;
//import web2.tp3.service.NouvelleService;
//import web2.tp3.events.UserDeleteEvent;
//
//
//import jakarta.transaction.Transactional;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.Collection;
//import java.util.List;
//
//@Service
//public class CritereService implements CommandLineRunner {
//
//    private final CritereRepository critereRepository;
//    private final Logger logger = LoggerFactory.getLogger(CritereService.class);
//    private final ValidationCritere validationCritere;
//    private final UserService userService;
//    private final NouvelleService nouvelleService;
//
//    //injecter le service nouvelle
//    @Autowired
//    public CritereService(CritereRepository critereRepository, ValidationCritere validationCritere, UserService userService, NouvelleService nouvelleService) {
//        this.critereRepository = critereRepository;
//        this.validationCritere = validationCritere;
//        this.userService = userService;
//        this.nouvelleService = nouvelleService;
//    }
//
//    @Override
//    public void run(String... args) {
//        if (critereRepository.count() == 0) {
//            logger.info("Initialisation de la base de données...");
//            logger.info("Création de 5 critères par défaut...");
//            System.out.println("Création de 5 critères par défaut...");
//            creerCriteresParDefaut();
//            logger.info("Base de données initialisée avec {} criteres.", critereRepository.count());
//        }
//    }
//
//    private void creerCriteresParDefaut() {
//
//        //ajout des users pour la creation des criteres par defaults
//        List<User>userList = userService.getAllUsers();
//        if (userList==null || userList.isEmpty()) {
//            logger.info("Aucun utilisateur trouvé — création des utilisateurs par défaut...");
//            userService.createDefaultUsers();
//            userList = userService.getAllUsers();
//        }
//        TypeCritere [] typeCriteres = new TypeCritere[]{
//                TypeCritere.CHRONOLOGIE,TypeCritere.CHRONOLOGIE,TypeCritere.LONGUEUR,TypeCritere.LONGUEUR
//                ,TypeCritere.RECHERCHE
//        };
//        for (int i = 0; i <typeCriteres.length ; i++) {
//            //on utilise modilo pour distribuer entre les user
//            User user = userList.get(i%userList.size());
//            Critere critereDef = new Critere();
//            critereDef.setType(typeCriteres[i]);
//            critereDef.setIdUtilisateur(user.getId());
//            critereDef.setUtilisateurNom(user.getNom());
//            critereDef.setCreeQuand(LocalDateTime.now());
//            String validation = validationCritere.valider(critereDef);
//            if (!validation.isEmpty()){
//                logger.warn("Critère par défaut non valide (type={}): {}", typeCriteres[i], validation);
//                continue;
//            }
//            critereRepository.save(critereDef);
//            logger.info("Critère par défaut créé: type={} pour utilisateur {} ({})", typeCriteres[i], user.getId(), user.getNom());
//        }
//        logger.info("Création des critères par défaut terminée. Total = {}", critereRepository.count());
//    }
//
//
//    public Collection<Critere> getAllCriteres() {
//        logger.info("CritereService -> getAllCriteres");
//        return critereRepository.findAll();
//    }
//
//    public Critere creerCritere(Critere critere){
//        logger.info("Création du critère : {}", critere.getType());
//        String messageErreur = validationCritere.valider(critere);
//        if(!messageErreur.isEmpty()){
//            logger.error("Ecchec de la validation :{}",messageErreur);
//            throw new IllegalArgumentException(messageErreur);
//        }
//        // définir la date
//        critere.setCreeQuand(LocalDateTime.now());
//        if (critere.getUtilisateurNom() == null) {
//            User user = userService.getUserById(critere.getIdUtilisateur());
//            critere.setUtilisateurNom(user.getNom());
//        }
//        logger.info("Critère créé avec succès : {}",critere.getType());
//        return critereRepository.save(critere);
//    }
//
//    //  Obtenir un critère par ID
//    public Critere getCritereById(Long id) {
//        logger.info("CritereService -> getCritereById({})", id);
//        return critereRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Critère introuvable avec l'id " + id));
//    }
//
//    //  Mettre à jour un critère existant
//    public Critere mettreAJourCritere(Long id, Critere nouveauCritere) {
//        logger.info("CritereService -> mettreAJourCritere({})", id);
//
//        Critere critereExistant = critereRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Critère introuvable avec l'id " + id));
//
//
//        String messageErreur = validationCritere.valider(nouveauCritere);
//        if (!messageErreur.isEmpty()) {
//            logger.error("Échec de la validation : {}", messageErreur);
//            throw new IllegalArgumentException(messageErreur);
//        }
//
//        // Mise à jour des champs
//        if (nouveauCritere.getType() != null) {
//            critereExistant.setType(nouveauCritere.getType());
//        }
//        if (nouveauCritere.getIdUtilisateur() != null) {
//            critereExistant.setIdUtilisateur(nouveauCritere.getIdUtilisateur());
//        }
//
//        logger.info("Critere mis à jour : {}", critereExistant);
//        return critereRepository.save(critereExistant);
//    }
//
//    public void supprimerCritere(Long id) {
//
//        logger.info("Suppression du critère id={}", id);
//        critereRepository.deleteById(id);
//
//        // Vérifie si la table est vide
//        if (critereRepository.count() == 0) {
//            logger.warn("Tous les critères ont été supprimés — recréation des critères par défaut...");
//            creerCriteresParDefaut();
//        }
//    }
//    /**
//     * Supprime tous les critères associés à un utilisateur lorsque celui-ci est supprimé.
//     * Écoute les UserDeleteEvent publiés par le module utilisateur.
//     */
//    @EventListener
//    @Transactional
//    public void supprimerCriteresUser(UserDeleteEvent event) {
//        Long userId = event.getUserId();
//        logger.info("CritereService -> supprimerCriteresUser : pour Id user={}",userId);
//        //suppression
//        critereRepository.deleteByIdUtilisateur(userId);
//        logger.info("CritereService -> critères supprimés pour userId={}", userId);
//
//        // Si la table devient vide, recréer les critères par défaut (comportement existant)
//        if (critereRepository.count() == 0) {
//            logger.warn("Tous les critères supprimés après suppression d'utilisateur — recréation des critères par défaut...");
//            creerCriteresParDefaut();
//        }
//    }
//
//    public List<Nouvelle> getNouvellesByCritere(Long critereId) {
//
//        logger.info("Récupération des nouvelles pour le critère id={}", critereId);
//        Critere critere = getCritereById(critereId);
//
//        // On récupère tout, et on initialise le retour avec cette liste par défaut
//        List<Nouvelle> nouvelles = (List<Nouvelle>) nouvelleService.getAllNouvelles();
//        List<Nouvelle> nouvellesRetour = nouvelles;
//
//        if (critere == null) {
//            throw new IllegalArgumentException("Le critère ne peut pas être null : " + critereId);
//        }
//
//        if (critere.getType().equals(TypeCritere.LONGUEUR)) {
//            // Tri par longueur de texte (croissant)
//            nouvellesRetour = nouvelles.stream()
//                    .sorted((a, b) -> a.getTexte().length() - b.getTexte().length())
//                    .toList();
//
//        } else if (critere.getType().equals(TypeCritere.CHRONOLOGIE)) {
//            // Tri par date : b comparé à a pour avoir le plus RÉCENT en premier
//            nouvellesRetour = nouvelles.stream()
//                    .sorted((a, b) -> b.getDate().compareTo(a.getDate()))
//                    .toList();
//
//        } else if (critere.getType().equals(TypeCritere.RECHERCHE)) {
//            // Filtre par mot-clé
//            if (critere.getRecherche() != null && !critere.getRecherche().isEmpty()) {
//                nouvellesRetour = nouvelles.stream()
//                        .filter(nouvelle -> nouvelle.getTexte().toLowerCase().contains(critere.getRecherche().toLowerCase()))
//                        .toList();
//            }
//        }
//
//        return nouvellesRetour;
//    }
//}
