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
    $(document).ready(function () {
        var table = $('#tableVehicules').DataTable( {
            select: true
        });

        table.on( 'select', function ( e, dt, type, indexes ) {
            var count = table.rows( { selected: true } ).count();
                if(count == 1) {
                    $('#btnModifier').prop("disabled", false);
                    $('#btnReserver').prop("disabled", false);
                }
                else {
                    $('#btnModifier').prop("disabled", true);
                    $('#btnReserver').prop("disabled", true);
                }
                $('#btnSupprimer').prop("disabled", false);
        } );

        table.on( 'deselect', function ( e, dt, type, indexes ) {
            var count = table.rows( { selected: true } ).count();
            if(count == 0) {
                $('#btnSupprimer').prop("disabled", true);
                $('#btnModifier').prop("disabled", true);
                $('#btnReserver').prop("disabled", true);
            }
        } );

        $('#btnValiderSupprimer').click(function () {
            var ids = $.map(table.rows( { selected: true } ).data(), function (item) {
                return item[0];
            });
            window.location = 'supprimerVehicule.htm?id='+ids;
        });

        $('#btnModifier').click(function () {
            var row = table.rows( { selected: true } ).data()[0];
            //console.log(row[0]);
            window.location = 'modifierVehicule.htm?id='+row[0];
        });

        $('#btnReserver').click(function () {
            var row = table.rows( { selected: true } ).data()[0];
            window.location = 'reserverVehicule.htm?id='+row[0];
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
            <button disabled id="btnModifier"type="button" class="btn btn-default btn-lg">Modifier</button>
            <button disabled id="btnSupprimer" type="button" class="btn btn-default btn-lg" data-toggle="modal" data-target="#supprimerModal">Supprimer</button>
            <button disabled id="btnReserver"type="button" class="btn btn-default btn-lg">Reserver</button>
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