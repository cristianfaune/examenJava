package cl.controller;

import cl.dominio.Cliente;
import cl.servicio.VentaServicio;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import cl.controller.AgregarDetalleServlet;
import cl.dominio.Pedido;
import cl.dto.DetalleProductoDTO;
import java.util.ArrayList;

/**
 *
 * @author CristianFaune
 */
@WebServlet(name = "PedidoServlet", urlPatterns = {"/PedidoServlet"})
public class PedidoServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/examen")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection con = ds.getConnection()) {

            VentaServicio venta = new VentaServicio(con);

            ArrayList<DetalleProductoDTO> lista = cl.controller.AgregarDetalleServlet.detalle;

            lista.clear();

            request.setAttribute("listaProd", lista);
            request.setAttribute("productos", venta.buscarProductos());
            request.getRequestDispatcher("/index.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexión", e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String nombre = request.getParameter("nombre");
        String strRut = request.getParameter("rut");
        String bebidaPapas = request.getParameter("bebidapapas");
        String llevar = request.getParameter("llevar");
        String pago = request.getParameter("pago");
        Map<String, String> mapMensajeRut = new HashMap<>();
        Map<String, String> mapMensajeNombre = new HashMap<>();
        Map<String, String> mapMensaje = new HashMap<>();
        Pedido pedido = new Pedido();

        try (Connection con = ds.getConnection()) {

            VentaServicio venta = new VentaServicio(con);

            if (nombre.isEmpty()) {
                mapMensajeNombre.put("errorNombre", "** No debe dejar el campo vacío **");
            } else if (!nombre.isEmpty()) {
                for (int i = 0; i < nombre.length(); i++) {
                    if (Character.isDigit(nombre.charAt(i))) {
                        mapMensajeNombre.put("errorNombre", "** Solo ingrese letras **");
                        break;
                    }
                }
            }

            if (strRut.isEmpty()) {
                mapMensajeRut.put("errorRut", "** No debe dejar el campo vacío **");
            }

            if (!mapMensajeRut.isEmpty() || !mapMensajeNombre.isEmpty()) {
                request.setAttribute("pago", pago);
                request.setAttribute("mapMensajeNombre", mapMensajeNombre);
                request.setAttribute("mapMensajeRut", mapMensajeRut);
                request.setAttribute("productos", venta.buscarProductos());
                request.setAttribute("lista", cl.controller.AgregarDetalleServlet.detalle);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } else if (cl.controller.AgregarDetalleServlet.detalle.size()>0) {
                
                Cliente cliente = venta.buscarCliente(Integer.parseInt(strRut));
                if (cliente == null) {
                    Cliente clienteNuevo;
                    clienteNuevo = new Cliente();
                    clienteNuevo.setRut(Integer.parseInt(strRut));
                    clienteNuevo.setNombre(nombre);

                    venta.ingresarCliente(clienteNuevo);
                }

                try {
                    if (bebidaPapas != null) {
                        pedido.setAgrandaBebidaPapas((byte) 1);
                    } else {
                        pedido.setAgrandaBebidaPapas((byte) 0);
                    }

                } catch (Exception e) {
                    throw new RuntimeException("Error ingreso papas", e);
                }

                try {
                    if (llevar != null) {
                        pedido.setParaLlevar((byte) 1);
                    } else {
                        pedido.setParaLlevar((byte) 0);
                    }

                } catch (Exception e) {
                    throw new RuntimeException("Error ingreso llevar", e);
                }

                try {
                    pedido.setMedioPago(pago);
                } catch (Exception e) {
                    throw new RuntimeException("Error en el ingreso de pago", e);
                }

                pedido.setRut(Integer.parseInt(strRut));

                int total = 0;
                int subTotal = 0;
                try {
                    for (DetalleProductoDTO pd : cl.controller.AgregarDetalleServlet.detalle) {
                        subTotal = pd.getPedidoDetalle().getCantidad() * pd.getProducto().getValor();
                        total += subTotal;
                        pedido.setTotal(total);
                    }
                } catch (Exception e) {
                    throw new RuntimeException("error total", e);
                }
                
                int numTicket = venta.numeroTicket();
                
                pedido.setTicket(numTicket);

                venta.ingresarPedido(pedido);

                try {
                    cl.controller.AgregarDetalleServlet.detalle.stream().forEach((pd) -> {
                        venta.ingresarPedidoDetalle(pd.getPedidoDetalle());
                    });
                } catch (Exception e) {
                    throw new RuntimeException("Error ingresar pedido detalle", e);
                }

                request.setAttribute("nombre", nombre);
                request.setAttribute("total", total);
                request.setAttribute("pedidoTicket", String.format("%04d", venta.ultimoNumeroPedido()));
                request.getRequestDispatcher("/VentaExitosa.jsp").forward(request, response);
                cl.controller.AgregarDetalleServlet.detalle.clear();

            }else{
                mapMensaje.put("errorIngreso", "** Debe agregar productos a su pedido **");
                request.setAttribute("mapMensaje", mapMensaje);
                request.setAttribute("productos", venta.buscarProductos());
                request.setAttribute("lista", cl.controller.AgregarDetalleServlet.detalle);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexión", e);
        }
    }

}
