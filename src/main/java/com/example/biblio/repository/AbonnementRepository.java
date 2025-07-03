package com.example.biblio.repository;

import com.example.biblio.model.Abonnement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AbonnementRepository extends JpaRepository<Abonnement, Integer> {
    // Tu peux ajouter des méthodes personnalisées ici, si besoin
   // FilmCategorie findByNom(String name);
   //Abonnement findByAdherant(int name);
   
   @Query("SELECT ab FROM Abonnement ab " +
   "JOIN ab.adherant ad " +
   "JOIN ab.typeAbonnement ta " +
   "WHERE CURRENT_DATE BETWEEN ab.dateDebut AND ab.dateFin")
    List<Abonnement> findActiveAbonnements();

}