package com.example.biblio.repository;
import com.example.biblio.model.ConditionPret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditionPretRepository extends JpaRepository<ConditionPret, Integer>{
    
}
