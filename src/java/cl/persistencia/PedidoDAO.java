/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author CristianFaune
 */
public class PedidoDAO {

    Connection con;

    public PedidoDAO(Connection con) {
        this.con = con;
    }

    /**
     * Método que retorna el último número de ticket(+1) desde la base de datos
     * para ocuparlo en el siguiente pedido
     *
     * @return
     */
    public int obtenerNumeroTicket() {

        String sql = "SELECT COALESCE(MAX(ticket),0)+\"1\" as ticket from pedido";
        int numTicket = 0;

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                numTicket = rs.getInt("ticket");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener número de ticket", e);
        }
        return numTicket;
    }

    /**
     * Método para ingresar pedido
     *
     * @param pedido
     */
    public void ingresarPedido(Pedido pedido) {

        String sql = "insert into pedido values (?,?,?,?,?,?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, pedido.getTicket());
            pstmt.setInt(2, pedido.getRut());
            pstmt.setString(3, pedido.getMedioPago());
            pstmt.setByte(4, pedido.isAgrandaBebidaPapas());
            pstmt.setByte(5, pedido.isParaLlevar());
            pstmt.setInt(6, pedido.getTotal());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error en el ingreso de pedido", e);
        }
    }

    /**
     * Método para retornar número de último pedido
     *
     * @return
     */
    public int ultimoNumeroPedido() {

        String sql = "SELECT COALESCE(MAX(ticket),0) as ticket from pedido";
        int numTicket = 0;

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                numTicket = rs.getInt("ticket");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener número de ultimo pedido", e);
        }
        return numTicket;
    }

    /**
     * Método que busca todos los datos del último pedido
     * @param ticket
     * @return 
     */
    public Pedido buscarUltimoPedido(int ticket) {

        Pedido pedido = null;

        String sql = "select * from pedido where ticket = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, ticket);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    pedido = new Pedido();

                    pedido.setTicket(rs.getInt("ticket"));
                    pedido.setRut(rs.getInt("rut"));
                    pedido.setMedioPago(rs.getString("medio_pago"));
                    pedido.setAgrandaBebidaPapas(rs.getByte(("agranda_bebida_papas")));
                    pedido.setParaLlevar(rs.getByte("para_llevar"));
                    pedido.setTotal(rs.getInt("total"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener número de ultimo pedido", e);
        }
        return pedido;
    }
    
    
    public ArrayList<Pedido> buscarPedidosRut(int rut){
        ArrayList<Pedido> lista = new ArrayList<>();
        Pedido pedido;
        
        String sql = "select * from pedido where rut = ?";
        
        try (PreparedStatement pstmt = con.prepareStatement(sql)){
            
            pstmt.setInt(1, rut);
            
            try (ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    pedido = new Pedido();
                    
                    pedido.setTicket(rs.getInt("ticket"));
                    pedido.setRut(rs.getInt("rut"));
                    pedido.setMedioPago(rs.getString("medio_pago"));
                    pedido.setAgrandaBebidaPapas(rs.getByte("agranda_bebida_papas"));
                    pedido.setParaLlevar(rs.getByte("para_llevar"));
                    pedido.setTotal(rs.getInt("total"));
                    
                    lista.add(pedido);
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error en la búsqueda de pedidos por rut",e);
        }
        
        return lista;
    }
}
