package web2.tp3.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web2.tp3.dto.NouvelleDTO;
import web2.tp3.dto.NouvelleMapper;
import web2.tp3.model.Nouvelle;
import web2.tp3.service.NouvelleService;

import java.util.Collection;


@CrossOrigin(origins = {"http://localhost:5173","http://localhost:5174","http://localhost:5175"})
@RestController
@RequestMapping("/nouvelles")
public class NouvelleController {
    @Autowired
    private NouvelleService nouvelleService;

    private final Logger logger = LoggerFactory.getLogger(NouvelleController.class);

    @GetMapping
    public Collection<NouvelleDTO> getAllNouvelles() throws InterruptedException {
        return nouvelleService.getAllNouvelles()
                .stream()
                .map(NouvelleMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public NouvelleDTO getNouvelleById(@PathVariable Long id) throws InterruptedException {
        return NouvelleMapper.toDTO(
                nouvelleService.getNouvelleById(id)
        );
    }

    @PostMapping
    public NouvelleDTO  createNouvelle(@RequestBody NouvelleDTO  dto) throws InterruptedException {
        Nouvelle created = nouvelleService.createNouvelle(
                dto.getTitre(),
                dto.getSrc(),
                dto.getDate(),
                dto.getResume(),
                dto.getTexte()
        );
        return NouvelleMapper.toDTO(created);
    }

    @DeleteMapping("/{id}")
    public void deleteNouvelle(@PathVariable Long id) throws InterruptedException {
        nouvelleService.deleteNouvelle(id);
    }

    @PatchMapping("/{id}")
    public NouvelleDTO updateNouvelle(@PathVariable Long id, @RequestBody NouvelleDTO editNouvelle) throws InterruptedException {

        Nouvelle updated = nouvelleService.modifyNouvelle(
                id,
                NouvelleMapper.toEntity(editNouvelle)
        );
        return NouvelleMapper.toDTO(updated);

    }

}


