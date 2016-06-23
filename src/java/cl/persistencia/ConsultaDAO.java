/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.Pedido;
import cl.dominio.PedidoDetalle;
import cl.dominio.Producto;
import cl.dto.DetalleProductoDTO;
import cl.dto.PedidoDetalleProductoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author CristianFaune
 */
public class ConsultaDAO {

    Connection con;

    public ConsultaDAO(Connection con) {
        this.con = con;
    }

    /**
     * Método para join entre pedido, detalle y producto y poder obtener el
     * regitro de un cliente a través del parámetro rut
     *
     * @param rut
     * @return
     */
    public ArrayList<PedidoDetalleProductoDTO> buscarDetallePedido(int rut) {
        ArrayList<PedidoDetalleProductoDTO> lista = new ArrayList<>();
        Pedido pedido;
        PedidoDetalle pedidoDetalle;
        Producto producto;

        String sql = "select * from pedido p join pedido_detalle pd using (ticket) "
                + "join producto pr using (id_producto) where p.rut = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, rut);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    pedido = new Pedido();

                    pedido.setTicket(rs.getInt("p.ticket"));
                    pedido.setRut(rs.getInt("p.rut"));
                    pedido.setMedioPago(rs.getString("p.medio_pago"));
                    pedido.setAgrandaBebidaPapas(rs.getByte("p.agranda_bebida_papas"));
                    pedido.setParaLlevar(rs.getByte("p.para_llevar"));
                    pedido.setTotal(rs.getInt("p.total"));

                    pedidoDetalle = new PedidoDetalle();

                    pedidoDetalle.setIdPedidoDetalle(rs.getInt("pd.id_pedido_detalle"));
                    pedidoDetalle.setTicket(rs.getInt("p.ticket"));
                    pedidoDetalle.setIdProducto(rs.getInt("pd.id_producto"));
                    pedidoDetalle.setCantidad(rs.getInt("pd.cantidad"));

                    producto = new Producto();

                    producto.setIdProducto(rs.getInt("pd.id_producto"));
                    producto.setDescripcion(rs.getString("pr.descripcion"));
                    producto.setValor(rs.getInt("pr.valor"));

                    lista.add(new PedidoDetalleProductoDTO(pedido, pedidoDetalle, producto));

                }

            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar pedido, detalle y producto", e);
        }

        return lista;
    }
 

    public ArrayList<DetalleProductoDTO> buscarDetalleProducto(){
        ArrayList<DetalleProductoDTO> lista = new ArrayList<>();
        PedidoDetalle pedidoDetalle;
        Producto producto;
        
        String sql = "select * from producto p join pedido_detalle pd "
                + "using(id_producto)";
        
        try (PreparedStatement pstmt = con.prepareStatement(sql)){
            
            try (ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    
                    pedidoDetalle = new PedidoDetalle();

                    pedidoDetalle.setIdPedidoDetalle(rs.getInt("pd.id_pedido_detalle"));
                    pedidoDetalle.setTicket(rs.getInt("pd.ticket"));
                    pedidoDetalle.setIdProducto(rs.getInt("p.id_producto"));
                    pedidoDetalle.setCantidad(rs.getInt("pd.cantidad"));

                    producto = new Producto();

                    producto.setIdProducto(rs.getInt("p.id_producto"));
                    producto.setDescripcion(rs.getString("p.descripcion"));
                    producto.setValor(rs.getInt("p.valor"));

                    lista.add(new DetalleProductoDTO(pedidoDetalle,producto));
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error en la búsqueda detalle producto");
        }
        return lista;
    }
    
}
