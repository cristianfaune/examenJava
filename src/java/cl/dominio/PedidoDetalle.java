/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dominio;

import java.io.Serializable;

/**
 *
 * @author CristianFaune
 */
public class PedidoDetalle implements Serializable{
    private int idPedidoDetalle;
    private int ticket;
    private int idProducto;
    private int cantidad;

    public PedidoDetalle(int idPedidoDetalle, int ticket, int idProducto, int cantidad) {
        this.idPedidoDetalle = idPedidoDetalle;
        this.ticket = ticket;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public PedidoDetalle() {
    }

    public int getIdPedidoDetalle() {
        return idPedidoDetalle;
    }

    public void setIdPedidoDetalle(int idPedidoDetalle) {
        this.idPedidoDetalle = idPedidoDetalle;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.idPedidoDetalle;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PedidoDetalle other = (PedidoDetalle) obj;
        if (this.idPedidoDetalle != other.idPedidoDetalle) {
            return false;
        }
        return true;
    }
    
    
}
