package model;

public class Usuario {
    protected int id;
    protected String nome;
    protected String email;
    protected String senha;
    protected boolean recebeNotificacao;

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

    public Usuario(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
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

}
