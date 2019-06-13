<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/select/1.3.0/css/select.bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/select/1.3.0/js/dataTables.select.min.js"></script>

<script>
    /*$(document).ready(function() {
        var events = $('#events');
        var tableVeh = $('#tableVehicules').DataTable( {
            dom: 'Bfrtip',
            select: {
                style: 'os',
                blurable: true
            }
        } );
    } );*/

    $(document).ready(function () {
        var table = $('#tableVehicules').DataTable( {
            select: true
        });

        $('#tableVehicules tbody').on('click', 'tr', function () {
            $(this).toggleClass('selected');
            if(table.rows('.selected').data().length == 0) {
                //console.log("is hidden")
				$('#btnSupprimer').prop("disabled", true);
            }
            else {
                $('#btnSupprimer').prop("disabled", false);
            }
        });

        $('#btnValiderSupprimer').click(function () {
            var ids = $.map(table.rows('.selected').data(), function (item) {
                return item[0];
            });
            window.location = 'supprimerVehicule.htm?id='+ids;
            //console.log(ids)
            //alert(table.rows('.selected').data().length + ' row(s) selected');
        });
		
    });
</script>

<body>
<%@include file="navigation.jsp"%>
<div class="jumbotron text-center">
    <h1>Vehicules</h1>
</div>

<div class="container">
    <a class="btn btn-secondary" href="index.htm" role="button"><span class="glyphicon glyphicon-menu-left"></span> Retour accueil</a>
    <h2>Tableau des Vehicules</h2>
    <div class="container">
        <div class="btn-group" role="group">
            <button id="btnAjouter"type="button" class="btn btn-default btn-lg" onclick="{window.location = 'ajouterVehicule.htm';}">Ajouter</button>
            <button disabled id="btnSupprimer" type="button" class="btn btn-default btn-lg" data-toggle="modal" data-target="#supprimerModal">Supprimer</button>
        </div>

        <h3>Liste des Vehicules</h3>
        <table id="tableVehicules" class="table table-striped table-bordered" style="width:100%">
            <thead>
            <tr>
                <th hidden>Id</th>
                <th>Reference Vehicule</th>
                <th>Type de Vehicule</th>
                <th>Categorie</th>
                <th>Etat de la Batterie</th>
                <th>Disponibilite</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${map}" var="item">
                <tr>
                    <td hidden>${item.key.idVehicule}</td>
                    <td>${item.key.rfid}</td>
                    <td>${item.value.typeVehicule}</td>
                    <td>${item.value.categorie}</td>
                    <td>${item.key.etatBatterie}</td>
                    <td>${item.key.disponibilite}</td>
                </tr>
            </c:forEach>
            </tbody>

            <tfoot>
                <tr>
                    <th hidden>Id</th>
                    <th>Reference Vehicule</th>
                    <th>Type de Vehicule</th>
                    <th>Categorie</th>
                    <th>Etat de la Batterie</th>
                    <th>Disponibilite</th>
                </tr>
            </tfoot>

        </table>

        <div class="modal fade" id="supprimerModal" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Supprimer Vehicule</h4>
                    </div>
                    <div class="modal-body">
                        <p>Etes-vous sur de vouloir supprimer le(s) vehicule(s) selectionnee(s) ?</p>
                    </div>
                    <div class="modal-footer">
                        <button id="btnValiderSupprimer" type="submit" class="btn btn-success btn-default pull-right">Supprimer</button>
                        <button type="submit" class="btn btn-danger btn-default pull-left" data-dismiss="modal">Annuler</button>
                    </div>
                </div>

            </div>
        </div>


    </div>
</div>
<%@include file="footer.jsp"%>
</body>

</html>