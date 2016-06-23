/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.servicio;

import cl.dominio.Cliente;
import cl.dominio.Pedido;
import cl.dominio.PedidoDetalle;
import cl.dominio.Producto;
import cl.dto.DetalleProductoDTO;
import cl.dto.PedidoDetalleProductoDTO;
import cl.persistencia.ClienteDAO;
import cl.persistencia.ConsultaDAO;
import cl.persistencia.PedidoDAO;
import cl.persistencia.PedidoDetalleDAO;
import cl.persistencia.ProductoDAO;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author CristianFaune
 */
public class VentaServicio {

    PedidoDAO pedidoDAO;
    ProductoDAO productoDAO;
    ClienteDAO clienteDAO;
    PedidoDetalleDAO pedidoDetalleDAO;
    ConsultaDAO consultaDAO;

    public VentaServicio(Connection con) {
        this.pedidoDAO = new PedidoDAO(con);
        this.productoDAO = new ProductoDAO(con);
        this.clienteDAO = new ClienteDAO(con);
        this.pedidoDetalleDAO = new PedidoDetalleDAO(con);
        this.consultaDAO = new ConsultaDAO(con);
    }

    public int numeroTicket() {
        return pedidoDAO.obtenerNumeroTicket();
    }

    public int ultimoNumeroPedido() {
        return pedidoDAO.ultimoNumeroPedido();
    }
    
    public Pedido buscarUltimoPedido(int ticket){
        return pedidoDAO.buscarUltimoPedido(ticket);
    }

    public ArrayList<Producto> buscarProductos() {
        return productoDAO.buscarProducto();
    }

    public Cliente buscarCliente(int rut) {
        return clienteDAO.buscarClienteRut(rut);
    }

    public Producto buscarProductoId(int idProducto) {
        return productoDAO.buscarProductoId(idProducto);
    }

    public void ingresarCliente(Cliente cliente) {
        clienteDAO.ingresarCliente(cliente);
    }

    public void ingresarPedido(Pedido pedido) {
        pedidoDAO.ingresarPedido(pedido);
    }

    public void ingresarPedidoDetalle(PedidoDetalle pedidoDetalle) {
        pedidoDetalleDAO.ingresarPedidoDetalle(pedidoDetalle);
    }
    
    public ArrayList<PedidoDetalleProductoDTO> buscarDetallePedido(int rut){
        return consultaDAO.buscarDetallePedido(rut);
    }
    
    public ArrayList<DetalleProductoDTO> buscarDetalleProducto(){
        return consultaDAO.buscarDetalleProducto();
    }
    
    public ArrayList<Pedido> buscarPedidosRut (int rut){
        return pedidoDAO.buscarPedidosRut(rut);
    }
    
    public ArrayList<PedidoDetalle> detallePorTicket(int ticket) {
        return pedidoDetalleDAO.detallePorTicket(ticket);
    }
}
