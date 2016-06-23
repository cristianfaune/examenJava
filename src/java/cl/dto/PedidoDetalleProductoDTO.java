/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dto;

import cl.dominio.Pedido;
import cl.dominio.PedidoDetalle;
import cl.dominio.Producto;
import java.io.Serializable;

/**
 *
 * @author CristianFaune
 */
public class PedidoDetalleProductoDTO implements Serializable{
    private Pedido pedido;
    private PedidoDetalle pedidoDetalle;
    private Producto Producto;

    public PedidoDetalleProductoDTO(Pedido pedido, PedidoDetalle pedidoDetalle, Producto Producto) {
        this.pedido = pedido;
        this.pedidoDetalle = pedidoDetalle;
        this.Producto = Producto;
    }

    public PedidoDetalleProductoDTO() {
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public PedidoDetalle getPedidoDetalle() {
        return pedidoDetalle;
    }

    public void setPedidoDetalle(PedidoDetalle pedidoDetalle) {
        this.pedidoDetalle = pedidoDetalle;
    }

    public Producto getProducto() {
        return Producto;
    }

    public void setProducto(Producto Producto) {
        this.Producto = Producto;
    }
    
    
}
