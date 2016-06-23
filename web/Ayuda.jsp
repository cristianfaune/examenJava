<%-- 
    Document   : Ayuda
    Created on : 10-dic-2015, 13:07:37
    Author     : CristianFaune
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
        <title>Ayuda</title>
    </head>
    <body>
        <div class="container">
            <%@include file="menu.jsp" %>
            <h3 class="text-center">¿Preguntas frecuentes?</h3>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <h4>¿Cómo utilizo el sistema?</h4>
                    <br/>
                    <p class="text-justify">Primero seleccione <strong>por lo menos un</strong> producto de nuestra larga y sabrosa lista que
                        que tenemos preparada para ti, indica la cantidad por cada ítem y finalmente identifícate
                        solo con tu <strong>nombre y rut<i> (solo números)</i></strong>. Además puedes seleccionar si necesitas tu pedido para llevar, si deseas
                        agrandar tus papas o bebida y tu forma de pago preferida.           
                    </p>
                    <br/>
                    <h4>¿Tengo que registrarme antes?</h4>
                    <br/>
                    <p class="text-justify"> NADA DE ESO !<br/>
                        Simplemente haces tu pedido y al momento de ingresar tu nombre y rut si no
                        estás previamente registrado te llevaremos a nuestra base de datos inmediatamente y
                        de forma automática para que puedas seguir comprando todas las veces que quieras.
                    </p>
                    <br/>
                    <h4>¿Puedo revisar mis pedidos anteriores?</h4>
                    <br/>
                    <p class="text-justify"> POR SUPUESTO !<br/>
                        En la parte superior izquierda de tu pantalla <i>(al lado del logo)</i>
                        hay un casillero en donde puedes buscar todos tus pedidos solo escribiendo
                        tu rut y presionando BUSCAR, esto te llevará a una ventana en donde puedes
                        volver a pedir tu pack favorito.
                    </p>
                </div>
                <div class="col-md-4"></div>
            </div>
        </div>

    </body>
</html>
