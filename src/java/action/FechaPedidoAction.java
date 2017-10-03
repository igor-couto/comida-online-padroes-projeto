package action;

import controller.Action;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pedido;
import model.PedidoRecebido;
import model.Usuario;
import percistence.PedidoDAO;
import percistence.UsuarioDAO;

public class FechaPedidoAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Usuario usuario = UsuarioDAO.getInstance().getUsuario(1);

            if(usuario!=null){
            
                Pedido pedido= PedidoDAO.getInstance().getOpenPedido(usuario);
                System.out.println(pedido.getStatus().getStatusID());
                
                if(pedido!=null){
                    System.out.println("Entrou: "+pedido.getStatus().getStatusID());
                    pedido.setStatus(new PedidoRecebido());
                    PedidoDAO.getInstance().Edit(pedido);
                }
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FechaPedidoAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.sendRedirect("FrontController?action=ViewMenu");
        
    }
    
}
