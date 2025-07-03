CREATE OR REPLACE VIEW abonnements_en_cours AS
SELECT 
    ab.id AS abonnement_id,
    ab.date_debut,
    ab.date_fin,
    ad.nom AS nom_adherant,
    ta.nom AS type_abonnement
FROM abonnement ab
JOIN adherant ad ON ab.id_adherant = ad.id
JOIN type_abonnement ta ON ab.id_type_abonnement = ta.id
WHERE CURRENT_DATE BETWEEN ab.date_debut AND ab.date_fin;
