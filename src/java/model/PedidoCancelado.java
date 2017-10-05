package model;

public class PedidoCancelado implements PedidoStatus{

    @Override
    public String getStatus() {
        return "Cancelado";
    }

    @Override
    public int getStatusID() {
        return 0;
    }

    @Override
    public String Aberto(Pedido p) {
        return "Este pedido já foi cancelado. Não é possivel realizar esta ação";
    }

    @Override
    public String Recebido(Pedido p) {
        return "Este pedido já foi cancelado. Não é possivel realizar esta ação";
    }

    @Override
    public String EmPreparacao(Pedido p) {
        return "Este pedido já foi cancelado. Não é possivel realizar esta ação";
    }

    @Override
    public String SaiuEntrega(Pedido p) {
        return "Este pedido já foi cancelado. Não é possivel realizar esta ação";
    }

    @Override
    public String Cancelado(Pedido p) {
        return "Este pedido já foi cancelado.";
    }

    @Override
    public int getNextID() {
        return 0;
    }

    
    
}
