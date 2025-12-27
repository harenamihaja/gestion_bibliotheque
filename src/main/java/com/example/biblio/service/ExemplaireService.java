package com.example.biblio.service;

import com.example.biblio.model.Exemplaire;
import com.example.biblio.model.Livre;
import com.example.biblio.repository.ExemplaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExemplaireService {
    private ExemplaireRepository exemplaireRepository;
    
    @Autowired
    public void setConditionRepository(ExemplaireRepository departmentRepository) {
        this.exemplaireRepository = departmentRepository;
    }


    public List<Exemplaire> getExemplairesDisponiblesEntre(LocalDate dateDebut, LocalDate dateFin) {
        return exemplaireRepository.findDisponiblesEntre(dateDebut, dateFin);
    }
    
    public Exemplaire save(Exemplaire department) {
        return exemplaireRepository.save(department);
    }
    

    public List<Exemplaire> getAvailableExemplaires() {
        return exemplaireRepository.findAvailableExemplaires();
    }
    public List<Exemplaire> getAll() {
        return exemplaireRepository.findAll();
    }

    public Exemplaire getById(int id) {
        return exemplaireRepository.findById(id).orElse(null);
    }
    public void deleteById(int id) {
        exemplaireRepository.deleteById(id);
    }
 
}

