<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<H1> Modification d'une Vehicule</H1>
<form method="post" action="updateVehicule.htm?id=${veh.idVehicule}">
    <div class="col-md-12 well well-md">
        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">Reference Id </label>
            <div class="col-md-3">
                <INPUT type="text" name="rfid" value="${veh.rfid}" id="rfid" class="form-control" min="0">
            </div>
        </div>
        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-3 control-label">Etat de la Batterie</label>
            <div class="col-md-3">
                <INPUT type="text" name="etat" value="${veh.etatBatterie}" id="etat" class="form-control" min="0">
            </div>
        </div>
        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-3 control-label">Type de Vehicule : </label>
            <div class="col-md-3">
                <SELECT name="typeId" id="typeId" class="form-control" min="0">
                    <c:forEach items="${mesTypes}" var="item">
                        <OPTION value="${item.idTypeVehicule}">${item.typeVehicule} ${item.categorie}</OPTION>
                    </c:forEach>
                </SELECT>
            </div>
        </div>
        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-3 control-label">Station : </label>
            <div class="col-md-3">
                <SELECT name="stationId" id="stationId" class="form-control" min="0">
                    <c:forEach items="${mesStations}" var="item">
                        <OPTION value="${item.idStation}">${item.adresse} </OPTION>
                    </c:forEach>
                </SELECT>
            </div>
        </div>
        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-ok"></span>
                Valider
            </button>

            <button type="button" class="btn btn-default btn-primary"
                    onclick="{
                            window.location = 'index.htm';
                        }">
                <span class="glyphicon glyphicon-remove"></span> Annuler

            </button>
        </div>
    </div>
</form>
</body>
<%@include file="footer.jsp"%>
</html>