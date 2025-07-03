package com.example.biblio.service;
import com.example.biblio.model.TypePret;
import com.example.biblio.repository.TypePretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypePretService {
    private TypePretRepository filmRepository;
    
    @Autowired
    public void setConditionRepository(TypePretRepository departmentRepository) {
        this.filmRepository = departmentRepository;
    }


    
    public TypePret save(TypePret department) {
        return filmRepository.save(department);
    }

    public List<TypePret> getAll() {
        return filmRepository.findAll();
    }

    public TypePret getById(int id) {
        return filmRepository.findById(id).orElse(null);
    }
    public void deleteById(int id) {
        filmRepository.deleteById(id);
    }
 
}




