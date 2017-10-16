package action;

import controller.Action;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pedido;
import percistence.PedidoDAO;


public class ReverterPedidoAction  implements Action{

    // Implementar MEMENTO
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");        
        
        if (id.equals("")) {
            
            response.sendRedirect("FrontController?action=Admin");
            
        } else {
            
            try {
                
                Pedido pedido = PedidoDAO.getInstance().getPedido(id);
                
                if(pedido != null){
                    
                    
                    int status = 0;
                    // IMPLEMENTAR AQUI A RECUPERACAO O ULTIMO ESTADO
                    // int status = pedido.restoreFromMemento( pedido.getEstadoSalvos().get(0) );   ?????  
                    
                    pedido.setStatus(Pedido.getClassStatus(status));
                    
                    pedido.guardarPedido();
                    
                    PedidoDAO.getInstance().Edit(pedido);
                    
                    response.sendRedirect("pedidoAlterado.jsp");
                    
                }else{
                    response.sendRedirect("FrontController?action=Admin");
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(LoginAction.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

}
