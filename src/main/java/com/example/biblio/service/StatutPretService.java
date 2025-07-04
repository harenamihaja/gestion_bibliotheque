
package com.example.biblio.service;
import com.example.biblio.model.StatutPret;
import com.example.biblio.repository.StatutPretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatutPretService {
    private StatutPretRepository filmRepository;
    
    @Autowired
    public void setStatutPretRepository(StatutPretRepository departmentRepository) {
<<<<<<< Updated upstream
        this.filmRepository = departmentRepository;
=======
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
    public List<ReservationEnAttenteDTO> getPretsEnCoursByAdherant(int adherantId) {
        return statutPretRepository.findPretsEnCoursByAdherant(adherantId);
    }

    public int countPretsEnCoursByAdherant(int adherantId) {
        return statutPretRepository.countPretsEnCoursByAdherant(adherantId);
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
>>>>>>> Stashed changes
    }

    public StatutPret save(StatutPret department) {
        return filmRepository.save(department);
    }

    public List<StatutPret> getAll() {
        return filmRepository.findAll();
    }

    public StatutPret getById(int id) {
        return filmRepository.findById(id).orElse(null);
    }
    public void deleteById(int id) {
        filmRepository.deleteById(id);
    }
 
}

