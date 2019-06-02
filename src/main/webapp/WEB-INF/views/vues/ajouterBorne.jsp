<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<H1> Ajout d'une Borne </H1>
<form method="post" action="insererBorne.htm" onsubmit="return teste()">
    <div class="col-md-12 well well-md">
        <h1>Ajouter Borne</h1>
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
                <SELECT name="station" id="station" class="form-control" min="0">
                    <c:forEach items="${stations}" var="item">
                        <c:choose>
                            <c:when test="${Borne.station.idStation == item.station}"><OPTION value="${item.station}" selected="selected">${item.station}</OPTION></c:when>
                            <c:otherwise><OPTION value="${item.station}">${item.station}</OPTION></c:otherwise>
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