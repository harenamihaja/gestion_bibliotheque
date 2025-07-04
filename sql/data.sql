-- Auteurs
INSERT INTO auteur (nom) VALUES
('Jules Verne'), ('George Orwell'), ('Jane Austen'), ('Mark Twain');

-- Categories
INSERT INTO categorie (nom) VALUES
('Science Fiction'), ('Roman'), ('Histoire'), ('Aventure');

-- Livres
INSERT INTO livre (titre, edition, resume, id_auteur, date_publication) VALUES
('Voyage au centre de la Terre', 'Hetzel', 'Exploration sous la surface terrestre.', 1, '1864-11-25'),
('1984', 'Secker and Warburg', 'Regime totalitaire dystopique.', 2, '1949-06-08'),
('Orgueil et Prejuges', 'Whitehall', 'Amour et statut social.', 3, '1813-01-28'),
('Les Aventures de Tom Sawyer', 'American Publishing', 'Jeunesse dans le Sud des Etats-Unis.', 4, '1876-06-01');

-- Association livre <-> categorie
INSERT INTO categorie_livre (id_livre, id_categorie) VALUES
(1, 1), (2, 1), (2, 2), (3, 2), (4, 4);

-- Exemplaires
INSERT INTO exemplaire (id_livre, date_achat) VALUES
(1, '2022-01-15'), (2, '2022-02-10'), (3, '2023-03-05'), (4, '2023-05-20');

-- Types adherants
INSERT INTO type_adherant (nom) VALUES
('Etudiant'), ('Enseignant'), ('Externe');

update type_adherant set delai = 10 where id =1;
update type_adherant set delai = 14 where id =2;
update type_adherant set delai = 7 where id =3;

update type_adherant set capacite = 5 where id =1;
update type_adherant set capacite = 7 where id =2;
update type_adherant set capacite = 1 where id =3;


-- Adherants
INSERT INTO adherant (nom, id_type_adherant, date_naissance, adresse) VALUES
('Martin Dupont', 1, '2000-06-14', '123 rue Alpha'),
('Claire Bernard', 2, '1985-02-20', '456 rue Beta'),
('Louis Noel', 3, '1975-09-30', '789 rue Gamma');

-- Types abonnement
INSERT INTO type_abonnement (nom, nb_mois, prix) VALUES
('Standard', 12, 30.00),
('Etudiant', 6, 15.00),
('Premium', 24, 50.00);

-- Abonnements
INSERT INTO abonnement (id_adherant, date_debut, date_fin, id_type_abonnement) VALUES
(1, '2024-01-01', '2024-07-01', 2),
(2, '2024-03-01', '2025-03-01', 1),
(3, '2023-09-01', '2025-09-01', 3);

-- Types de pret
INSERT INTO type_pret (type) VALUES
('Court terme'), ('Long terme');

-- Prets
INSERT INTO pret (id_adherant, id_exemplaire, id_type_pret) VALUES
(1, 1, 1), (2, 2, 2), (3, 3, 2);

-- Type statut pret
INSERT INTO type_statut_pret (nom) VALUES
('En cours'),('En attente'), ('Retarde'),('Rejette'), ('Rendu');

-- Statuts pret
INSERT INTO statut_pret (id_type_statut, date_debut, date_fin, id_pret) VALUES
(1, '2025-06-01 10:00:00', NULL, 1),
(3, '2025-04-01 10:00:00', '2025-04-20 10:00:00', 2),
(2, '2025-05-10 10:00:00', NULL, 3);

-- Conditions de pret
INSERT INTO condition_pret (id_type_adherant, duree, limitation_age, id_livre, id_type_pret) VALUES
(1, 14, 18, 1, 1),
(2, 30, 0, 2, 2),
(3, 7, 21, 4, 1);
