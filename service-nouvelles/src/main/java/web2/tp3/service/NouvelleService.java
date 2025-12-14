package web2.tp3.service;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web2.tp3.model.Nouvelle;
import web2.tp3.repository.NouvelleRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class NouvelleService {

    @Autowired
    private NouvelleRepository nouvelleRepository;

    private final Logger logger = LoggerFactory.getLogger(NouvelleService.class);
    private final ValidationNouvelle validationNouvelle = new ValidationNouvelle();

    @PostConstruct
    public void init() {
        logger.info("********* Nouvelle service started *********");
        if (nouvelleRepository.count() == 0) {
            createDefaultNouvelles();
        }
    }

    public Collection<Nouvelle> getAllNouvelles() {
        logger.info("NouvelleService appel getAllNouvelles des nouvelles");
        return nouvelleRepository.findAll();
    }

    public Nouvelle getNouvelleById(Long id) {
        logger.info("NouvelleService appel getNouvelleById des {}", id);
        return nouvelleRepository.findById(id).
                orElseThrow(() -> {
                    logger.error("Nouvelle pas trouver avec id: " + id);
                    return new RuntimeException("Nouvelle pas trouver avec id: " + id);
                });
    }

    public void createDefaultNouvelles() {
        logger.info("Création des nouvelles par defaut...");
        List<Nouvelle> defaultNouvelles = List.of(
                createNouvelle("K-Pop Demon Hunters detrone tout sur Netflix !",
                "https://sm.ign.com/ign_nordic/cover/k/k-pop-demo/k-pop-demon-hunters_y2mt.jpg",
                    LocalDate.parse("2025-09-10"),
                "Le film d’action fantastique sud-coréen K-Pop Demon Hunters est devenu le film le plus regardé sur Netflix cette semaine, dépassant tous les records. Mélange de chorégraphies de danse et combats surnaturels, il séduit un public mondial.",
                "K-Pop Demon Hunters explose les audiences Netflix grâce à son originalité.",
                        10
                ),
                createNouvelle(
                    "Denis Villeneuve lance enfin son film SF tant attendu",
                    "https://media.vanityfair.com/photos/66df24363c8c1e73ddc1ad43/master/w_2560%2Cc_limit/2038602752",
                    LocalDate.parse("2025-09-10"),
                "Le réalisateur canadien Denis Villeneuve a sorti Nebula’s Edge, un film de science-fiction explorant la colonisation spatiale et les dilemmes moraux liés à l’intelligence artificielle. La critique est unanime: un chef-d’œuvre visuel et philosophique.",
                "Nebula’s Edge mêle SF visuelle et réflexion éthique dans un univers captivant.",
                        57
                ),
                createNouvelle(
                        "Fast & Furious 11 promet encore plus d’adrénaline",
                         "https://i.ytimg.com/vi/bKC614AHNY4/maxresdefault.jpg",
                    LocalDate.parse("2025-09-14"),
                    "La franchise Fast & Furious revient avec son 11e opus, qui promet des courses-poursuites encore plus folles et des cascades inédites. Vin Diesel revient sur le devant de la scène dans ce film attendu par des millions de fans.",
                    "Fast & Furious 11 mise sur l’action démesurée pour ravir les fans.",
                        91
                ),
                createNouvelle("La série \"Shadow Realm\" renouvelée pour une saison 3",
                        "https://oyster.ignimgs.com/mediawiki/apis.ign.com/shadow-realms/9/93/Realms2.jpg",
                        LocalDate.parse("2025-09-12"),
                        "La série fantastique Shadow Realm vient d’être renouvelée pour une troisième saison après le succès critique et public de la saison 2. Les fans peuvent s’attendre à des intrigues encore plus sombres et complexes.",
                        "Shadow Realm continue de fasciner avec une nouvelle saison annoncée.",
                        52
                ),

                createNouvelle("Le biopic \"Tesla : Génie incompris\" séduit la presse",
                        "https://ichef.bbci.co.uk/ace/standard/976/cpsprodpb/F453/production/_119574526_gettyimages-545337079.jpg",
                        LocalDate.parse("2025-09-11"),
                        "Le film Tesla : Génie incompris, retraçant la vie tumultueuse de Nikola Tesla, reçoit des critiques élogieuses pour son interprétation intense et son réalisme historique. L’acteur principal est salué pour son rôle poignant.",
                        "Un biopic puissant qui redonne vie au génie Nikola Tesla."
                        ,23
                ),

                createNouvelle("Le thriller \"Silent Echo\" frappe fort sur HBO",
                        "https://m.media-amazon.com/images/M/MV5BN2M2YmY3YTItYTYzZS00OGVmLWFlNzAtYWMxMDY4NmQ2NzUwXkEyXkFqcGc@._V1_.jpg",
                        LocalDate.parse("2025-09-13"),
                        "La nouvelle série Silent Echo sur HBO explore les secrets d’une petite ville avec un suspense haletant. La réalisation et le jeu des acteurs sont particulièrement salués par la critique.",
                        "Silent Echo captive avec son ambiance mystérieuse et ses personnages profonds.",
                        12
                ),

                createNouvelle("Marvel annonce une nouvelle saga pour 2026",
                        "https://preview.redd.it/my-prediction-for-phase-6s-slate-based-on-the-recently-v0-wk3geo7thygd1.jpeg?width=1080&crop=smart&auto=webp&s=7e93a68812963321ee34aa9e74cf777304766715",
                        LocalDate.parse("2025-09-14"),
                        "Marvel Studios a révélé les grandes lignes de sa prochaine saga cinématographique qui débutera en 2026, promettant de nouveaux héros et des intrigues encore plus interconnectées à travers plusieurs films.",
                        "Une nouvelle ère Marvel s’ouvre avec une saga ambitieuse prévue pour 2026.",
                        64
                ),

                createNouvelle("Le film d’animation \"Dreamscape\" éblouit Cannes",
                        "https://californiaherps.com/films/filmimages/dreamscape.jpg",
                        LocalDate.parse("2025-09-10"),
                        "Présenté en avant-première à Cannes, Dreamscape est un film d’animation qui explore le monde des rêves avec une esthétique visuelle révolutionnaire. Il est déjà pressenti pour de nombreux prix internationaux.",
                        "Dreamscape enchante par son univers visuel onirique et innovant.",
                        71
                ),

                createNouvelle("La série \"Cyberfront\" choque avec sa saison 2",
                        "https://kitbash3d.com/_next/image?url=https%3A%2F%2Fcdn.shopify.com%2Fs%2Ffiles%2F1%2F2316%2F3287%2Fproducts%2FCBP_WEB_SET_GALLERY_01.jpg%3Fv%3D1755556430&w=2560&q=75",
                        LocalDate.parse("2025-09-13"),
                        "La saison 2 de Cyberfront a surpris par ses rebondissements audacieux et ses critiques sociales tranchantes. La série dystopique continue de diviser mais fascine les amateurs de SF.",
                        "Cyberfront pousse les limites du genre avec une deuxième saison controversée.",
                        36
                ),

                createNouvelle("Nouveau record pour la comédie \"Happy Failure\"",
                        "https://i0.wp.com/maze.fr/wp-content/uploads/2021/09/SHAMELESS4-1080x720.jpg?resize=1080%2C720",
                        LocalDate.parse("2025-09-15"),
                        "La comédie indépendante Happy Failure bat des records d’audience en streaming grâce à son humour frais et son regard tendre sur l’échec. Une bouffée d’air frais dans le paysage cinématographique.",
                        "Happy Failure séduit par son humour authentique et sa sincérité.",
                        80
                )
        );
        nouvelleRepository.saveAll(defaultNouvelles);
        logger.info("Les nouvelles par défaut on été créés avec succès! ({} éléments)", defaultNouvelles.size());
    }

    //Appel createNouvelle donnant la popularité par defaut -> 0
    public Nouvelle createNouvelle(String titre, String src, LocalDate date, String resume, String texte) {
        return createNouvelle(titre, src, date, resume, texte, 0);
    }

    public Nouvelle createNouvelle(String titre, String src, LocalDate date, String resume, String texte, Integer popularite) {
        Collection<Nouvelle> nouvelleList = getAllNouvelles();
        String msgValidation;

        //Vérifie unicité des titres
        boolean titreExiste = nouvelleList.stream()
                .anyMatch(n -> n.getTitre().equalsIgnoreCase(titre));

        if (titreExiste) {
            logger.error("Titre déjà existant! {}", titre);
            throw new IllegalArgumentException("Une nouvelle avec ce titre existe déjà : " + titre);
        }

        //Appel la validation de nouvelle
        msgValidation = validationNouvelle.validerNouvelle(titre,src,resume,texte);
        if (!msgValidation.isEmpty()) {
            logger.error(msgValidation);
            throw new IllegalArgumentException(msgValidation);
        }
        //Crée la nouvelle si il n'y a pas d'erreur dans les entrées
        Nouvelle newNouvelle = new Nouvelle();
        newNouvelle.setTitre(titre);
        newNouvelle.setSrc(src);
        newNouvelle.setDate(date);
        newNouvelle.setResume(resume);
        newNouvelle.setTexte(texte);

        if (popularite == null || popularite < 0) {
            newNouvelle.setPopularite(0);
        }else {
            newNouvelle.setPopularite(popularite);
        }


        logger.info("Une nouvelle a été créer ");
        return nouvelleRepository.save(newNouvelle);
    }

    public Nouvelle modifyNouvelle(Long id, Nouvelle editNouvelle) {
        logger.info("Modification de la nouvelle {}", editNouvelle.getTitre());
        Nouvelle nouvelle = nouvelleRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Nouvelle introuvable avec l'id: " + id));
        nouvelle.setTitre(editNouvelle.getTitre());
        nouvelle.setSrc(editNouvelle.getSrc());
        nouvelle.setDate(editNouvelle.getDate());
        nouvelle.setResume(editNouvelle.getResume());
        nouvelle.setTexte(editNouvelle.getTexte());

        return nouvelleRepository.save(nouvelle);
    }

    public void deleteNouvelle(Long id) {
        logger.info("Suppression de la nouvelle: {}", id);
        nouvelleRepository.deleteById(id);

        //Rénitialise quand le compteur des nouvelles est a 0 et appel la creation des nouvelles par defaut
        if (nouvelleRepository.count() == 0) {
            logger.warn("Toutes les nouvelles ont été supprimer - Réinitialisations des nouvelles");
            createDefaultNouvelles();
        }
    }






}
