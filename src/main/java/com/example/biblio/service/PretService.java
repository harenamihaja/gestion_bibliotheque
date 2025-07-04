package com.example.biblio.service;
import com.example.biblio.model.Pret;
import com.example.biblio.repository.PretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PretService {
    private PretRepository filmRepository;
    
    @Autowired
    public void setPretRepository(PretRepository departmentRepository) {
        this.filmRepository = departmentRepository;
    }

    public Pret save(Pret department) {
        return filmRepository.save(department);
    }

    public List<Pret> getAll() {
        return filmRepository.findAll();
    }

    public Pret getById(int id) {
        return filmRepository.findById(id).orElse(null);
    }
    
    public void deleteById(int id) {
        filmRepository.deleteById(id);
    }
 
}

