<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.biblio.model.Exemplaire" %>

<html>
<head>
    <title>Exemplaires disponibles</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        h2 {
            color: #333;
        }
        .no-data {
            color: #888;
            font-style: italic;
        }
        .popup {
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: white;
            padding: 20px;
            border: 1px solid #ccc;
            box-shadow: 0 4px 8px rgba(0,0,0,0.2);
            z-index: 1000;
            width: 400px;
        }
        .popup-content {
            margin-bottom: 20px;
        }
        .popup-overlay {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0,0,0,0.5);
            z-index: 999;
        }
        .popup button, .popup input[type="submit"] {
            padding: 8px 16px;
            margin-right: 10px;
        }
        .popup input[type="date"] {
            padding: 8px;
            margin: 10px 0;
            width: 100%;
        }
    </style>
</head>
<%
    Boolean success = (Boolean) request.getAttribute("reservationSuccess");
    if (success != null && success) {
%>
    <div id="successModal" class="popup" style="display: block;">
        <div class="popup-content">
            <h3>Succès</h3>
            <p>Réservation en attente de validation.</p>
            <button onclick="closeSuccessModal()">OK</button>
        </div>
    </div>
    <div class="popup-overlay" id="successOverlay" style="display: block;"></div>
<%
    }
%>

<body>
    <h2>Liste des exemplaires disponibles</h2>
    <div id="popupOverlay" class="popup-overlay"></div>
    <div id="popup" class="popup">
        <div class="popup-content">
            <h3>Détails de l'exemplaire</h3>
            <p><strong>ID Exemplaire:</strong> <span id="popupId"></span></p>
            <p><strong>Titre:</strong> <span id="popupTitre"></span></p>
            <p><strong>Auteur:</strong> <span id="popupAuteur"></span></p>
            <p><strong>Édition:</strong> <span id="popupEdition"></span></p>
            <p><strong>Date de publication:</strong> <span id="popupDatePublication"></span></p>
            <p><strong>Prochaine date de prêt (En cours):</strong> <span id="popupDateDebutPrevision"></span></p>
            <form id="reservationForm" action="/pret/reservation" method="post">
                <label for="reservationDate">Date de réservation:</label>
                <input type="date" id="reservationDate" name="reservationDate" required>
                <input type="hidden" id="exemplaireId" name="exemplaireId">
                <br>
                <input type="submit" value="Valider">
                <button type="button" onclick="closePopup()">Annuler</button>
            </form>
        </div>
    </div>
    <%
        List<Exemplaire> exemplaires = (List<Exemplaire>) request.getAttribute("exemplaires");
        if (exemplaires != null && !exemplaires.isEmpty()) {
    %>
        <table>
            <thead>
                <tr>
                    <th>ID Exemplaire</th>
                    <th>Titre</th>
                    <th>Auteur</th>
                    <th>Édition</th>
                    <th>Date de publication</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Exemplaire exemplaire : exemplaires) {
                %>
                    <tr>
                        <td><%= exemplaire.getId() %></td>
                        <td><%= exemplaire.getLivre() != null ? exemplaire.getLivre().getTitre() : "N/A" %></td>
                        <td><%= exemplaire.getLivre() != null && exemplaire.getLivre().getAuteur() != null ? exemplaire.getLivre().getAuteur().getNom() : "Inconnu" %></td>
                        <td><%= exemplaire.getLivre() != null && exemplaire.getLivre().getEdition() != null ? exemplaire.getLivre().getEdition() : "N/A" %></td>
                        <td><%= exemplaire.getLivre() != null && exemplaire.getLivre().getDatePublication() != null ? exemplaire.getLivre().getDatePublication() : "N/A" %></td>
                        <td>
                            <button onclick="showPopup(
                                '<%= exemplaire.getId() %>',
                                '<%= exemplaire.getLivre() != null ? exemplaire.getLivre().getTitre().replace("'", "\\'") : "N/A" %>',
                                '<%= exemplaire.getLivre() != null && exemplaire.getLivre().getAuteur() != null ? exemplaire.getLivre().getAuteur().getNom().replace("'", "\\'") : "Inconnu" %>',
                                '<%= exemplaire.getLivre() != null && exemplaire.getLivre().getEdition() != null ? exemplaire.getLivre().getEdition().replace("'", "\\'") : "N/A" %>',
                                '<%= exemplaire.getLivre() != null && exemplaire.getLivre().getDatePublication() != null ? exemplaire.getLivre().getDatePublication() : "N/A" %>'
                            )">Réserver</button>
                        </td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    <%
        } else {
    %>
        <p class="no-data">Aucun exemplaire disponible trouvé.</p>
    <%
        }
    %>
    <script>
        function showPopup(id, titre, auteur, edition, datePublication) {
            // Populate popup fields
            document.getElementById('popupId').textContent = id;
            document.getElementById('popupTitre').textContent = titre;
            document.getElementById('popupAuteur').textContent = auteur;
            document.getElementById('popupEdition').textContent = edition;
            document.getElementById('popupDatePublication').textContent = datePublication;
            document.getElementById('exemplaireId').value = id;
            document.getElementById('popupDateDebutPrevision').textContent = 'Chargement...';

            // Fetch date_debut_prevision via AJAX
            fetch('/exemplaires/date-debut-prevision/' + id, {
                method: 'GET',
               
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erreur réseau');
                }
                return response.json();
            })
            .then(data => {
                document.getElementById('popupDateDebutPrevision').textContent = data.dateDebutPrevision || 'Non disponible';
            })
            .catch(error => {
                console.error('Erreur:', error);
                document.getElementById('popupDateDebutPrevision').textContent = 'Erreur de chargement';
            });

            // Show popup and overlay
            document.getElementById('popup').style.display = 'block';
            document.getElementById('popupOverlay').style.display = 'block';
        }

        function closePopup() {
            document.getElementById('popup').style.display = 'none';
            document.getElementById('popupOverlay').style.display = 'none';
        }
    </script>
    <script>
        function closeSuccessModal() {
            document.getElementById('successModal').style.display = 'none';
            document.getElementById('successOverlay').style.display = 'none';
        }
    </script>
</body>
</html>

