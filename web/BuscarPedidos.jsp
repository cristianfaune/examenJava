<%-- 
    Document   : BuscarPedidos
    Created on : 09-dic-2015, 21:09:21
    Author     : CristianFaune
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
        <title>Pedidos Cliente</title>
    </head>
    <body>
        <div class="container">
            <%@include file="menu.jsp" %>
            <h3 class="text-center">Encuentra tus últimos pedidos y solicítalos de inmediato</h3>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <table border="0" class="table table-hover">
                        <thead>
                            <tr>
                                <th>Pedido</th>
                                <th>Valor</th>
                                <th>Acción</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="p" items="${pedido}">
                                <tr>
                                    <td>
                                        <c:forEach var="d" items="${detalle}">
                                            <c:if test="${d.pedidoDetalle.ticket == p.ticket}">
                                                <c:out value="${d.producto.descripcion} /"/>
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                    <td><c:out value="$ ${p.total}"/></td>

                                    <td>
                                        <form action="<c:url value="/PedidoHistoricoServlet"/>" method="post">
                                            <input type="hidden" name="ticketPedido" value="${p.ticket}"/>
                                            <input type="submit" value="Pedir" class="btn btn-default btn-primary btn-xs"/>
                                        </form>
                                    </td>

                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-3"></div>
            </div>
        </div>
    </body>
</html>
