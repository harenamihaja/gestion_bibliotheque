package com.example.biblio.dto;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservationEnAttenteDTO {
    private Long idPret;
    private String nomAdherant;
    private Long idExemplaire;
    private String titreLivre;
    private LocalDate dateDebutPrevision;
    private LocalDateTime dateDebutStatut;

    public ReservationEnAttenteDTO(Long idPret, String nomAdherant, Long idExemplaire, String titreLivre, LocalDate dateDebutPrevision, LocalDateTime dateDebutStatut) {
        this.idPret = idPret;
        this.nomAdherant = nomAdherant;
        this.idExemplaire = idExemplaire;
        this.titreLivre = titreLivre;
        this.dateDebutPrevision = dateDebutPrevision;
        this.dateDebutStatut = dateDebutStatut;
    }

    // Getters & setters
    public Long getIdPret() {
        return idPret;
    }

    public void setIdPret(Long idPret) {
        this.idPret = idPret;
    }

    public String getNomAdherant() {
        return nomAdherant;
    }

    public void setNomAdherant(String nomAdherant) {
        this.nomAdherant = nomAdherant;
    }

    public Long getIdExemplaire() {
        return idExemplaire;
    }

    public void setIdExemplaire(Long idExemplaire) {
        this.idExemplaire = idExemplaire;
    }

    public String getTitreLivre() {
        return titreLivre;
    }

    public void setTitreLivre(String titreLivre) {
        this.titreLivre = titreLivre;
    }

    public LocalDate getDateDebutPrevision() {
        return dateDebutPrevision;
    }

    public void setDateDebutPrevision(LocalDate dateDebutPrevision) {
        this.dateDebutPrevision = dateDebutPrevision;
    }

    public LocalDateTime getDateDebutStatut() {
        return dateDebutStatut;
    }

    public void setDateDebutStatut(LocalDateTime dateDebutStatut) {
        this.dateDebutStatut = dateDebutStatut;
    }
}
