package com.example.biblio.service;

import com.example.biblio.model.Adherant;
import jakarta.persistence.EntityManager;
import com.example.biblio.repository.AdherantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdherantService {
    private AdherantRepository filmRepository;
    
    @Autowired
    public void setAdherantRepository(AdherantRepository departmentRepository) {
        this.filmRepository = departmentRepository;
    }
       
    @Autowired
    private EntityManager entityManager;
    

    public Adherant save(Adherant department) {
        return filmRepository.save(department);
    }

    public List<Adherant> getAll() {
        return filmRepository.findAll();
    }

    public Adherant getById(int id) {
        return filmRepository.findById(id).orElse(null);
    }

    public void deleteById(int id) {
        filmRepository.deleteById(id);
    }
 
}

