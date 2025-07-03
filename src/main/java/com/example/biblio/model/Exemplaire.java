package com.example.biblio.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Entity
@Table(name = "exemplaire")
public class Exemplaire {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateAchat;

    @ManyToOne
    @JoinColumn(name = "id_livre")
    private Livre livre;
}