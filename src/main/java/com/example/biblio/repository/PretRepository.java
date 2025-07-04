package com.example.biblio.repository;
import com.example.biblio.model.Pret;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PretRepository extends JpaRepository<Pret, Integer>{
    @Query(value = """
        SELECT p.*
        FROM pret p
        JOIN (
            SELECT sp.id_pret, MAX(sp.id) AS max_statut_id
            FROM statut_pret sp
            GROUP BY sp.id_pret
        ) latest ON latest.id_pret = p.id
        JOIN statut_pret sp ON sp.id = latest.max_statut_id
        JOIN type_statut_pret tsp ON sp.id_type_statut = tsp.id
        WHERE tsp.nom = 'En cours'
          AND p.id_exemplaire = :exemplaireId
          AND p.date_debut_prevision IS NOT NULL
          AND :reservationDate BETWEEN p.date_debut_prevision AND (p.date_debut_prevision + (:delai * INTERVAL '1 day'))
        LIMIT 1
    """, nativeQuery = true)
    Optional<Pret> findPlageOccupee(Long exemplaireId, LocalDate reservationDate, int delai);
    
}