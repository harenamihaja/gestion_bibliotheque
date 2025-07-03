package com.example.biblio.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Entity
@Table(name = "categorie")
public class Categorie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;

    @OneToMany(mappedBy = "categorie")
    private List<CategorieLivre> livres;
}