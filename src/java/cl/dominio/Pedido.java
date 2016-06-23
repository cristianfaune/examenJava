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
public class Pedido implements Serializable{
    private int ticket;
    private int rut;
    private String medioPago;
    private byte agrandaBebidaPapas;
    private byte paraLlevar;
    private int total;

    public Pedido(int ticket, int rut, String medioPago, byte agrandaBebidaPapas, byte paraLlevar, int total) {
        this.ticket = ticket;
        this.rut = rut;
        this.medioPago = medioPago;
        this.agrandaBebidaPapas = agrandaBebidaPapas;
        this.paraLlevar = paraLlevar;
        this.total = total;
    }

    public Pedido() {
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public byte isAgrandaBebidaPapas() {
        return agrandaBebidaPapas;
    }

    public void setAgrandaBebidaPapas(byte agrandaBebidaPapas) {
        this.agrandaBebidaPapas = agrandaBebidaPapas;
    }

    public byte isParaLlevar() {
        return paraLlevar;
    }

    public void setParaLlevar(byte paraLlevar) {
        this.paraLlevar = paraLlevar;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.ticket;
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
        final Pedido other = (Pedido) obj;
        if (this.ticket != other.ticket) {
            return false;
        }
        return true;
    }
    
    
}
