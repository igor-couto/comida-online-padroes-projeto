package percistence;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Pedido;
import model.Produto;
import model.Usuario;


public class ProdutoDAO {
    private static ProdutoDAO instance = new ProdutoDAO();
    
    private ProdutoDAO(){}
    
    public static ProdutoDAO getInstance(){
        return instance;
    }
    
    public List<Produto> getProdutos(){
        List<Produto> produtos = new ArrayList<Produto>();
        Connection conn = null;
        PreparedStatement  stmt = null;
        conn = DatabaseLocator.getInstance().getConnection();

        try {
            stmt = conn.prepareStatement("SELECT * FROM produto");            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Produto p = new Produto(rs.getInt("idProduto"),rs.getString("nome"),rs.getFloat("preco"),rs.getString("descricao"));
                produtos.add(p);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return produtos;
    }
    
    public List<Produto> getProdutosPedido(Pedido pedido){
        List<Produto> produtos = new ArrayList<Produto>();
        Connection conn = null;
        PreparedStatement  stmt = null;
        conn = DatabaseLocator.getInstance().getConnection();

        try {
            stmt = conn.prepareStatement("SELECT produto.* FROM produtosPedido LEFT JOIN produto ON produto.idProduto=produtosPedido.idProduto WHERE idPedido=?");            
            stmt.setString(1, Integer.toString(pedido.getId()));
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Produto p = new Produto(rs.getInt("idProduto"),rs.getString("nome"),rs.getFloat("preco"),rs.getString("descricao"));
                produtos.add(p);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return produtos;
    }
    
    public Produto getProduto(Integer id){
        
        Connection conn = null;
        PreparedStatement  stmt = null;
        conn = DatabaseLocator.getInstance().getConnection();
        
        Produto produto = null;

        try {
            stmt = conn.prepareStatement("SELECT * FROM produto WHERE idProduto=?");            
            stmt.setString(1, Integer.toString(id));
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Produto p = new Produto(rs.getInt("idProduto"),rs.getString("nome"),rs.getFloat("preco"),rs.getString("descricao"));
                produto = p;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return produto;
    }
    
    public void closeResources(Connection conn, Statement st){
        try {
            if(st!=null) st.close();
            if(conn!=null) conn.close();

        } catch(SQLException e) {

        }
    }
    
}
