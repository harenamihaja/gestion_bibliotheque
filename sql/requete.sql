----exemplaire non disponible en cours
SELECT DISTINCT pret.id_exemplaire
FROM pret 
JOIN statut_pret ON pret.id = statut_pret.id_pret
JOIN type_statut_pret ON type_statut_pret.id = statut_pret.id_type_statut
WHERE 
    type_statut_pret.nom IN ('En cours', 'Retarde')
    AND CURRENT_DATE BETWEEN statut_pret.date_debut AND COALESCE(statut_pret.date_fin, CURRENT_DATE + INTERVAL '100 years');


-----exemplaire dispo
SELECT DISTINCT l.*
FROM livre l
JOIN exemplaire e ON e.id_livre = l.id
WHERE e.id NOT IN (
    SELECT pret.id_exemplaire
    FROM pret 
    JOIN statut_pret ON pret.id = statut_pret.id_pret
    JOIN type_statut_pret ON type_statut_pret.id = statut_pret.id_type_statut
    WHERE 
        type_statut_pret.nom IN ('En cours', 'Retarde')
        AND CURRENT_DATE BETWEEN statut_pret.date_debut AND COALESCE(statut_pret.date_fin, CURRENT_DATE + INTERVAL '100 years')
);


--------un id_exemplaire est dispo entre deux dates
SELECT COUNT(*) = 0 AS est_disponible
FROM pret
JOIN statut_pret ON pret.id = statut_pret.id_pret
JOIN type_statut_pret ON statut_pret.id_type_statut = type_statut_pret.id
WHERE pret.id_exemplaire = :id_exemplaire
  AND type_statut_pret.nom IN ('En cours', 'Retarde')
  AND (
        statut_pret.date_debut <= :date_fin
    AND statut_pret.date_fin >= :date_debut
  );

  
------------exemplaires dispo entre 2 dates
SELECT e.*
FROM exemplaire e
WHERE e.id NOT IN (
    SELECT pret.id_exemplaire
    FROM pret
    JOIN statut_pret ON pret.id = statut_pret.id_pret
    JOIN type_statut_pret ON statut_pret.id_type_statut = type_statut_pret.id
    WHERE type_statut_pret.nom IN ('En cours', 'Retarde')
      AND (
            statut_pret.date_debut <= :date_fin
        AND statut_pret.date_fin >= :date_debut
      )
);

-------assemblage details des prets
SELECT 
    p.id AS id_pret,
    p.id_adherant,
    p.id_exemplaire,
    p.id_type_pret,
    p.date_debut_prevision,
    sp.id_type_statut,
    sp.date_debut,
    sp.date_fin,
    sp.id_pret AS statut_id_pret,
    tsp.nom AS statut_nom
FROM pret p
LEFT JOIN statut_pret sp ON p.id = sp.id_pret
LEFT JOIN type_statut_pret tsp ON sp.id_type_statut = tsp.id
ORDER BY p.id;

-------
SELECT 
    p.id AS id_pret,
    p.id_adherant,
    p.id_exemplaire,
    p.id_type_pret,
    p.date_debut_prevision,
    sp.id_type_statut,
    sp.date_debut,
    sp.date_fin,
    sp.id_pret AS statut_id_pret,
    tsp.nom AS statut_nom
FROM pret p
LEFT JOIN statut_pret sp ON p.id = sp.id_pret
LEFT JOIN type_statut_pret tsp ON sp.id_type_statut = tsp.id
WHERE p.id_exemplaire = :exemplaireId
  AND tsp.nom = 'En cours'
  AND p.date_debut_prevision > CURRENT_DATE
ORDER BY p.date_debut_prevision ASC
LIMIT 1;
