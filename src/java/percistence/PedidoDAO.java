package percistence;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Pedido;
import model.PedidoAberto;
import model.PedidoRecebido;
import model.Produto;
import model.Usuario;


public class PedidoDAO {
    private static PedidoDAO instance = new PedidoDAO();
    
    private PedidoDAO(){}
    
    public static PedidoDAO getInstance(){
        return instance;
    }
    
    public void Save(Pedido pedido) throws SQLException{
        
        Connection conn = null;
        PreparedStatement  stmt = null;
        System.out.println("Entrou 2");
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            java.util.Date data = new java.util.Date();
            java.sql.Date dataSql = new java.sql.Date(data.getTime());
            stmt = conn.prepareStatement("INSERT INTO pedido (idUsuario,status,data) values (?, 1, ?)",Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, Integer.toString(pedido.getUsuario().getId()));
            stmt.setDate(2, dataSql);
            int idPedido = stmt.executeUpdate();
            pedido.setId(idPedido);
            
            ArrayList<Produto> produtos = (ArrayList<Produto>) pedido.getProdutos();
            if(produtos.size()>0){
                for (Produto temp : produtos) {
                    stmt = conn.prepareStatement("INSERT INTO produtosPedido (idPedido,idProduto,quantidade) values (?, ?, 1)");
                    stmt.setString(1, Integer.toString(pedido.getId()));
                    stmt.setString(2, Integer.toString(temp.getId())); 
                    stmt.executeUpdate();
                }
            }
            
        } catch(SQLException e) {
            
            throw e;
            
        } finally {
            
            closeResources(conn, stmt);
            
        }

    }
    
    public void Edit(Pedido pedido) throws SQLException{
        
        Connection conn = null;
        PreparedStatement  stmt = null;
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            stmt = conn.prepareStatement("UPDATE pedido SET idUsuario=?,status=? WHERE idPedido=?");
            stmt.setString(1, Integer.toString(pedido.getUsuario().getId()));
            stmt.setString(2, Integer.toString(pedido.getStatus().getStatusID()));
            stmt.setString(3, Integer.toString(pedido.getId()));
            stmt.executeUpdate();
            
            ArrayList<Produto> produtos = (ArrayList<Produto>) pedido.getProdutos();

            stmt = conn.prepareStatement("DELETE FROM produtosPedido WHERE idPedido=?");
            stmt.setString(1, Integer.toString(pedido.getId()));
            stmt.executeUpdate();
            
            for (Produto prod : produtos) {
                stmt = conn.prepareStatement("INSERT INTO produtosPedido (idPedido,idProduto,quantidade) values (?, ?, 1) ON DUPLICATE KEY UPDATE quantidade=1");
                stmt.setString(1, Integer.toString(pedido.getId()));
                stmt.setString(2, Integer.toString(prod.getId())); 
                stmt.executeUpdate();
            }
            
        } catch(SQLException e) {
            
            throw e;
            
        } finally {
            
            closeResources(conn, stmt);
            
        }

    }
    
    public Pedido getOpenPedido(Usuario usuario) throws SQLException{
        Connection conn = null;
        PreparedStatement  stmt = null;
        Pedido pedido = null;
        try {
            
            conn = DatabaseLocator.getInstance().getConnection();

            stmt = conn.prepareStatement("SELECT * FROM pedido WHERE idUsuario=? AND status=1");
            stmt.setString(1, Integer.toString(usuario.getId()));
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                
                //Aqui tem que criar o pedido com o status correto.
                pedido = new Pedido(rs.getInt("idPedido"), rs.getDate("data"), rs.getDate("data"), new PedidoAberto(), usuario, null);
                
                List<Produto> produtos = ProdutoDAO.getInstance().getProdutosPedido(pedido);
                
                pedido.setProdutos(produtos);
                
                System.out.println(pedido.getProdutos().size());
                
                
            }
            
        } catch(SQLException e) {
            
            throw e;
            
        } finally {
            
            closeResources(conn, stmt);
            
        }
        
        return pedido;
    }
    
    public List<Pedido> getPedidosRecebidos() throws SQLException{
        Connection conn = null;
        PreparedStatement  stmt = null;
        List<Pedido> pedidos = new ArrayList<>();
        try {
            
            conn = DatabaseLocator.getInstance().getConnection();

            stmt = conn.prepareStatement("SELECT * FROM pedido WHERE status>=2");
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Usuario usuarioPedido=UsuarioDAO.getInstance().getUsuario(Integer.parseInt(rs.getString("idUsuario")));
                
                //Aqui tem que criar o pedido com o status correto.
                Pedido pedido = new Pedido(rs.getInt("idPedido"), rs.getDate("data"), rs.getDate("data"), Pedido.getClassStatus(rs.getInt("status")),usuarioPedido, null);
                
                List<Produto> produtos = ProdutoDAO.getInstance().getProdutosPedido(pedido);
                
                pedido.setProdutos(produtos);
                
                pedidos.add(pedido);
                
            }
            
        } catch(SQLException e) {
            
            throw e;
            
        } finally {
            
            closeResources(conn, stmt);
            
        }
        
        return pedidos;
    }
    
    
    
    public void closeResources(Connection conn, Statement st){
        try {
            if(st!=null) st.close();
            if(conn!=null) conn.close();

        } catch(SQLException e) {

        }
    }
    
}
