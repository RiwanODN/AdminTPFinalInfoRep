<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<H1> Reservation d'une Vehicule</H1>
<form method="post" action="confirmation.htm?id=${veh.idVehicule}">
    <div class="col-md-12 well well-md">

        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">Clients : </label>
                    <div class="col-md-3">
                        <select name="clientId" id="clientId" required>
                            <option value="">--Choisir le client--</option>
                            <c:forEach items="${mesClients}" var="item">
                                <option value="${item.idClient}">${item.nom} ${item.prenom}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="row" >
                    <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">Date de Reservation</label>
                    <div class="col-md-3">
                        <input type="date" name="dateReservation" id="dateReservation" class="form-control">
                        <input type="time" name="timeReservation" id="timeReservation" class="form-control">
                    </div>
                </div>
                <div class="row" >
                    <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">Date d'echeance</label>
                    <div class="col-md-3">
                        <INPUT type="date" name="dateEcheance" id="dateEcheance" class="form-control">
                        <input type="time" name="timeEcheance" id="timeEcheance" class="form-control">
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