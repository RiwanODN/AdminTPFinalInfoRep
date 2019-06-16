 <%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<H1> Modifier un client </H1>
<form method="post" action="insererClientModif.htm" onsubmit="return teste()">
<div class="col-md-12 well well-md">
    <h1>Modifier Séjour</h1>
    <div class="row" >
        <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-3 control-label">Nom du client : </label>
        <div class="col-md-3">
            <INPUT type="hidden" name="idClient" value=${item.idClient} id="idadh" class="form-control" min="0">
            <INPUT type="text" name="txtnom" value=${item.nom} id="nom" class="form-control" min="0">
        </div>

    </div>
    <div class="row" >
        <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-3 control-label">Prénom du client : </label>
        <div class="col-md-3">
            <INPUT type="text" name="txtprenom" value=${item.prenom} id="prenom" class="form-control" min="0">
        </div>
    </div>
    <div class="row" >
        <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
        </div>
    </div>


    <div class="form-group">
        <label class="col-md-3 control-label">Date de naissance : </label>
        <div class="col-md-3">
            <input type="date" id="dateNaiss" name="dDateNaiss"
                   value="<c:out value="${item.dateNaissance}" />"
                   max="<c:out value="${currentDate}" />">
        </div>
    </div>

    <div class="row" >
        <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
        </div>
    </div>

    <div class="form-group">
        <label class="col-md-3 control-label">Role du client : </label>
        <div class="col-md-3">
            <INPUT type="text" name="txtrole" value=${item.role} id="role" class="form-control" min="0">
        </div>
    </div>

    <div class="row" >
        <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
        </div>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-ok"></span>
            Ajouter
        </button>

        <button type="button" class="btn btn-default btn-primary"
                onclick="{
                            window.location = 'listerClient.htm';
                        }">
            <span class="glyphicon glyphicon-remove"></span> Annuler

        </button>
    </div>
</div>
</form>
</body>
<%@include file="footer.jsp"%>
</html>