
package com.example.biblio.service;
import com.example.biblio.dto.ReservationEnAttenteDTO;
import com.example.biblio.model.Pret;
import com.example.biblio.model.StatutPret;
import com.example.biblio.model.TypeStatutPret;
import com.example.biblio.repository.PretRepository;
import com.example.biblio.repository.StatutPretRepository;
import com.example.biblio.repository.TypeStatutPretRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StatutPretService {
    private StatutPretRepository statutPretRepository;
    @Autowired
    private PretRepository pretService;
    
    @Autowired
    private TypeStatutPretRepository typeStatutPretRepository;
    @Autowired
    public void setStatutPretRepository(StatutPretRepository departmentRepository) {
        this.statutPretRepository = departmentRepository;
    }
    
    public boolean rejeterPret(int pretId) {
        try {
            // Obtenir l'ancien statut actif
            StatutPret dernierStatut = statutPretRepository.findDernierStatutByPretId(pretId);
            if (dernierStatut != null) {
                dernierStatut.setDateFin(LocalDateTime.now());
                statutPretRepository.save(dernierStatut);
            }
    
            // Obtenir l’ID du type de statut "Rejette"
            TypeStatutPret typeRejette = typeStatutPretRepository.findByNom("Rejette");
            if (typeRejette == null) return false;
    
            // Créer un nouveau statut "Rejette"
            StatutPret nouveau = new StatutPret();
            nouveau.setPret(pretService.getById(pretId));
            nouveau.setDateDebut(LocalDateTime.now());
            nouveau.setTypeStatut(typeRejette);
            statutPretRepository.save(nouveau);
    
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    

    @Transactional
    public boolean validerPret(int pretId) {
        try {
            Integer idStatutMax = statutPretRepository.findMaxIdByPretId(pretId);
            if (idStatutMax != null) {
                statutPretRepository.updateDateFinById(idStatutMax);
            }

            StatutPret nouveau = new StatutPret();
            Pret pret = pretService.getById(pretId);
            if (pret == null) return false;

            nouveau.setPret(pret);
            nouveau.setDateDebut(LocalDateTime.now());

            TypeStatutPret enCours = typeStatutPretRepository.findByNom("En cours");
            if (enCours == null) return false;

            nouveau.setTypeStatut(enCours);
            statutPretRepository.save(nouveau);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public StatutPret save(StatutPret department) {
        return statutPretRepository.save(department);
    }
    public List<ReservationEnAttenteDTO> getReservationsEnAttente() {
        return statutPretRepository.findReservationsEnAttente();
    }
    public List<StatutPret> getAll() {
        return statutPretRepository.findAll();
    }

    public StatutPret getById(int id) {
        return statutPretRepository.findById(id).orElse(null);
    }
    public void deleteById(int id) {
        statutPretRepository.deleteById(id);
    }
 
}

