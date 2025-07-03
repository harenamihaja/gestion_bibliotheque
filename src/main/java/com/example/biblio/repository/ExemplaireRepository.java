package com.example.biblio.repository;
import com.example.biblio.model.Exemplaire;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
public interface ExemplaireRepository extends JpaRepository<Exemplaire, Integer>{
    @Query(value = """
        SELECT e.*
        FROM exemplaire e
        WHERE e.id NOT IN (
            SELECT p.id_exemplaire
            FROM pret p
            JOIN statut_pret sp ON p.id = sp.id_pret
            JOIN type_statut_pret tsp ON tsp.id = sp.id_type_statut
            WHERE tsp.nom IN ('En cours', 'Retarde')
              AND (
                    sp.date_debut <= :dateFin
                AND sp.date_fin >= :dateDebut
              )
        )
        """, nativeQuery = true)
    List<Exemplaire> findDisponiblesEntre(
        @Param("dateDebut") LocalDate dateDebut,
        @Param("dateFin") LocalDate dateFin
    );
}
