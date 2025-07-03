package com.example.biblio.service;
import com.example.biblio.model.Livre;
import com.example.biblio.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivreService {
    private LivreRepository filmRepository;
    
    @Autowired
    public void setConditionRepository(LivreRepository departmentRepository) {
        this.filmRepository = departmentRepository;
    }


    
    public Livre save(Livre department) {
        return filmRepository.save(department);
    }

    public List<Livre> getAll() {
        return filmRepository.findAll();
    }

    public Livre getById(int id) {
        return filmRepository.findById(id).orElse(null);
    }
    public void deleteById(int id) {
        filmRepository.deleteById(id);
    }
 
}


