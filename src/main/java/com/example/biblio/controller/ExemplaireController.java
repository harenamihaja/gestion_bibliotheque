package com.example.biblio.controller;


import com.example.biblio.model.Exemplaire;
import com.example.biblio.service.ExemplaireService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/exemplaires")
public class ExemplaireController {

    private final ExemplaireService exemplaireService;

    public ExemplaireController(ExemplaireService exemplaireService) {
        this.exemplaireService = exemplaireService;
    }

    @GetMapping("/disponibles")
    public List<Exemplaire> getExemplairesDisponibles(
            @RequestParam("dateDebut") 
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateDebut,
            @RequestParam("dateFin") 
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFin
    ) {
        System.out.println("dateDebut"+ dateDebut);
        System.out.println("dateFin"+ dateFin);
        return exemplaireService.getExemplairesDisponiblesEntre(dateDebut, dateFin);
    }
}