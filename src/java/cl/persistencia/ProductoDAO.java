/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author CristianFaune
 */
public class ProductoDAO {
    Connection con;

    public ProductoDAO(Connection con) {
        this.con = con;
    }
    
    public ArrayList<Producto> buscarProducto(){
        ArrayList<Producto> lista = new ArrayList<>();
        Producto producto = null;
        
        String sql = "select * from producto";
        
        try (PreparedStatement pstmt = con.prepareStatement(sql);
            
            ResultSet rs = pstmt.executeQuery();){
            while (rs.next()) {
                producto = new Producto();
                
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setValor(rs.getInt("valor"));
                
                lista.add(producto);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar productos",e);
        }
        
        return lista;
    }
    
    public Producto buscarProductoId(int idProducto){
        Producto producto = null;
        
        String sql = "select * from producto where id_producto = ?";
        
        try (PreparedStatement pstmt = con.prepareStatement(sql)){
            
            pstmt.setInt(1, idProducto);
            
            try (ResultSet rs = pstmt.executeQuery()){
                if (rs.next()) {
                    producto = new Producto();
                    
                    producto.setIdProducto(rs.getInt("id_producto"));
                    producto.setDescripcion(rs.getString("descripcion"));
                    producto.setValor(rs.getInt("valor"));
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error en la búsqueda de producto por id",e);
        }
        
        return producto;
    }
}
