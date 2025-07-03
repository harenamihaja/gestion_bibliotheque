package com.example.biblio.repository;
import com.example.biblio.model.StatutPret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatutPretRepository extends JpaRepository<StatutPret, Integer>{
    
}

