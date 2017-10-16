package model;


public class PedidoMemento {
    private PedidoStatus status;

    public PedidoMemento(PedidoStatus status) {
        this.status = status;
    }
    
    public PedidoStatus getEstadoSalvo(){
        return status;
    }

    public String toString() {
        return status.getStatus();
    }    
    
}
