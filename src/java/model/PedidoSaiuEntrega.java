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
        return "Este pedido já saiu pra entrega. Não é possivel realizar esta ação";
    }

    @Override
    public String Recebido(Pedido p) {
        return "Este pedido já saiu pra entrega. Não é possivel realizar esta ação";
    }

    @Override
    public String EmPreparacao(Pedido p) {
        return "Este pedido já saiu pra entrega. Não é possivel realizar esta ação";
    }

    @Override
    public String SaiuEntrega(Pedido p) {
        return "Este pedido já saiu pra entrega.";
    }

    @Override
    public String Cancelado(Pedido p) {
        return "Este pedido já saiu pra entrega. Não é possivel realizar esta ação";
    }

    @Override
    public int getNextID() {
        return -1;
    }
    
}
