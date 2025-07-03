package com.example.biblio.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Entity
@Table(name = "type_pret")
public class TypePret {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
}