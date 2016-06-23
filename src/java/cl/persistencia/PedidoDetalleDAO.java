/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.PedidoDetalle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author CristianFaune
 */
public class PedidoDetalleDAO {

    Connection con;

    public PedidoDetalleDAO(Connection con) {
        this.con = con;
    }

    /**
     * Método para ingresar pedido detalle
     *
     * @param pedidoDetalle
     */
    public void ingresarPedidoDetalle(PedidoDetalle pedidoDetalle) {

        String sql = "insert into pedido_detalle (ticket, id_producto, cantidad) "
                + "values (?,?,?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, pedidoDetalle.getTicket());
            pstmt.setInt(2, pedidoDetalle.getIdProducto());
            pstmt.setInt(3, pedidoDetalle.getCantidad());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error en el ingreso de detalle pedido", e);
        }
    }

    /**
     * Método que retorna una lista con el detalle de un pedido por parámetro TICKET
     * @param ticket
     * @return 
     */
    public ArrayList<PedidoDetalle> detallePorTicket(int ticket) {
        ArrayList<PedidoDetalle> lista = new ArrayList<>();
        PedidoDetalle pedidoDetalle;

        String sql = "select * from pedido_detalle where ticket = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, ticket);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    pedidoDetalle = new PedidoDetalle();

                    pedidoDetalle.setIdPedidoDetalle(rs.getInt("id_pedido_detalle"));
                    pedidoDetalle.setTicket(rs.getInt("ticket"));
                    pedidoDetalle.setIdProducto(rs.getInt("id_producto"));
                    pedidoDetalle.setCantidad(rs.getInt("cantidad"));
                    
                    lista.add(pedidoDetalle);

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error en la búsqueda de pedido detalle por ticket",e);
        }
        return lista;
    }
}
