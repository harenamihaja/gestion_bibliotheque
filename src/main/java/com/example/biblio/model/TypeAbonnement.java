package com.example.biblio.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Entity
@Table(name = "type_abonnement")
public class TypeAbonnement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private Integer nbMois;
    private Double prix;
}