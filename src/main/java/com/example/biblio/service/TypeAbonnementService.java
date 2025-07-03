package com.example.biblio.service;
import com.example.biblio.model.TypeAbonnement;
import com.example.biblio.repository.TypeAbonnementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeAbonnementService {
    private TypeAbonnementRepository filmRepository;
    
    @Autowired
    public void setConditionRepository(TypeAbonnementRepository departmentRepository) {
        this.filmRepository = departmentRepository;
    }


    
    public TypeAbonnement save(TypeAbonnement department) {
        return filmRepository.save(department);
    }

    public List<TypeAbonnement> getAll() {
        return filmRepository.findAll();
    }

    public TypeAbonnement getById(int id) {
        return filmRepository.findById(id).orElse(null);
    }
    public void deleteById(int id) {
        filmRepository.deleteById(id);
    }
 
}




