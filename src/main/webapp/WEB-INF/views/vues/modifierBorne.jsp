<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<H1> Modification d'une borne </H1>
<form method="post" action="ajoutModificationBorne.htm" onsubmit="return teste()">
    <div class="col-md-12 well well-md">
        <h1>Modifier une borne</h1>
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
            <label class="col-md-3 control-label">Station : </label>
            <div class="col-md-3">


                <div class="col-md-3">
                    <INPUT type="hidden" name="borne" value=${Borne.idBorne} id="borne" class="form-control" min="0">

                </div>

                <SELECT name="station" id="station" class="form-control" min="0">
                    <c:forEach items="${stations}" var="item">
                        <c:choose>
                            <c:when test="${Borne.station.idStation== item.idStation}"><OPTION value="${item.idStation}" selected="selected">${item.idStation}</OPTION></c:when>
                            <c:otherwise><OPTION value="${item.idStation}">${item.idStation}</OPTION></c:otherwise>
                        </c:choose>
                    </c:forEach>
                </SELECT>

            </div>
        </div>

        <div class="form-group">
            <label class="col-md-3 control-label">Vehicule : </label>
            <div class="col-md-3">

                <SELECT name="vehicule" id="vehicule" class="form-control" min="0">
                    <OPTION value=""></OPTION>
                    <c:forEach items="${vehicules}" var="item">
                        <c:choose>
                            <c:when test="${Borne.vehicule.idVehicule== item.idVehicule}"><OPTION value="${item.idVehicule}" selected="selected">${item.idVehicule}</OPTION></c:when>
                            <c:otherwise><OPTION value="${item.idVehicule}">${item.idVehicule}</OPTION></c:otherwise>
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
                            window.location = 'listerBorne.htm';
                        }">
                <span class="glyphicon glyphicon-remove"></span> Annuler

            </button>
        </div>
    </div>
</form>
<%@include file="footer.jsp"%>
</body>

</html>