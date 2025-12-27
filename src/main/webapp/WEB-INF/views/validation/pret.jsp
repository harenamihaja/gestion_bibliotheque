<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.biblio.dto.ReservationEnAttenteDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty success}">
    <script>alert('${success}');</script>
</c:if>
<c:if test="${not empty error}">
    <script>alert('${error}');</script>
</c:if>

<html>
<head><title>Réservations en attente</title></head>
<body>
    <h2>Réservations en attente</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID Prêt</th>
                <th>Nom Adhérant</th>
                <th>ID Exemplaire</th>
                <th>Titre Livre</th>
                <th>Date début prévision</th>
                <th>Date début statut</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<ReservationEnAttenteDTO> reservations = (List<ReservationEnAttenteDTO>) request.getAttribute("reservations");
                for (ReservationEnAttenteDTO res : reservations) {
            %>
                <tr>
                    <td><%= res.getIdPret() %></td>
                    <td><%= res.getNomAdherant() %></td>
                    <td><%= res.getIdExemplaire() %></td>
                    <td><%= res.getTitreLivre() %></td>
                    <td><%= res.getDateDebutPrevision() != null ? res.getDateDebutPrevision() : "N/A" %></td>
                    <td><%= res.getDateDebutStatut() %></td>
                    <td>
                        <a href="/pret/valider/<%= res.getIdPret() %>"  >
                            <button>Valider</button>
                        </a>
                        <a href="/pret/rejeter/<%= res.getIdPret() %>">
                            <button>Rejeter</button>
                        </a>
                    </td>
                    

                </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
