<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="mongoPojo.Federation"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des Fédérations (MongoDB)</title>
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
</style>
<body>
	<h1>Liste des Fédérations - MongoDB</h1>

	<table>
		<thead>
			<tr>
				<th>Nom</th>
				<th>Acronyme</th>
				<th>Adresse</th>
				<th>Disciplines</th>
				<th>Sportifs</th>
			</tr>
		</thead>
		<tbody>
			<%
			ArrayList<Federation> federations = (ArrayList<Federation>) request.getAttribute("federations");
			if (federations != null && !federations.isEmpty()) {
				for (Federation federation : federations) {
			%>

			<tr>
				<td><%=federation.getNom()%></td>
				<td><%=federation.getAcronyme()%></td>
				<td><%=federation.getAdresse()%></td>
				<td>
					<ul>
						<%
						if (federation.getDisciplines() != null) {
							for (Object d : federation.getDisciplines()) {
						%>
						<li><%=d.toString()%></li>
						<%
						}
						}
						%>
					</ul>
				</td>
				<td>
					<ul>
						<%
						if (federation.getSportifs() != null) {
							for (Object s : federation.getSportifs()) {
						%>
						<li><%=s.toString()%></li>
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
				<td colspan="6">Aucune fédération trouvée</td>
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
