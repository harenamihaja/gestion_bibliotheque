package com.example.biblio.service;
import com.example.biblio.model.TypeAdherant;
import com.example.biblio.repository.TypeAdherantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeAdherantService {
    private TypeAdherantRepository filmRepository;
    
    @Autowired
    public void setConditionRepository(TypeAdherantRepository departmentRepository) {
        this.filmRepository = departmentRepository;
    }


    
    public TypeAdherant save(TypeAdherant department) {
        return filmRepository.save(department);
    }

    public List<TypeAdherant> getAll() {
        return filmRepository.findAll();
    }

    public TypeAdherant getById(int id) {
        return filmRepository.findById(id).orElse(null);
    }
    public void deleteById(int id) {
        filmRepository.deleteById(id);
    }
 
}



