<%-- 
    Document   : index
    Created on : 30-nov-2015, 9:47:02
    Author     : CristianFaune
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="css/bootstrap.min.css" rel="stylesheet"/>
        <title>Pedidos John Master</title>
    </head>
    <body>
        <sql:setDataSource var="ds" driver="com.mysql.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/et_dej4501?zeroDateTimeBehavior=convertToNull"
                           user="examen"  password="examen" />

        <sql:query dataSource="${ds}" var="producto">
            SELECT * from producto order by id_producto
        </sql:query>
        <!--Inicio conteneder principal-->
        <div class="container">
            <%@include file="menu.jsp" %>
            <br/>
            <div class="page-header">
                <h1>Bienvenido a John Master <small>Aquí encontrará los mejores productos</small></h1>
            </div>
            <br/>
            <div class="row">
                <div class="col-md-4">
                </div>
                <!-- Inicio contenedor campos nombre y rut-->
                <div class="col-md-8">
                    <p class="text-left"><h5>Seleccione un producto o combo, indique cantidad y agregue</h5></p>
                    <form action="<c:url value="/AgregarDetalleServlet"/>" method="post">
                        <div class="col-lg-6">
                            <select class="form-control" name="producto">
                                <option value="0">--Seleccione un producto--</option>
                                <c:forEach var="p" items="${producto.rows}">
                                    <option value="${p.id_producto}">
                                        <c:out value="${p.descripcion}"/>
                                    </option>
                                </c:forEach>
                            </select>
                            <p class="text-danger"><c:out value="${mapMensaje['errorProducto']}"/></p>
                        </div>
                        <div>
                            <c:if test="${empty mapMensaje}">
                                <div class="col-lg-2">
                                    <input type="number" class="form-control" name="cantidad" placeholder="cantidad"/>
                                </div>
                            </c:if>
                            <c:if test="${not empty mapMensaje}">
                                <div class="form-group has-error col-lg-2">
                                    <input type="number" class="form-control" name="cantidad" placeholder="<c:out value="${mapMensaje['errorCantidad']}"/>"/>
                                </div>
                            </c:if>
                        </div>
                        <div>
                            <input type="submit" value="Agregar" class="btn btn-default btn-primary"/>
                        </div>
                    </form>
                    <br/><br/><br/>
                    <table border="0" class="table">
                        <thead>
                            <tr>
                                <th>Producto</th>
                                <th>Cantidad</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="l" items="${lista}">
                                <tr>
                                    <td><c:out value="${l.producto.descripcion}"/></td>
                                    <td><c:out value="${l.pedidoDetalle.cantidad}"/></td>
                                    <td></td>
                                    <c:set var="subTotal" value="${l.pedidoDetalle.cantidad*l.producto.valor}"/>
                                    <c:set var="total" value="${subTotal + total}"/>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <h4 class="text-right"><strong><c:out value="Total: $${total}" default="0" /></strong></h4>
                    <h4 class="text-danger"><c:out value="${mapMensaje['errorIngreso']}"/></h4>
                </div>

                <!--Fin contenedor de campos nombre y rut-->
            </div>
            <div class="row">
                <div class="col-md-4 col-lg-offset-0">
                    <form class="form-horizontal" action="<c:url value="/PedidoServlet"/>" method="post">
                        <div class="checkbox col-lg-offset-3">
                            <h5>Opcionales:</h5>
                            <p><input type="checkbox" id="blankCheckbox1" value="true" name="bebidapapas"/><i>Agrandar bebida y papas</i></p>
                        </div>
                        <div class="checkbox col-lg-offset-3">
                            <p> <input type="checkbox" id="blankCheckbox2" value="true" name="llevar"/><i>Pedido para llevar</i></p>

                        </div>
                        <br/>
                        <div class="radio col-lg-offset-4">
                            <h5>Selecciona tu forma de pago:</h5>
                            <label>
                                <input type="radio" name="pago" id="optionsRadios1" value="Efectivo" checked><i>Efectivo</i>
                            </label>
                        </div>
                        <div class="radio col-lg-offset-4">
                            <label>
                                <input type="radio" name="pago" id="optionsRadios2" value="Tarjeta de débito / crédito"><i>Tarjeta de débito / Crédito</i>
                            </label>
                        </div>
                        <br/>
                </div>             

                <div class="col-md-4">
                    <br/>

                    <p><h5>Indíquenos su nombre y rut para identificarlo</h5></p>
                    <c:if test="${empty mapMensajeNombre}">
                        <div class="form-group has-success col-lg-12">
                            <label class="control-label" for="inputSuccess1"></label>
                            <input type="text" class="form-control" id="inputSuccess1" 
                                   name="nombre" aria-describedby="helpBlock2" placeholder="Ingrese nombre"
                                   value="<c:out value="${param.nombre}"/>"/>
                            <span id="helpBlock2" class="help-block"></span>
                        </div>
                    </c:if>
                    <c:if test="${not empty mapMensajeNombre}">
                        <div class="form-group has-error col-lg-12">
                            <label class="control-label" for="inputError1"><c:out value="${mapMensajeNombre['errorNombre']}"/></label>
                            <input type="text" class="form-control" 
                                   id="inputError1" name="nombre" placeholder="** Error en el ingreso" autofocus="true">
                        </div>
                    </c:if>
                    <c:if test="${empty mapMensajeRut}">
                        <div class="form-group has-success col-lg-12">
                            <label class="control-label" for="inputSuccess2"></label>
                            <input type="number" class="form-control" id="inputSuccess1" 
                                   name="rut" aria-describedby="helpBlock2" placeholder="Ingrese rut (solo números)"
                                   value="<c:out value="${param.rut}"/>"/>
                            <span id="helpBlock2" class="help-block"></span>
                        </div>
                    </c:if>
                    <c:if test="${not empty mapMensajeRut}">
                        <div class="form-group has-error col-lg-12">
                            <label class="control-label" for="inputError2"><c:out value="${mapMensajeRut['errorRut']}"/></label>
                            <input type="number" class="form-control" 
                                   id="inputError1" name="rut" placeholder="** Error en el ingreso">
                        </div>
                    </c:if>
                </div>
            </div>
            <div class="col-md-4 col-lg-offset-8">
                <div>
                    <input type="submit" value="Enviar Pedido" class="btn btn-default btn-lg btn-warning"/>
                    <a href="<c:url value="/PedidoServlet"/>" class="btn btn-default btn-lg btn-danger">Cancelar Pedido</a>
                </div>
            </div>
        </form>
    </div>
    <!--Fin contenedor principal-->
</body>
</html>
