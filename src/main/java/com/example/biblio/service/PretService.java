package com.example.biblio.service;
import com.example.biblio.model.Pret;
import com.example.biblio.repository.PretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PretService {
    private PretRepository pretRepository;
    
    @Autowired
    public void setPretRepository(PretRepository departmentRepository) {
        this.pretRepository = departmentRepository;
    }
    public Optional<Pret> getPlageOccupee(Long exemplaireId, LocalDate reservationDate, int delai) {
        return pretRepository.findPlageOccupee(exemplaireId, reservationDate, delai);
    }
    
    public boolean isExemplaireDisponible(Long exemplaireId, LocalDate reservationDate, int delai) {
        return getPlageOccupee(exemplaireId, reservationDate, delai).isEmpty();
    }
    
    public Pret save(Pret department) {
        return pretRepository.save(department);
    }

    public List<Pret> getAll() {
        return pretRepository.findAll();
    }

    public Pret getById(int id) {
        return pretRepository.findById(id).orElse(null);
    }
    public void deleteById(int id) {
        pretRepository.deleteById(id);
    }
 
}

