/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dto;

import cl.dominio.PedidoDetalle;
import cl.dominio.Producto;
import java.io.Serializable;

/**
 *
 * @author CristianFaune
 */
public class DetalleProductoDTO implements Serializable{
    private PedidoDetalle pedidoDetalle;
    private Producto producto;

    public DetalleProductoDTO(PedidoDetalle pedidoDetalle, Producto producto) {
        this.pedidoDetalle = pedidoDetalle;
        this.producto = producto;
    }

    public DetalleProductoDTO() {
    }

    public PedidoDetalle getPedidoDetalle() {
        return pedidoDetalle;
    }

    public void setPedidoDetalle(PedidoDetalle pedidoDetalle) {
        this.pedidoDetalle = pedidoDetalle;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    
}
