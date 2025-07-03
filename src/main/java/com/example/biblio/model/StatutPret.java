package com.example.biblio.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "statut_pret")
public class StatutPret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Clé étrangère vers type_statut_pret
    @ManyToOne
    @JoinColumn(name = "id_type_statut")
    private TypeStatutPret typeStatut;

    @Column(name = "date_debut")
    private LocalDateTime dateDebut;

    @Column(name = "date_fin")
    private LocalDateTime dateFin;

    @Column(name = "id_pret")
    private Long idPret;

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeStatutPret getTypeStatut() {
        return typeStatut;
    }

    public void setTypeStatut(TypeStatutPret typeStatut) {
        this.typeStatut = typeStatut;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public Long getIdPret() {
        return idPret;
    }

    public void setIdPret(Long idPret) {
        this.idPret = idPret;
    }
}
