
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.biblio.model.Abonnement" %>

<html>
<head>
    <title>Abonnements en cours</title>
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
    </style>
</head>
<body>
    <h2>Liste des abonnements en cours</h2>
    <%
        List<Abonnement> abonnements = (List<Abonnement>) request.getAttribute("abonnements");
        if (abonnements != null && !abonnements.isEmpty()) {
    %>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom Adhérant</th>
                    <th>Type d'abonnement</th>
                    <th>Date de début</th>
                    <th>Date de fin</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Abonnement ab : abonnements) {
                %>
                    <tr>
                        <td><%= ab.getId() %></td>
                        <td><%= ab.getAdherant().getNom() %></td>
                        <td><%= ab.getDateDebut() %></td>
                        <td><%= ab.getDateFin() %></td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    <%
        } else {
    %>
        <p class="no-data">Aucun abonnement en cours trouvé.</p>
    <%
        }
    %>
</body>
</html>
```
