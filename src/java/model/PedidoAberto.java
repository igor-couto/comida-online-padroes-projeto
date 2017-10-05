/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author fhnri
 */
public class PedidoAberto  implements PedidoStatus{

    @Override
    public String getStatus() {
        return "Aberto";
    }

    @Override
    public int getStatusID() {
        return 1;
    }

    @Override
    public String Aberto(Pedido p) {
        return "Este pedido já está aberto";
    }

    @Override
    public String Recebido(Pedido p) {
        p.setStatus(new PedidoRecebido());
        return "Seu pedido foi recebido";
    }

    @Override
    public String EmPreparacao(Pedido p) {
        p.setStatus(new PedidoEmPreparacao());
        return "Seu pedido está em preparação";
    }

    @Override
    public String SaiuEntrega(Pedido p) {
        p.setStatus(new PedidoSaiuEntrega());
        return "Seu pedido saiu para entrega";
    }

    @Override
    public String Cancelado(Pedido p) {
        p.setStatus(new PedidoCancelado());
        return "Seu pedido foi cancelado";
    }

    @Override
    public int getNextID() {
        return 2;
    }
    
}
