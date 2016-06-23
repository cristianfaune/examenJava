package cl.controller;

import cl.dominio.Pedido;
import cl.dto.PedidoDetalleProductoDTO;
import cl.servicio.VentaServicio;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author CristianFaune
 */
@WebServlet(name = "BuscarPedidosServlet", urlPatterns = {"/BuscarPedidosServlet"})
public class BuscarPedidosServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/examen")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String rut = request.getParameter("rut");
        Map<String, String> mapMensaje = new HashMap<>();

        try (Connection con = ds.getConnection()) {

            VentaServicio venta = new VentaServicio(con);

            if (rut.isEmpty()) {
                mapMensaje.put("errorRut", "** Debe ingresar un rut válido **");
            } else if (rut.length() > 11) {
                mapMensaje.put("errorRut", "** El rut ingresado es demasiado largo **");
            }

            if (!mapMensaje.isEmpty()) {
                request.setAttribute("mapMensaje", mapMensaje);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } else {
                request.setAttribute("pedido", venta.buscarPedidosRut(Integer.parseInt(rut)));
                request.setAttribute("detalle", venta.buscarDetallePedido(Integer.parseInt(rut)));
                request.getRequestDispatcher("/BuscarPedidos.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error de conexión", e);
        }

    }

}
