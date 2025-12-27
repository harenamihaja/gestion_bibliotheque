package com.example.biblio.repository;
import com.example.biblio.model.StatutPret;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.biblio.dto.*;


@Repository
public interface StatutPretRepository extends JpaRepository<StatutPret, Integer>{
    @Query(value = """
        SELECT new com.example.biblio.dto.ReservationEnAttenteDTO(
            p.id,
            a.nom,
            e.id,
            l.titre,
            p.date_debut_prevision,
            sp.dateDebut
        )
        FROM StatutPret sp
        JOIN sp.pret p
        JOIN p.adherant a
        JOIN p.exemplaire e
        JOIN e.livre l
        WHERE sp.typeStatut.nom = 'En attente'
        AND sp.id = (
            SELECT MAX(sp2.id)
            FROM StatutPret sp2
            WHERE sp2.pret.id = p.id
        )
        ORDER BY p.id
""")
List<ReservationEnAttenteDTO> findReservationsEnAttente();

    @Query("SELECT MAX(s.id) FROM StatutPret s WHERE s.pret.id = :pretId")
    Integer findMaxIdByPretId(@Param("pretId") int pretId);

    @Modifying
    @Query("UPDATE StatutPret s SET s.dateFin = CURRENT_TIMESTAMP WHERE s.id = :id")
    void updateDateFinById(@Param("id") int id);


    @Query("SELECT sp FROM StatutPret sp WHERE sp.pret.id = :pretId ORDER BY sp.id DESC LIMIT 1")
    StatutPret findDernierStatutByPretId(@Param("pretId") int pretId);
    

}

