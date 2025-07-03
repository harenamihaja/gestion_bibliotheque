package com.example.biblio.repository;
import com.example.biblio.model.TypeStatutPret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeStatutPretRepository extends JpaRepository<TypeStatutPret, Integer>{
    
}

