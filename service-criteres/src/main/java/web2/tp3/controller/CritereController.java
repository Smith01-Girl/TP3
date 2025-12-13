//package web2.tp3.controller;
//
//
//import web2.tp3.model.Critere;
//import web2.tp3.model.Nouvelle;
//import web2.tp3.service.CritereService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Collection;
//import java.util.List;
//
//
//@CrossOrigin(origins = {"http://localhost:5173","http://localhost:5174","http://localhost:5175"})
//@RequestMapping("/criteres")
//@RestController
//public class CritereController {
//
//    private final CritereService critereService;
//
//    @Autowired
//    public CritereController(CritereService critereService) {
//        this.critereService = critereService;
//    }
//   // private CritereRepository critereRepository;
//    private Logger logger = LoggerFactory.getLogger(CritereController.class);
//
//
//    // GET – obtenir tous les nouvelles filtrées
//    @GetMapping("nouvelles/{id}")
//    public List<Nouvelle> getNouvellesFiltrees(@PathVariable Long id) {
//        logger.info("CritereController -> getNouvellesFiltrees pour id={}", id);
//        return critereService.getNouvellesByCritere(id);
//
//    }
//
//    // GET – obtenir tous les critères
//    @GetMapping
//    public Collection<Critere> getAllCriteres() {
//        logger.info("CritereController -> getAllCriteres");
//        return critereService.getAllCriteres();
//    }
//
//    // GET – obtenir un critère par son ID
//    @GetMapping("/{id}")
//    public Critere getCritereById(@PathVariable Long id) {
//        return critereService.getCritereById(id);
//    }
//
//    // POST – ajouter un nouveau critère
//    @PostMapping
//    public Critere createCritere(@RequestBody Critere critere) {
//        return critereService.creerCritere(critere);
//    }
//
//    // DELETE – supprimer un critère
//    @DeleteMapping("/{id}")
//    public void deleteCritere(@PathVariable Long id) {
//        critereService.supprimerCritere(id);
//    }
//
//    // PATCH – modifier un critère
//    @PatchMapping("/{id}")
//    public Critere updateCritere(@PathVariable Long id, @RequestBody Critere critere) {
//        return critereService.mettreAJourCritere(id, critere);
//    }
//
//
//}
