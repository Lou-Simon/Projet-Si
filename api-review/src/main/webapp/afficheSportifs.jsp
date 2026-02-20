<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sportifs et pratiques</title>
</head>
<body>
    <h3>Liste des sportifs et de ce qu'ils font</h3>
    
    <c:forEach items="${requestScope.sportifs}" var="sp">
        <h4>${sp.nom}</h4>
        <p>
            <b>Adresse : </b>${sp.rue} - ${sp.codePostal} ${sp.ville}
        </p>
        
        <c:choose>
            <c:when test="${empty sp.disciplines}">
                <p><i>Ne pratique aucune discipline sportive</i></p>
            </c:when>
            <c:otherwise>
                <p><b>Liste des disciplines pratiquées : </b></p>
                <ul>
                    <c:forEach items="${sp.disciplines}" var="disc">
                        <li>${disc.intitule} (du sport ${disc.sport.intitule})</li>
                    </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</body>
</html>