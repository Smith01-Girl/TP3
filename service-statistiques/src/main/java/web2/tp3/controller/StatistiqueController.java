package web2.tp3.controller;

import web2.tp3.dto.StatPopulariteDTO;
import web2.tp3.service.StatistiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@CrossOrigin(origins = {"http://localhost:5173","http://localhost:5174","http://localhost:5175"})
@RestController
@RequestMapping("/statistiques")
public class StatistiqueController {
    @Autowired
    private StatistiqueService statistiqueService;

    @GetMapping("/nombre-nouvelle")
    public int nombreNouvelle() throws InterruptedException{
        return statistiqueService.nombreNouvelle();
    }

    @GetMapping("/moins-populaire")
    public StatPopulariteDTO nouvelleMoinsPopulaire() throws InterruptedException{
        return statistiqueService.nouvelleMoinsPopulaire();
    }

    @GetMapping("/plus-populaire")
    public StatPopulariteDTO nouvellePlusPopulaire() throws InterruptedException{
        return statistiqueService.nouvellePlusPopulaire();
    }

    @GetMapping("/plus-courte")
    public int nouvellePlusCourte() throws InterruptedException{
        return statistiqueService.nouvellePlusCourte();
    }

    @GetMapping("/plus-longue")
    public int nouvellePlusLongue() throws InterruptedException{
        return statistiqueService.nouvellePlusLongue();
    }

    @GetMapping("/nombre-critere")
    public int nombreCritere() throws InterruptedException{
        return statistiqueService.nombreCritere();
    }

    @GetMapping("/nombre-nouvelle-courte")
    public Long getNombreNouvelleCourte() throws InterruptedException{
        return statistiqueService.nombreNouvelleCourte();
    }

    @GetMapping("/date-recente")
    public LocalDate getDateNouvelleRecente() throws InterruptedException{
        return statistiqueService.getDateNouvelleRecente();
    }

    @GetMapping("/date-vieille")
    public LocalDate getDateNouvelleVieille() throws InterruptedException{
        return statistiqueService.getDateNouvelleVieille();
    }
}
