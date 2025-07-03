package com.example.biblio.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Entity
@Table(name = "categorie_livre")
public class CategorieLivre {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_livre")
    private Livre livre;

    @ManyToOne
    @JoinColumn(name = "id_categorie")
    private Categorie categorie;
}