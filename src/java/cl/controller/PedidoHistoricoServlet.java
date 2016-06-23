package cl.controller;

import cl.dominio.Cliente;
import cl.dominio.Pedido;
import cl.dominio.PedidoDetalle;
import cl.servicio.VentaServicio;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "PedidoHistoricoServlet", urlPatterns = {"/PedidoHistoricoServlet"})
public class PedidoHistoricoServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/examen")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String ticketPedido = request.getParameter("ticketPedido");
        PedidoDetalle nuevoDetalle = new PedidoDetalle();
        Pedido nuevoPedido = new Pedido();

        try (Connection con = ds.getConnection()) {

            VentaServicio venta = new VentaServicio(con);

            ArrayList<PedidoDetalle> detalle = venta.detallePorTicket(Integer.parseInt(ticketPedido));
            Pedido pedido = venta.buscarUltimoPedido(Integer.parseInt(ticketPedido));
            int nuevoTicket = venta.numeroTicket();

            nuevoPedido.setTicket(nuevoTicket);
            nuevoPedido.setRut(pedido.getRut());
            nuevoPedido.setAgrandaBebidaPapas(pedido.isAgrandaBebidaPapas());
            nuevoPedido.setMedioPago(pedido.getMedioPago());
            nuevoPedido.setParaLlevar(pedido.isParaLlevar());
            nuevoPedido.setTotal(pedido.getTotal());

            venta.ingresarPedido(nuevoPedido);

            for (PedidoDetalle detalle1 : detalle) {
                nuevoDetalle.setIdPedidoDetalle(detalle1.getIdPedidoDetalle());
                nuevoDetalle.setTicket(nuevoPedido.getTicket());
                nuevoDetalle.setIdProducto(detalle1.getIdProducto());
                nuevoDetalle.setCantidad(detalle1.getCantidad());
                
                venta.ingresarPedidoDetalle(nuevoDetalle);
            }
            
            Cliente cliente = venta.buscarCliente(nuevoPedido.getRut());
            
            request.setAttribute("total", nuevoPedido.getTotal());
            request.setAttribute("pedidoTicket", String.format("%04d", nuevoPedido.getTicket()));
            request.setAttribute("nombre", cliente.getNombre());
            request.getRequestDispatcher("/VentaExitosa.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException("Error de conexión", e);
        }

    }

}
