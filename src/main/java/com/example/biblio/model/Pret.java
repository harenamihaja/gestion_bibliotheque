package com.example.biblio.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pret")
public class Pret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_adherant", nullable = false)
    private Adherant adherant;

    @ManyToOne
    @JoinColumn(name = "id_exemplaire", nullable = false)
    private Exemplaire exemplaire;

    @ManyToOne
    @JoinColumn(name = "id_type_pret", nullable = false)
    private TypePret typePret;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Adherant getAdherant() {
        return adherant;
    }

    public void setAdherant(Adherant adherant) {
        this.adherant = adherant;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    public TypePret getTypePret() {
        return typePret;
    }

    public void setTypePret(TypePret typePret) {
        this.typePret = typePret;
    }


    // Getters & setters
}
