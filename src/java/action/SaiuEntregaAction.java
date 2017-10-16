/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import controller.Action;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pedido;
import percistence.PedidoDAO;

/**
 *
 * @author fhnri
 */
public class SaiuEntregaAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RequestDispatcher despachar = request.getRequestDispatcher("alteracaoStatus.jsp");
        
        String id = request.getParameter("id");        
        
        if (id.equals("")) {
            
            response.sendRedirect("FrontController?action=Admin");
            
        } else {
            
            try {
                Pedido pedido = PedidoDAO.getInstance().getPedido(id);
                if(pedido!=null){
                    
                    String m = pedido.getStatus().SaiuEntrega(pedido);
                    
                    PedidoDAO.getInstance().Edit(pedido);
                    
                    request.setAttribute("message", m);
                    
                    despachar.forward(request, response);
                    
                }else{
                    response.sendRedirect("FrontController?action=Admin");
                }
            
            } catch (SQLException ex) {
            
                Logger.getLogger(SaiuEntregaAction.class.getName()).log(Level.SEVERE, null, ex);
            
            } catch (ServletException ex) {
                Logger.getLogger(SaiuEntregaAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
        
    }
    
}
