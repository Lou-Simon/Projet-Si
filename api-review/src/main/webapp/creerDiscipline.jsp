<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="donnees.Sport"%>
<%@ page import="sportsDAO.DAO_JPA"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Créer une discipline</title>
</head>
<body>
	<h2>Formulaire de création de discipline</h2>

	<form action="CreerDiscipline" method="post">
		<table>
			<tr>
				<td>Intitulé de la discipline :</td>
				<td><input type="text" name="intituleDiscipline" /></td>
			</tr>
			<tr>
				<td>Choisir le sport associé :</td>
				<td><select name="idSport">
						<%-- Utilisation du c:forEach sur l'attribut "lesSports" (Page 63) --%>
						<c:forEach items="${requestScope.sports}" var="s">
							<option value="${s.codeSport}">${s.intitule}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Valider" /></td>
			</tr>
		</table>
	</form>

	<p>
		<a href="index.html">Retour au menu</a>
	</p>
</body>
</html>