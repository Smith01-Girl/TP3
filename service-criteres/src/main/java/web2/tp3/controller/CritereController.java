package web2.tp3.controller;

import org.springframework.web.bind.annotation.*;
import web2.tp3.dto.NouvelleDTO;
import web2.tp3.model.Critere;
import web2.tp3.service.CritereService;

import java.util.List;

@RestController
@RequestMapping("/criteres")
public class CritereController {

    private final CritereService critereService;

    public CritereController(CritereService critereService) {
        this.critereService = critereService;
    }

    @PostMapping
    public Critere create(@RequestBody Critere critere) {
        return critereService.createCritere(critere);
    }

    @GetMapping
    public List<Critere> getAll() {
        return critereService.getAllCriteres();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        critereService.deleteCritere(id);
    }

    // Endpoint spécial : demande au service critère d'aller chercher les nouvelles filtrées
    @GetMapping("/{id}/nouvelles")
    public List<NouvelleDTO> getNouvellesByCritere(@PathVariable Long id) {
        return critereService.getNouvellesFiltrees(id);
    }
}