<%-- 
    Document   : menu
    Created on : 01-dic-2015, 22:16:33
    Author     : CristianFaune
--%>

<%@page import="java.sql.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="css/bootstrap.min.css" rel="stylesheet"/>
    </head>
    <body>
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="<c:url value="/PedidoServlet"/>">
                        <img src="imagenes/johnmaster.jpg" alt="logo" class="img-responsive img-thumbnail" width="100px"/>
                    </a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                    </ul>
                    <form class="navbar-form navbar-left" role="search" action="<c:url value="/BuscarPedidosServlet"/>" method="post">
                        <div class="form-group">
                            <input type="number" class="form-control" name="rut" placeholder="Ingrese su rut">
                        </div>
                        <button type="submit" class="btn btn-default">Buscar Pedidos</button>
                        <p class="text-danger"><c:out value="${mapMensaje['errorRut']}"/></p>
                    </form>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="Ayuda.jsp">Ayuda</a></li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
    </body>
</html>
