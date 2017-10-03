package model;

public class PedidoSaiuEntrega implements PedidoStatus{

    @Override
    public String getStatus() {
        return "Saiu para entrega";
    }

    @Override
    public int getStatusID() {
        return 4;
    }

    @Override
    public String Aberto(Pedido p) {
        return "Não foi possível realizar esta operação";
    }

    @Override
    public String Recebido(Pedido p) {
        return "Não foi possível realizar esta operação";
    }

    @Override
    public String EmPreparacao(Pedido p) {
        return "Não foi possível realizar esta operação";
    }

    @Override
    public String SaiuEntrega(Pedido p) {
        return "Não foi possível realizar esta operação";
    }

    @Override
    public String Cancelado(Pedido p) {
        return "Não foi possível realizar esta operação";
    }
    
}
