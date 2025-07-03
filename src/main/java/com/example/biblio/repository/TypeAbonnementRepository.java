package com.example.biblio.repository;
import com.example.biblio.model.TypeAbonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeAbonnementRepository extends JpaRepository<TypeAbonnement, Integer>{
    
}

