package percistence;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Promocao;
import model.Usuario;


public class UsuarioDAO {
    private static UsuarioDAO instance = new UsuarioDAO();
    
    private UsuarioDAO(){}
    
    public static UsuarioDAO getInstance(){
        return instance;
    }
    
    public Usuario Login(String email, String senha) throws SQLException{
        Usuario usuario=null;
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            stmt = conn.prepareStatement("SELECT idUsuario,nome, email, senha,notificacao,desconto FROM usuario WHERE email = ? AND senha = ?");
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
            
                usuario = new Usuario(rs.getInt("idUsuario"),rs.getString("nome"),rs.getString("email"),(rs.getInt("notificacao")>0),rs.getInt("desconto"));
                
            }
        } catch(SQLException e) { 
            throw e;
        } finally {
            closeResources(conn, stmt);
        }
        
        return usuario; 
    }
    
    public void Save(Usuario usuario) throws SQLException{
        
        Connection conn = null;
        PreparedStatement  stmt = null;
        
        try {
            conn = DatabaseLocator.getInstance().getConnection();

            stmt = conn.prepareStatement("INSERT INTO usuario (nome, email, senha, notificacao,desconto) values (?, ?, ?, ?,0)",Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());   
            stmt.setString(3, usuario.getSenha()); 
            stmt.setInt(4, (usuario.isRecebeNotificacao()) ? 1 : 0); 
            int id = stmt.executeUpdate();
            
            
            ResultSet genKeys = stmt.getGeneratedKeys();
            genKeys.next();
            
            int idUsuario = genKeys.getInt(1);            
            usuario.setId(id);
            
        } catch(SQLException e) {    
            throw e;
        } finally {
            closeResources(conn, stmt);
        }

    }
    
    public void Edit(String id,String nome,String email,boolean notificacao,int desconto) throws SQLException{
        
        Connection conn = null;
        PreparedStatement  stmt = null;
        
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            System.out.println("Desconto:"+desconto);

            stmt = conn.prepareStatement("UPDATE usuario SET nome=?,email=?,notificacao=?,desconto=? WHERE idUsuario=?");
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setInt(3, (notificacao) ? 1 : 0); 
            stmt.setInt(4, desconto);
            stmt.setString(5, id);            
            stmt.executeUpdate();
            
        } catch(SQLException e) {
            
            throw e;
            
        } finally {
            
            closeResources(conn, stmt);
            
        }

    }
    
    public Usuario getUsuario(int id) throws SQLException{
        Connection conn = null;
        PreparedStatement  stmt = null;
        Usuario usuario =null;
        try {
            
            conn = DatabaseLocator.getInstance().getConnection();

            stmt = conn.prepareStatement("SELECT * FROM usuario WHERE idUsuario=?");
            stmt.setString(1, Integer.toString(id));
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
            
                usuario = new Usuario(rs.getInt("idUsuario"),rs.getString("nome"),rs.getString("email"), (rs.getInt("notificacao")>0),rs.getInt("desconto"));
                
            }
            
        } catch(SQLException e) {
            
            throw e;
            
        } finally {
            
            closeResources(conn, stmt);
            
        }
        
        return usuario;
        
    }
    
    public List<Usuario> getUsuariosNotificacao(Promocao promocao) throws SQLException{
        Connection conn = null;
        PreparedStatement  stmt = null;
        List<Usuario> usuarios = new ArrayList<>();
        try {
            
            conn = DatabaseLocator.getInstance().getConnection();

            stmt = conn.prepareStatement("SELECT * FROM usuario WHERE notificacao=1");

            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){

                usuarios.add(new Usuario(rs.getInt("idUsuario"),rs.getString("nome"),rs.getString("email"), (rs.getInt("notificacao")>0),rs.getInt("desconto"),promocao));
                
            }
            
        } catch(SQLException e) {
            
            throw e;
            
        } finally {
            
            closeResources(conn, stmt);
            
        }
        
        return usuarios;
        
    }
    
    public void Delete(int id) throws SQLException{
        Connection conn = null;
        PreparedStatement  stmt = null;
        try {
            
            conn = DatabaseLocator.getInstance().getConnection();

            stmt = conn.prepareStatement("DELETE FROM usuario WHERE idUsuario=?");
            
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
            
        } catch(SQLException e) {
            
            throw e;
            
        } finally {
            
            closeResources(conn, stmt);
            
        }

    }
    
    public void closeResources(Connection conn, Statement st){
        try {
            if(st!=null) st.close();
            if(conn!=null) conn.close();

        } catch(SQLException e) {

        }
    }
    
}
