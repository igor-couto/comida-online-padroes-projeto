package model;

import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import percistence.UsuarioDAO;

public class Usuario implements Observer{
    protected int id;
    protected String nome;
    protected String email;
    protected String senha;
    protected boolean recebeNotificacao;
    protected int desconto;
    private Observable promocao;
    public boolean isRecebeNotificacao() {
        return recebeNotificacao;
    }

    public void setRecebeNotificacao(boolean recebeNotificacao) {
        this.recebeNotificacao = recebeNotificacao;
    }
    
    public Usuario(String nome, String email, String senha, boolean recebeNotificacao) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.recebeNotificacao = recebeNotificacao;
    }

    public Usuario(int id, String nome, String email, boolean recebeNotificacao, int desconto) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.desconto=desconto;
        this.recebeNotificacao = recebeNotificacao;
    }
    
    public Usuario(int id, String nome, String email, boolean recebeNotificacao,int desconto,Observable promocao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.promocao=promocao;
        this.desconto=desconto;
        this.recebeNotificacao = recebeNotificacao;
        promocao.addObserver(this);
    }

    public Usuario() {
        
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getDesconto() {
        return desconto;
    }

    public void setDesconto(int desconto) {
        this.desconto = desconto;
    }
    
    @Override
    public void update(Observable promocaoSubject, Object arg1){
        System.out.println("Entrou na notificacao");
        if(promocaoSubject instanceof Promocao){
            Promocao novoDesconto = (Promocao) promocaoSubject;
            this.setDesconto(novoDesconto.getDesconto());
            try {
                UsuarioDAO.getInstance().Edit(Integer.toString(id), nome, email, recebeNotificacao,desconto);
            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
