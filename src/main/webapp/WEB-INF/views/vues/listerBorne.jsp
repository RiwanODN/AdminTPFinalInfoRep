<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>

<div class="jumbotron text-center">
	<h1>Listing des Bornes</h1>
</div>

<div class="container">
	<a class="btn btn-secondary" href="../index.htm" role="button"><span class="glyphicon glyphicon-menu-left"></span> Retour accueil</a>
	<h2>Tableau des Bornes</h2>
	<div class="container">
		<h3>Liste des Bornes</h3>
		<table class="table table-hover">
			<tr>
				<th class="col-md-2">Identifiant</th>
				<th class="col-md-2">Etat</th>
				<th class="col-md-4">Adresse</th>
				<th class="col-md-4">Ville</th>
				<th class="col-md-4">ID Vehicule</th>



			</tr>

			<c:forEach items="${mesBornes}" var="item">
				<tr>
					<td>${item.idBorne}</td>
					<td>${item.etatBorne}</td>
					<td>${item.station.numero} ${item.station.adresse}</td>
					<td>${item.station.ville}</td>
					<td>${item.vehicule.getIdVehicule()}</td>
					<td><a class="btn btn-info" href="modifierBorne.htm?id=${item.idBorne}" role="button"><span
							class="glyphicon glyphicon-pencil"></span> Modifier</a>
						<a class="btn btn-danger" href="supprimerBorne.htm?id=${item.idBorne}" role="button"><span
								class="glyphicon glyphicon-remove-circle"></span> Supprimer</a></td>

				</tr>
			</c:forEach>
		</table>
	</div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>