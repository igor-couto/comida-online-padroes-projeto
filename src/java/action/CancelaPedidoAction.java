package action;

import controller.Action;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pedido;
import model.PedidoCancelado;
import percistence.PedidoDAO;

public class CancelaPedidoAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");        
        
        if (id.equals("")) {
            
            response.sendRedirect("FrontController?action=Admin");
            
        } else {
            
            try {
                Pedido pedido = PedidoDAO.getInstance().getPedido(id);
                
                if(pedido!=null){
                    
                    pedido.setStatus(new PedidoCancelado());
                    
                    PedidoDAO.getInstance().Edit(pedido);
                    
                    response.sendRedirect("pedidoAlterado.jsp");
                    
                }else{
                    response.sendRedirect("FrontController?action=Admin");
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(CancelaPedidoAction.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
}
