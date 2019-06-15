<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<H1> Ajout d'une Utilise </H1>
<form method="post" action="insererUtilise.htm" onsubmit="return teste()">
    <div class="col-md-12 well well-md">
        <h1>Ajouter Utilise</h1>
        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>

        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>


        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-3 control-label">Client : </label>
            <div class="col-md-3">
                <SELECT name="Client" id="Client" class="form-control" min="0">
                    <c:forEach items="${Clients}" var="item">
                        <c:choose>
                            <c:when test="${Utilise.Client.idClient== item.idClient}"><OPTION value="${item.idClient}" selected="selected">${item.idClient}</OPTION></c:when>
                            <c:otherwise><OPTION value="${item.idClient}">${item.idClient}</OPTION></c:otherwise>
                        </c:choose>
                    </c:forEach>
                </SELECT>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-3 control-label">Vehicule : </label>
            <div class="col-md-3">
                <SELECT name="Vehicule" id="Vehicule" class="form-control" min="0">
                    <c:forEach items="${Vehicules}" var="item">
                        <c:choose>
                            <c:when test="${Utilise.Vehicule.idVehicule== item.idVehicule}"><OPTION value="${item.idVehicule}" selected="selected">${item.idVehicule}</OPTION></c:when>
                            <c:otherwise><OPTION value="${item.idVehicule}">${item.idVehicule}</OPTION></c:otherwise>
                        </c:choose>
                    </c:forEach>
                </SELECT>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-3 control-label">Borne : </label>
            <div class="col-md-3">
                <SELECT name="Borne" id="Borne" class="form-control" min="0">
                    <c:forEach items="${Bornes}" var="item">
                        <c:choose>
                            <c:when test="${Utilise.Borne.idBorne== item.idBorne}"><OPTION value="${item.idBorne}" selected="selected">${item.idBorne}</OPTION></c:when>
                            <c:otherwise><OPTION value="${item.idBorne}">${item.idBorne}</OPTION></c:otherwise>
                        </c:choose>
                    </c:forEach>
                </SELECT>
            </div>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-ok"></span>
                Ajouter
            </button>

            <button type="button" class="btn btn-default btn-primary"
                    onclick="{
                            window.location = 'listerUtilise.htm';
                        }">
                <span class="glyphicon glyphicon-remove"></span> Annuler

            </button>
        </div>
    </div>
</form>
<%@include file="footer.jsp"%>
</body>

</html>