package model;

import java.util.Date;
import java.util.List;

public class Pedido {
    protected int id;
    protected Date date;
    protected Date ultimaAlteracao;
    protected PedidoStatus status;//1 - Aberto, 2 - Recebido, 3 - Em preparacao,4 - Saiu pra entrega, 0 - Cancelado
    protected Usuario usuario;
    protected List<Produto> produtos;

    public Pedido(int id, Date date, Date ultimaAlteracao, PedidoStatus status, Usuario usuario, List<Produto> produtos) {
        this.id = id;
        this.date = date;
        this.ultimaAlteracao = ultimaAlteracao;
        this.status = status;
        this.usuario = usuario;
        this.produtos = produtos;
    }

    public Pedido(Usuario usuario, List<Produto> produtos) {
        this.usuario = usuario;
        this.produtos = produtos;
        date=new Date();
        ultimaAlteracao=new Date();
        status=new PedidoRecebido();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
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
    
    
}
