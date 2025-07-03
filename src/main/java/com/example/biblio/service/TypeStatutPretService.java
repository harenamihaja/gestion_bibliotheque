
package com.example.biblio.service;
import com.example.biblio.model.TypeStatutPret;
import com.example.biblio.repository.TypeStatutPretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeStatutPretService {
    private TypeStatutPretRepository filmRepository;
    
    @Autowired
    public void setConditionRepository(TypeStatutPretRepository departmentRepository) {
        this.filmRepository = departmentRepository;
    }


    
    public TypeStatutPret save(TypeStatutPret department) {
        return filmRepository.save(department);
    }

    public List<TypeStatutPret> getAll() {
        return filmRepository.findAll();
    }

    public TypeStatutPret getById(int id) {
        return filmRepository.findById(id).orElse(null);
    }
    public void deleteById(int id) {
        filmRepository.deleteById(id);
    }
 
}




