package com.example.biblio.controller;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.biblio.service.AbonnementService;
import com.example.biblio.model.*;


@Controller
@RequestMapping("/abonnements")

public class AbonnementController {
 //   @Autowired
   // private AbonnementEnCoursService abonnementService;
    @Autowired
    private AbonnementService abonnementService;



    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(LocalDate.parse(text, DateTimeFormatter.ISO_LOCAL_DATE));
            }

            @Override
            public String getAsText() {
                LocalDate date = (LocalDate) getValue();
                return (date != null ? date.format(DateTimeFormatter.ISO_LOCAL_DATE) : "");
            }
        });
    }

    // @GetMapping("/")
    // public String listAbonnements(Model model) {
    //     List<AbonnementEnCours> abonnements = abonnementService.getAbonnementsActifs();
    //     System.out.println("Abonnements recuperes depuis la vue :");
    //     for (AbonnementEnCours ab : abonnements) {
    //         System.out.println("ID: " + ab.getAbonnementId() +
    //                            ", Nom: " + ab.getNomAdherant() +
    //                            ", Type: " + ab.getTypeAbonnement() +
    //                            ", Début: " + ab.getDateDebut() +
    //                            ", Fin: " + ab.getDateFin());
    //     }
    
    //     model.addAttribute("abonnements", abonnements);
    //     return "abonnements"; // nom du fichier JSP sans extension
    // }
    
    @GetMapping("/active")
    public String getActiveAbonnements(Model model) {
        List<Abonnement> abonnements = abonnementService.getActiveAbonnements();
        model.addAttribute("abonnements", abonnements);
        return "abonnements/active-abonnements";
    }
    @GetMapping("/test")
    public String listAbonnementsAll(Model model) {
        List<Abonnement> abonnements = abonnementService.getAll();
        System.out.println("Abonnements  recuperes depuis la vue :");
        for (Abonnement ab : abonnements) {
            System.out.println("ID: " + ab.getId() +
                               ", Nom: " + ab.getAdherant() +
                               ", Type: " + ab.getTypeAbonnement() +
                               ", Début: " + ab.getDateDebut() +
                               ", Fin: " + ab.getDateFin());
        }
      
        model.addAttribute("abonnements", abonnements);
        return "abonnements"; // nom du fichier JSP sans extension
    }
}
