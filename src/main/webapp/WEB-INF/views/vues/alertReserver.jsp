<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<div class="col-md-12 well well-md">
    <center><h1>Vehicule reservee</h1></center>
</div>
    <div class="alert-danger" role="alert">
        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
        Impossible d'appliquer cette action sur des vehicule(s) reservee(s)!.
    </div>

<div class="form-group">
    <div class="col-md-6 col-md-offset-3">
        <button type="button" class="btn btn-default btn-primary"  onclick="relocate_home()">
            <span class="glyphicon glyphicon-log-in"></span>
            Accueil
        </button>
    </div>
</div>


<script>
    function relocate_home()
    {
        location.href = "index.htm";
    }
</script>
<%@include file="footer.jsp"%>
</body>
</html>




