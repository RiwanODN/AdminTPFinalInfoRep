<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
	<div class="jumbotron text-center">
		<h1>Listing des clients</h1>
	</div>

	<div class="container">
		<a class="btn btn-secondary" href="index.htm" role="button"><span class="glyphicon glyphicon-menu-left"></span> Retour accueil</a>
		<h2>Tableau des clients</h2>
		<div class="container">
			<h3>Liste des clients</h3>
			<table class="table table-hover">
				<tr>
					<th class="col-md-1">Numero</th>
					<th class="col-md-2">Nom</th>
					<th class="col-md-2">Prénom</th>
					<th class="col-md-4">Date de naissance</th>

				</tr>

				<c:forEach items="${mesClients}" var="item">
					<tr>
						<td>${item.idClient}</td>
						<td>${item.nom}</td>
						<td>${item.prenom}</td>
						<td>${item.dateNaissance}</td>
						<td><a class="btn btn-info" href="modifierClient.htm?id=${item.idClient}" role="button"><span
								class="glyphicon glyphicon-pencil"></span> Modifier</a>
							<a class="btn btn-danger" href="supprimerClient.htm?id=${item.idClient}" role="button"><span
									class="glyphicon glyphicon-remove-circle"></span> Supprimer</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
    </div>
<%@include file="footer.jsp"%>
</body>

</html>