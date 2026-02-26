<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="mongoPojo.Sportif"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des Sportifs (MongoDB)</title>
</head>
<style>
table, td, th {
  border: 10px solid green;
  border-collapse: collapse;
}

th {
	background-color : green;
	color: white;
}

a {
	padding:10px;
	background-color: black;
	color: white;
	text-decoration: none;
	border-radius: 10px;
}

td{
	padding: 10px;
}
</style>
<body>
	<h1>Liste des Sportifs - MongoDB</h1>

	<table border="1">
		<thead>
			<tr>
				<th>Nom</th>
				<th>Prénom</th>
				<th>Age</th>
				<th>Genre</th>
				<th>Adresse</th>
				<th>Disciplines</th>
			</tr>
		</thead>
		<tbody>
			<%
			ArrayList<Sportif> sportifs = (ArrayList<Sportif>) request.getAttribute("sportifs");
			if (sportifs != null && !sportifs.isEmpty()) {
				for (Sportif sportif : sportifs) {
			%>

			<tr>
				<td><%=sportif.getNom()%></td>
				<td><%=sportif.getPrenom()%></td>
				<td><%=sportif.getAge()%></td>
				<td><%=sportif.getGenre()%></td>
				<td><%=sportif.getAdresse()%></td>
				<td>
					<ul>
						<%
						if (sportif.getDisciplines() != null) {
							for (Object d : sportif.getDisciplines()) {						%>
						<li><%=d.toString()%></li>
						<%
						}
						}
						%>
					</ul>
				</td>
			</tr>

			<%
			} 
			} else {
			%>
			<tr>
				<td colspan="6">Aucun sportif trouvé</td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>

	<br>
	<a href="index.html">Retour au menu</a>
</body>
</html>
