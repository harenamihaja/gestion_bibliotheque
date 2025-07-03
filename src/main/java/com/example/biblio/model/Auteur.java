package com.example.biblio.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Entity
@Table(name = "auteur")
public class Auteur {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @OneToMany(mappedBy = "auteur")
    private List<Livre> livres;
}