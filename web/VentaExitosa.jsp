<%-- 
    Document   : VentaExitosa
    Created on : 09-dic-2015, 9:23:53
    Author     : CristianFaune
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
        <title>Tu número de pedido</title>
    </head>
    <body>
        <div class="container">
            <%@include file="menu.jsp" %>
            <h1 class="text-center">Su pedido:</h1>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <table border="0" class="table table-hover">
                        <tbody>
                            <tr>
                                <td>
                                    <p>Muchas Gracias !</p> <h4><c:out value="${nombre}"/></h4>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <p>Tu número de pedido es:</p> <h2><strong><c:out value="${pedidoTicket}"/></strong></h2> 
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <p>Monto de su pedido:</p> <h3>$<c:out value="${total}"/></h3>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <p>Por favor pase por caja para que luego pueda esperar cómodamente su pedido</p>

                </div>
                <div class="col-md-4"></div>
            </div>
        </div>
    </body>
</html>
