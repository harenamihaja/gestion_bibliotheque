package com.example.biblio.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "livre")
public class Livre {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String edition;
    private String resume;
    private LocalDate datePublication;

    @ManyToOne
    @JoinColumn(name = "id_auteur")
    private Auteur auteur;

    @OneToMany(mappedBy = "livre")
    private List<Exemplaire> exemplaires;

    @OneToMany(mappedBy = "livre")
    private List<CategorieLivre> categories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public LocalDate getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    public List<Exemplaire> getExemplaires() {
        return exemplaires;
    }

    public void setExemplaires(List<Exemplaire> exemplaires) {
        this.exemplaires = exemplaires;
    }

    public List<CategorieLivre> getCategories() {
        return categories;
    }

    public void setCategories(List<CategorieLivre> categories) {
        this.categories = categories;
    }
}