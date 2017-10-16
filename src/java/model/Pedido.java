package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {
    protected int id;
    protected Date date;
    protected Date ultimaAlteracao;
    protected PedidoStatus status;//1 - Aberto, 2 - Recebido, 3 - Em preparacao,4 - Saiu pra entrega, 0 - Cancelado
    protected Usuario usuario;
    protected List<Produto> produtos;
    public static SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yy");
    protected FormaPagamento formaPagamento;
    
    protected ArrayList<PedidoMemento> estadoSalvos = new ArrayList();

    public ArrayList<PedidoMemento> getEstadoSalvos() {
        return estadoSalvos;
    }

    public void guardarPedido (){
         estadoSalvos.add( saveToMemento() );
    }
    
    public PedidoMemento saveToMemento(){
        return new PedidoMemento(status);
    }
    
    public void restoreFromMemento(PedidoMemento memento){
        status = memento.getEstadoSalvo();
    }
    

    
    public Pedido(int id, Date date, Date ultimaAlteracao, PedidoStatus status, Usuario usuario, FormaPagamento formaPagamento, List<Produto> produtos) {
        this.id = id;
        this.date = date;
        this.ultimaAlteracao = ultimaAlteracao;
        this.status = status;
        this.usuario = usuario;
        this.produtos = produtos;
        this.formaPagamento=formaPagamento;
        
    }

    public Pedido(Usuario usuario, List<Produto> produtos) {
        this.usuario = usuario;
        this.produtos = produtos;
        date=new Date();
        ultimaAlteracao=new Date();
        status=new PedidoAberto();
        this.formaPagamento=new PagamentoDinheiro();
    }

    public int getId() {
        return id;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }
    
    public String getDateString(){
        return Pedido.dataFormato.format(date);
    }

    public Date getUltimaAlteracao() {
        return ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }
    
    

    public void setDate(Date date) {
        this.date = date;
    }

    public PedidoStatus getStatus() {
        return status;
    }

    public void setStatus(PedidoStatus status) {
        this.status = status;
        this.ultimaAlteracao=new Date();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
    public void addProduto(Produto p){
        produtos.add(p);
    }

    public void removeProduto(int i) {
        produtos.remove(i);
    }
    
    public float getTotal(){
        float total=0f;
        for (Produto p:produtos){
            total+=p.getPreco();
        }
        return total;
    }
    
    public float getTotalFinal(){
        float total=this.getTotal();        
        return formaPagamento.precoFinal(total);
    }
    
    public static FormaPagamento getClassPagamento(int i){
        FormaPagamento pagamento;
        if(i==2){
            pagamento=new PagamentoCartao();
        }else{
            pagamento=new PagamentoDinheiro();
        }
        
        return pagamento;
    }
    public static PedidoStatus getClassStatus(int i){
        PedidoStatus status;
        switch (i) {
            case 1:
                status = new PedidoAberto();
                break;
            case 2:
                status = new PedidoRecebido();
                break;
            case 3:
                status = new PedidoEmPreparacao();
                break;
            case 4:
                status = new PedidoSaiuEntrega();
                break;
            default:
                status = new PedidoCancelado();
                break;
        }
        
        return status;
    }
    
}
