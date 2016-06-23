package cl.controller;

import cl.dominio.PedidoDetalle;
import cl.dominio.Producto;
import cl.dto.DetalleProductoDTO;
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
@WebServlet(name = "AgregarDetalleServlet", urlPatterns = {"/AgregarDetalleServlet"})
public class AgregarDetalleServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/examen")
    private DataSource ds;
    static ArrayList<DetalleProductoDTO> detalle = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection con = ds.getConnection()) {

            VentaServicio venta = new VentaServicio(con);

            request.setAttribute("productos", venta.buscarProductos());
            request.getRequestDispatcher("/index.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexión", e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String producto = request.getParameter("producto");
        String cantidad = request.getParameter("cantidad");
        Map<String, String> mapMensaje = new HashMap<>();
        PedidoDetalle pedidoDetalle = new PedidoDetalle();
        

        try (Connection con = ds.getConnection()) {

            VentaServicio venta = new VentaServicio(con);

            int numTicket = venta.numeroTicket();

            if (cantidad.isEmpty() || Integer.parseInt(cantidad) == 0) {
                mapMensaje.put("errorCantidad", " cant > 0");
            } else {
                pedidoDetalle.setCantidad(Integer.parseInt(cantidad));
            }

            if (Integer.parseInt(producto) == 0) {
                mapMensaje.put("errorProducto", "** Debe seleccionar un producto **");
            } else {
                pedidoDetalle.setIdProducto(Integer.parseInt(producto));
            }

            Producto objProducto = venta.buscarProductoId(Integer.parseInt(producto));
            

            if (!mapMensaje.isEmpty()) {
                request.setAttribute("mapMensaje", mapMensaje);
                request.setAttribute("productos", venta.buscarProductos());
                request.setAttribute("lista", detalle);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } else {
                pedidoDetalle.setTicket(numTicket);
                detalle.add(new DetalleProductoDTO(pedidoDetalle, objProducto));
                request.setAttribute("ticket", numTicket);
                request.setAttribute("lista", detalle);
                request.setAttribute("productos", venta.buscarProductos());
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error de conexión", e);
        }
    }

}
