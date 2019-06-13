<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<style>
	.loader {
		border: 5px solid #f3f3f3; /* Light grey */
		border-top: 5px solid black; /* Blue */
		border-radius: 50%;
		width: 25px;
		height: 25px;
		animation: spin 2s linear infinite;
	}

	@keyframes spin {
		0% { transform: rotate(0deg); }
		100% { transform: rotate(360deg); }
	}
</style>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<link rel="stylesheet" href="https://unpkg.com/leaflet@1.3.1/dist/leaflet.css" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

	<script type="text/javascript" src="https://unpkg.com/leaflet@1.3.1/dist/leaflet.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>

</html>
<body onload="test();">
<%@include file="navigation.jsp"%>
<h1>Carte des Stations</h1>
	<div class="container">
		<div class="container">
			<div class="row">

				<div id="map" style="width:80%; height:80%"></div>
				<div class="btn-group" role="group">
					<button type="button" class="btn btn-default btn-lg" data-toggle="modal" data-target="#ajouterModal">Ajouter</button>
				</div>

                <div class="modal fade" id="ajouterModal" role="dialog">
					<div class="modal-dialog">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">Ajouter Station</h4>
							</div>
							<div class="modal-body">
								<form>

									<div class="form-group"> <!-- Adresse field -->
										<label class="control-label " for="adresse">Adresse</label>
										<input class="form-control" id="adresse" name="adresse" type="text"/>
									</div>

									<div class="form-group"> <!-- Rue field -->
										<label class="control-label" for="numero">Numero</label>
										<input class="form-control" id="numero" name="numero" type="text"/>
									</div>

									<div class="form-group"> <!-- Ville field -->
										<label class="control-label " for="ville">Ville</label>
										<input class="form-control" id="ville" name="ville" type="text"/>
									</div>

									<div class="form-group"> <!-- Code Postal field -->
										<label class="control-label " for="codePostal">Code Postal</label>
										<input class="form-control" id="codePostal" name="codePostal" type="text"/>
									</div>

								</form>

							</div>
							<div class="modal-footer">
								<button id="btnAjouter" type="submit" class="btn btn-success btn-default pull-right" onclick="chercher()">Ajouter</button>
								<button type="submit" class="btn btn-danger btn-default pull-left" data-dismiss="modal">Annuler</button>
							</div>
						</div>

					</div>
				</div>

                <div class="modal fade" id="supprimerModal" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title">Supprimer Station</h4>
                            </div>
                            <div class="modal-body">
                                <p>Etes-vous sur de vouloir supprimer la station ?</p>
                            </div>
                            <div class="modal-footer">
                                <button id="btnValiderSupprimer" type="submit" onclick="supprimer()" class="btn btn-success btn-default pull-right">Supprimer</button>
                                <button type="submit" class="btn btn-danger btn-default pull-left" data-dismiss="modal">Annuler</button>
                            </div>
                        </div>

                    </div>
                </div>

                </div>

			</div>
		</div>
    </div>
<%@include file="footer.jsp"%>
</body>
</html>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script>

    var globalId;

	function test() {

		var map = L.map('map', {
			center: [45.750000, 4.84],
			zoom: 13
		});
		var osmLayer = L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
			attribution: '© OpenStreetMap contributors',
			maxZoom: 19
		});
		map.addLayer(osmLayer);
		/*
		var LeafIcon = L.Icon.extend({
			options: {
				iconSize:     [30, 30],
				iconAnchor:   [22, 94],
				popupAnchor:  [-3, -76]
			}
		});
		var testIcon = new LeafIcon({iconUrl: './../../resources/images/popup.png'});
        */
		jQuery.ajax({
			type: 'POST',
			url: 'afficherStation.htm',
			success: function (result) {
				result.forEach(function (value) {
					var marker = L.marker([value.latitude, value.longitude]
                        //, {icon: testIcon}
                        ).addTo(map)
							.bindPopup(
							    '<div>' +
                                    '<p>'+value.numero+', '+value.adresse+'</p>' +
                                    '<p>'+value.codePostal+', '+value.ville+'</p>' +
                                    '<button id="btnSupprimer" type="button" class="btn btn-default btn-sm" data-toggle="modal" ' +
                                'data-target="#supprimerModal" onclick="setId('+value.idStation+')">Supprimer</button>'+
                                '</div>');
				});
			}
		});
	};
	
	function chercher() {
		var num=$("#numero").val();
		var adr=$("#adresse").val();
		var ville=$("#ville").val();
		var cp=$("#codePostal").val();
		var str = num + "+" + adr + "+" + ville+ "+" + cp;
			$.ajax({
				url: "https://nominatim.openstreetmap.org/search", // URL de Nominatim
				type: 'get', // Requête de type GET
				data: "q="+str+"&format=json&addressdetails=1&limit=1&polygon_svg=1" // Données envoyées (q -> adresse complète, format -> format attendu pour la réponse, limit -> nombre de réponses attendu, polygon_svg -> fournit les données de polygone de la réponse en svg)
			}).done(function (response) {
				if(response != ""){
					userlat = response[0]['lat'];
					userlon = response[0]['lon'];
					console.log("lon"+userlon+"lat"+userlat+"ville"+ville);
					window.location = 'ajouterStation.htm?lon='+userlon+'&lat='+userlat+'&num='+num+'&adr='+adr+'&ville='+ville+'&cp='+cp;
				}
				else {
					alert("Adresse Introuvable!");
				}
			}).fail(function (error) {
				alert(error);
				console.log(error);
			});
	}

	function setId(elmtid) {
        globalId = elmtid;
    }

    function supprimer() {
        window.location = 'supprimerStation.htm?id='+globalId;
    }
	
</script>
