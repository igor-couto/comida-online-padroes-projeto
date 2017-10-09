/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import model.PedidoStatus;
import percistence.PedidoDAO;

/**
 *
 * @author fhnri
 */
public class AlteraStatusAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        String id = request.getParameter("id");        
        
        if (id.equals("")) {
            
            response.sendRedirect("FrontController?action=Admin");
            
        } else {
            
            try {
                
                Pedido pedido = PedidoDAO.getInstance().getPedido(id);
                
                if(pedido!=null){
                    
                    int status = pedido.getStatus().getStatusID()<5 ? pedido.getStatus().getStatusID()+1 : 5;
                    
                    pedido.setStatus(Pedido.getClassStatus(status));
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
