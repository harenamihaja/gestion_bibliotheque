package com.example.biblio.controller;

import com.example.biblio.dto.ReservationEnAttenteDTO;
import com.example.biblio.model.*;
import com.example.biblio.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/pret")
public class PretController {

    private final PretService pretService;
    private final StatutPretService statutPretService;
    private final AdherantService adherantService;
    private final ExemplaireService exemplaireService;
    private final TypePretService typePretService;
    private final TypeStatutPretService typeStatutPretService;

    @Autowired
    public PretController(
            PretService pretService,
            StatutPretService statutPretService,
            AdherantService adherantService,
            ExemplaireService exemplaireService,
            TypePretService typePretService,
            TypeStatutPretService typeStatutPretService

    ) {
        this.pretService = pretService;
        this.statutPretService = statutPretService;
        this.adherantService = adherantService;
        this.exemplaireService = exemplaireService;
        this.typePretService = typePretService;
        this.typeStatutPretService = typeStatutPretService;
    }

    @GetMapping("/rejeter/{id}")
public String rejeterPret(@PathVariable("id") int pretId, RedirectAttributes redirectAttributes) {
    boolean success = statutPretService.rejeterPret(pretId);
    if (success) {
        redirectAttributes.addFlashAttribute("success", "Prêt rejeté avec succès.");
    } else {
        redirectAttributes.addFlashAttribute("error", "Erreur lors du rejet du prêt.");
    }
    return "redirect:/pret/en-attente";
}
    @GetMapping("/valider/{id}")
    public String validerReservation(@PathVariable("id") int pretId, RedirectAttributes redirectAttributes) {
        boolean success = statutPretService.validerPret(pretId);
    
        if (success) {
            redirectAttributes.addFlashAttribute("success", "Réservation validée avec succès.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la validation.");
        }
    
        return "redirect:/pret/en-attente";
    }
    

    @GetMapping("/en-attente")
    public String afficherReservationsEnAttente(Model model) {
        List<ReservationEnAttenteDTO> reservations = statutPretService.getReservationsEnAttente();
        model.addAttribute("reservations", reservations);
        return "validation/pret"; // correspond au JSP
    }

    @PostMapping("/reservation")
    public String reserverExemplaire(
            @RequestParam("reservationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationDate,
            @RequestParam("exemplaireId") Long exemplaireId,RedirectAttributes redirectAttributes 
    ) {
        // Récupérations avec tes méthodes
        //mila ovaina le id adherant
        Adherant adherant = adherantService.getById(1);
        TypePret typePret = typePretService.getById(2);
        Exemplaire exemplaire = exemplaireService.getById(exemplaireId.intValue());
    
        // Création du prêt
        Pret pret = new Pret();
        pret.setAdherant(adherant);
        pret.setExemplaire(exemplaire);
        pret.setTypePret(typePret);
        pret.setDate_debut_prevision(reservationDate);
    
        Pret pretEnregistre = pretService.save(pret);
    
        // Création du statut de prêt
        StatutPret statut = new StatutPret();
        statut.setPret(pretEnregistre);
        statut.setDateDebut(LocalDateTime.now());
        statut.setTypeStatut(typeStatutPretService.getById(2));
    
        statutPretService.save(statut);

        redirectAttributes.addFlashAttribute("reservationSuccess", true);
        return "redirect:/exemplaires/disponibles";
    }
    
}
