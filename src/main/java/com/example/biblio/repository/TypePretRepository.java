package com.example.biblio.repository;
import com.example.biblio.model.TypePret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypePretRepository extends JpaRepository<TypePret, Integer>{
    
}

