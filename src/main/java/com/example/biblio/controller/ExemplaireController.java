package com.example.biblio.controller;


import com.example.biblio.model.Exemplaire;
import com.example.biblio.model.Livre;
import com.example.biblio.service.ExemplaireService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/exemplaires")
public class ExemplaireController {

    private final ExemplaireService exemplaireService;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ExemplaireController(JdbcTemplate jdbcTemplate,ExemplaireService exemplaireService) {
        this.jdbcTemplate = jdbcTemplate;
        this.exemplaireService = exemplaireService;

    }

    @GetMapping("/date-debut-prevision/{exemplaireId}")
    @ResponseBody
    public Map<String, Object> getDateDebutPrevision(@PathVariable Long exemplaireId) {
        String sql = """
            SELECT 
                p.id AS id_pret,
                p.date_debut_prevision
            FROM pret p
            LEFT JOIN statut_pret sp ON p.id = sp.id_pret
            LEFT JOIN type_statut_pret tsp ON sp.id_type_statut = tsp.id
            WHERE p.id_exemplaire = ?
              AND tsp.nom = 'En cours'
              AND p.date_debut_prevision > CURRENT_DATE
            ORDER BY p.date_debut_prevision ASC
            LIMIT 1
        """;

        try {
            Map<String, Object> result = jdbcTemplate.queryForMap(sql, exemplaireId);
            return Map.of("dateDebutPrevision", result.get("date_debut_prevision").toString());
        } catch (Exception e) {
            return Map.of("dateDebutPrevision", "");
        }
    }
    

    @GetMapping("/disponibles")
    public String getAvailableExemplaires(Model model) {
        List<Exemplaire> exemplaires = exemplaireService.getAvailableExemplaires();
        model.addAttribute("exemplaires", exemplaires);
        return "exemplaire/disponible";
    }
    @GetMapping("/reservation")
    public String reserverExemplaire(Model model) {
        List<Exemplaire> livres = exemplaireService.getAvailableExemplaires();
        model.addAttribute("livres", livres);
        return "exemplaire/disponible";
    }



    // @GetMapping("/disponibles")
    // public String getExemplairesDisponibles(
    //         @RequestParam("dateDebut") 
    //         @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateDebut,
    //         @RequestParam("dateFin") 
    //         @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFin
    //         ,Model model
    // ) {
    //     System.out.println("dateDebut"+ dateDebut);
    //     System.out.println("dateFin"+ dateFin); 
    //     List<Exemplaire> abonnements = exemplaireService.getExemplairesDisponiblesEntre(dateDebut, dateFin);
    //     model.addAttribute("exemplaires", abonnements);
    //     return "exemplaire/disponible";
    // }
}