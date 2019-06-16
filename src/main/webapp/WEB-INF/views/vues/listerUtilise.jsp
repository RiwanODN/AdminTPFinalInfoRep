<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>

<div class="jumbotron text-center">
	<h1>Listing des Utilises</h1>
</div>

<div class="container">
	<a class="btn btn-secondary" href="index.htm" role="button"><span class="glyphicon glyphicon-menu-left"></span> Retour accueil</a>
	<h2>Tableau des Utilises</h2>
	<div class="container">
		<h3>Liste des Utilises</h3>
		<table class="table table-hover">
			<tr>
				<th class="col-md-2">Vehicule</th>
				<th class="col-md-2">Client</th>
				<th class="col-md-4">Date</th>
				<th class="col-md-4">Borne de départ</th>
				<th class="col-md-4">Borne d'arrivée</th>
			</tr>

			<c:forEach items="${mesUtilises}" var="item">
				<tr>
					<td>${item.vehicule.getIdVehicule()}</td>
					<td>${item.client.getIdClient()}</td>
					<td>${item.getDate()} €</td>
					<td>${item.borneDepart.getIdBorne() }</td>
					<td>${item.borneArrivee.getIdBorne() }</td>
					<td><a class="btn btn-info" href="modifierUtilise.htm?id=${item.getDate()}" role="button"><span
							class="glyphicon glyphicon-pencil"></span> Modifier</a>
						<a class="btn btn-danger" href="supprimerUtilise.htm?id=${item.getDate()}" role="button"><span
								class="glyphicon glyphicon-remove-circle"></span> Supprimer</a></td>

				</tr>
			</c:forEach>
		</table>
	</div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>