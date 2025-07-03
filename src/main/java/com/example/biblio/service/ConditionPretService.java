package com.example.biblio.service;
import com.example.biblio.model.ConditionPret;
import com.example.biblio.repository.ConditionPretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConditionPretService {
    private ConditionPretRepository filmRepository;
    
    @Autowired
    public void setConditionRepository(ConditionPretRepository departmentRepository) {
        this.filmRepository = departmentRepository;
    }


    
    public ConditionPret save(ConditionPret department) {
        return filmRepository.save(department);
    }

    public List<ConditionPret> getAll() {
        return filmRepository.findAll();
    }

    public ConditionPret getById(int id) {
        return filmRepository.findById(id).orElse(null);
    }
    public void deleteById(int id) {
        filmRepository.deleteById(id);
    }
 
}

