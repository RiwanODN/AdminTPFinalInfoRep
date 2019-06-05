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
    $(document).ready(function() {
        $('#tableVehicules').DataTable( {
            select: {
                style: 'os',
                blurable: true
            }
        } );
    } );
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
        <h3>Liste des Vehicules</h3>
        <table id="tableVehicules" class="table table-striped table-bordered" style="width:100%">
            <thead>
            <tr>
                <th>Reference Vehicule</th>
                <th>Type de Vehicule</th>
                <th>Categorie</th>
                <th>Etat</th>
                <th>Disponibilite</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${map}" var="item">
                <tr>
                    <td>${item.key.idVehicule}</td>
                    <td>${item.value.typeVehicule}</td>
                    <td>${item.value.categorie}</td>
                    <td>${item.key.etatBatterie}</td>
                    <td>${item.key.disponibilite}</td>
                </tr>
            </c:forEach>
            </tbody>

            <tfoot>
                <tr>
                    <th>Ref.</th>
                    <th>Type de Vehicule</th>
                    <th>Categorie</th>
                    <th>Etat</th>
                    <th>Disponibilite</th>
                </tr>
            </tfoot>

        </table>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>

</html>