package model;

public class PedidoRecebido implements PedidoStatus{

    @Override
    public String getStatus() {
        return "Recebido";
    }
    
    @Override
    public int getStatusID() {
        return 2;
    }

    @Override
    public String Aberto(Pedido p) {
        return "Seu pedido já foi recebido. Não foi possível realizar esta operação";
    }

    @Override
    public String Recebido(Pedido p) {
        return "Seu pedido já foi recebido";
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
        return 3;
    }
    
}
