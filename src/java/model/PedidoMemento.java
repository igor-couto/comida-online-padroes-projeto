/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author 11944413600
 */
public class PedidoMemento {
    private PedidoStatus status;

    public PedidoMemento(PedidoStatus status) {
        this.status = status;
    }

    public String toString() {
        return status.getStatus();
    }    
    
}
