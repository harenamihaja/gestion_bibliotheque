
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
        this.filmRepository = departmentRepository;
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

