-- Création de la base
CREATE DATABASE gestion_bibliotheque;
\c gestion_bibliotheque

-- Table auteur
CREATE TABLE auteur (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL
);

-- Table livre
CREATE TABLE livre (
    id SERIAL PRIMARY KEY,
    titre VARCHAR(100) NOT NULL,
    edition VARCHAR(50),
    resume TEXT,
    id_auteur INT REFERENCES auteur(id) ON DELETE SET NULL,
    date_publication DATE
);

-- Table categorie
CREATE TABLE categorie (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL
);

-- Association livre <-> categorie
CREATE TABLE categorie_livre (
    id SERIAL PRIMARY KEY,
    id_livre INT REFERENCES livre(id) ON DELETE CASCADE,
    id_categorie INT REFERENCES categorie(id) ON DELETE CASCADE
);

-- Table exemplaire
CREATE TABLE exemplaire (
    id SERIAL PRIMARY KEY,
    id_livre INT REFERENCES livre(id) ON DELETE CASCADE,
    date_achat DATE
);

-- Table type_adherant
CREATE TABLE type_adherant (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(50) NOT NULL
);
<<<<<<< Updated upstream
=======
alter table type_adherant add column delai int;
 alter table type_adherant add column capacite int;


>>>>>>> Stashed changes

-- Table adherant
CREATE TABLE adherant (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    id_type_adherant INT REFERENCES type_adherant(id) ON DELETE SET NULL,
    date_naissance DATE,
    adresse TEXT
);

-- Table type_abonnement
CREATE TABLE type_abonnement (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    nb_mois INT,
    prix DECIMAL(10,2)
);

-- Table abonnement
CREATE TABLE abonnement (
    id SERIAL PRIMARY KEY,
    id_adherant INT REFERENCES adherant(id) ON DELETE CASCADE,
    date_debut DATE,
    date_fin DATE,
    id_type_abonnement INT REFERENCES type_abonnement(id) ON DELETE SET NULL
);

-- Table type_pret
CREATE TABLE type_pret (
    id SERIAL PRIMARY KEY,
    type VARCHAR(50) NOT NULL
);

-- Table pret
CREATE TABLE pret (
    id SERIAL PRIMARY KEY,
    id_adherant INT REFERENCES adherant(id) ON DELETE CASCADE,
    id_exemplaire INT REFERENCES exemplaire(id) ON DELETE CASCADE,
    id_type_pret INT REFERENCES type_pret(id) ON DELETE CASCADE
);

-- Table type_statut_pret
CREATE TABLE type_statut_pret (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(50)
);

-- Table statut_pret
CREATE TABLE statut_pret (
    id SERIAL PRIMARY KEY,
    id_type_statut INT REFERENCES type_statut_pret(id) ON DELETE CASCADE,
    date_debut TIMESTAMP,
    date_fin TIMESTAMP,
    id_pret INT REFERENCES pret(id) ON DELETE CASCADE
);

-- Table condition_pret
CREATE TABLE condition_pret (
    id SERIAL PRIMARY KEY,
    id_type_adherant INT REFERENCES type_adherant(id) ON DELETE SET NULL,
    duree INT,
    limitation_age INT,
    id_livre INT REFERENCES livre(id) ON DELETE CASCADE,
    id_type_pret INT REFERENCES type_pret(id) ON DELETE CASCADE
);
