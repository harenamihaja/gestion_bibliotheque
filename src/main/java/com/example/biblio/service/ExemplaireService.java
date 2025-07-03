package com.example.biblio.service;

import com.example.biblio.model.Exemplaire;
import com.example.biblio.repository.ExemplaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExemplaireService {
    private ExemplaireRepository filmRepository;
    
    @Autowired
    public void setConditionRepository(ExemplaireRepository departmentRepository) {
        this.filmRepository = departmentRepository;
    }


    public List<Exemplaire> getExemplairesDisponiblesEntre(LocalDate dateDebut, LocalDate dateFin) {
        return filmRepository.findDisponiblesEntre(dateDebut, dateFin);
    }
    
    public Exemplaire save(Exemplaire department) {
        return filmRepository.save(department);
    }

    public List<Exemplaire> getAll() {
        return filmRepository.findAll();
    }

    public Exemplaire getById(int id) {
        return filmRepository.findById(id).orElse(null);
    }
    public void deleteById(int id) {
        filmRepository.deleteById(id);
    }
 
}

