package action;

import controller.Action;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pedido;
import model.Produto;
import model.Usuario;
import percistence.PedidoDAO;
import percistence.ProdutoDAO;
import percistence.UsuarioDAO;

public class AddItemAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        String id = request.getParameter("id");        
        
        if (id.equals("")) {
            
            response.sendRedirect("FrontController?action=ViewMenu");
            
        } else {
            
            Produto produto = null;            
            try {
                
                //Pegar id do usuario
                Usuario usuario = UsuarioDAO.getInstance().getUsuario(1);
                
                produto = ProdutoDAO.getInstance().getProduto(Integer.parseInt(id));
                
                Pedido pedido = PedidoDAO.getInstance().getOpenPedido(usuario);
                
                if(pedido!=null){
                    
                    pedido.addProduto(produto);
                    PedidoDAO.getInstance().Edit(pedido);
                    
                }else{

                    List<Produto> produtos = new ArrayList<Produto>();
                    produtos.add(produto);
                    pedido = new Pedido(usuario, produtos);
                    PedidoDAO.getInstance().Save(pedido);
                    
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(LoginAction.class.getName()).log(Level.SEVERE, null, ex);
            }
            

                response.sendRedirect("itemAdicionado.jsp");
        }
    }
    
}
