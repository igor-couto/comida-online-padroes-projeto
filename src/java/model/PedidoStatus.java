package model;

public interface PedidoStatus {
    
    public String getStatus();
    
    public int getStatusID();
    
    public String Aberto(Pedido p);
    
    public String Recebido(Pedido p);
    
    public String EmPreparacao(Pedido p);
    
    public String SaiuEntrega(Pedido p);
    
    public String Cancelado(Pedido p);
}
