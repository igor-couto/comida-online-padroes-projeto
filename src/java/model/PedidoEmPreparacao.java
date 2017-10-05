package model;

public class PedidoEmPreparacao implements PedidoStatus{

    @Override
    public String getStatus() {
        return "Em preparação";
    }
    
    @Override
    public int getStatusID() {
        return 3;
    }

    @Override
    public String Aberto(Pedido p) {
       return "Este pedido já está sendo preparado. Não foi possível realizar esta operação";
    }

    @Override
    public String Recebido(Pedido p) {
        return "Este pedido já está sendo preparado. Não foi possível realizar esta operação";
    }

    @Override
    public String EmPreparacao(Pedido p) {
        p.setStatus(new PedidoEmPreparacao());
        return "Seu está em preparação";
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
        return 4;
    }
    
    
    
}
