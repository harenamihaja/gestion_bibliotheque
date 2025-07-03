DROP DATABASE IF EXISTS spring_initiation;
CREATE DATABASE spring_initiation;
USE spring_initiation;
CREATE TABLE Employe(
    id_emp SERIAL PRIMARY KEY,
    id_dept INTEGER,
    nom_emp VARCHAR(255),
    num_emp VARCHAR(255),
    birth DATE
);

\c postgres;
DROP DATABASE IF EXISTS spring_initiation;
CREATE DATABASE spring_initiation;
\c spring_initiation;
CREATE TABLE Department (
    id SERIAL,
    nom VARCHAR(255),
    PRIMARY KEY (id)
);

create table Film(
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255),
    date_sortie DATE
);
create table Categorie(
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255)
);

CREATE TABLE Film_Categorie (
    id SERIAL PRIMARY KEY,
    id_film INTEGER,
    id_categorie INTEGER,
    FOREIGN KEY (id_film) REFERENCES Film(id),
    FOREIGN KEY (id_categorie) REFERENCES Categorie(id)
);

insert into Film(nom,date_sortie)Values('power rangers','2025-12-01');
insert into Film(nom,date_sortie)Values('ohm','2025-12-01');

insert into Categorie(nom)Values('romance');
insert into Categorie(nom)Values('thriller');



CREATE VIEW as SELECT Film.nom,  FROM Film_Categorie
join Film 
on Film.id = Film_Categorie.id_film
join Categorie
on Categorie.id = Film_Categorie.id_categorie;