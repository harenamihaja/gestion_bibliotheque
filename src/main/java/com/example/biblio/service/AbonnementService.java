package com.example.biblio.service;

import com.example.biblio.model.Abonnement;
import jakarta.persistence.EntityManager;
import com.example.biblio.repository.AbonnementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbonnementService {
    private AbonnementRepository abonnementRepository;
    
    @Autowired
    public void setAbonnementRepository(AbonnementRepository departmentRepository) {
        this.abonnementRepository = departmentRepository;
    }
       
    @Autowired
    private EntityManager entityManager;
    

    public Abonnement save(Abonnement department) {
        return abonnementRepository.save(department);
    }
    
    public List<Abonnement> getActiveAbonnements() {
        return abonnementRepository.findActiveAbonnements();
    }

    public List<Abonnement> getAll() {
        return abonnementRepository.findAll();
    }

    public Abonnement getById(int id) {
        return abonnementRepository.findById(id).orElse(null);
    }

    public void deleteById(int id) {
        abonnementRepository.deleteById(id);
    }
 
}
