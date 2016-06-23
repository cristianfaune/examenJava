/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author CristianFaune
 */
public class ClienteDAO {

    Connection con;

    public ClienteDAO(Connection con) {
        this.con = con;
    }

    /**
     * Método para el ingreso de clientes
     *
     * @param cliente
     */
    public void ingresarCliente(Cliente cliente) {

        String sql = "insert into cliente values (?,?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, cliente.getRut());
            pstmt.setString(2, cliente.getNombre());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error en el ingreso de cliente", e);
        }
    }

    /**
     * Método para retornar un objeto Cliente según su rut
     *
     * @param rut
     * @return
     */
    public Cliente buscarClienteRut(int rut) {

        Cliente cliente = null;

        String sql = "select * from cliente where rut = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql);) {

            pstmt.setInt(1, rut);

            try (ResultSet rs = pstmt.executeQuery();) {

                if (rs.next()) {
                    cliente = new Cliente();

                    cliente.setRut(rs.getInt("rut"));
                    cliente.setNombre(rs.getString("nombre"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar cliente por rut", e);
        }

        return cliente;
    }
}
