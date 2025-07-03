package com.example.biblio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "condition_pret")
public class ConditionPret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_type_adherant")
    private TypeAdherant typeAdherant;

    @Column(name = "duree")
    private Integer duree;

    @Column(name = "limitation_age")
    private Integer limitationAge;

    @ManyToOne
    @JoinColumn(name = "id_livre", nullable = false)
    private Livre livre;

    @ManyToOne
    @JoinColumn(name = "id_type_pret", nullable = false)
    private TypePret typePret;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeAdherant getTypeAdherant() {
        return typeAdherant;
    }

    public void setTypeAdherant(TypeAdherant typeAdherant) {
        this.typeAdherant = typeAdherant;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public Integer getLimitationAge() {
        return limitationAge;
    }

    public void setLimitationAge(Integer limitationAge) {
        this.limitationAge = limitationAge;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public TypePret getTypePret() {
        return typePret;
    }

    public void setTypePret(TypePret typePret) {
        this.typePret = typePret;
    }

    // Getters & setters
}

