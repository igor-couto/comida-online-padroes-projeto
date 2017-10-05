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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pedido;
import model.Produto;
import model.Usuario;
import percistence.PedidoDAO;
import percistence.ProdutoDAO;
import percistence.UsuarioDAO;

/**
 *
 * @author 11944413600
 */
public class RemoveItemAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        
        String id = request.getParameter("id");        
        
        Cookie[] cookies = request.getCookies();
        
        int idUsuario=0;
        
        for(Cookie cookie:cookies){
            
            if(cookie.getName().equals("usuario")){
                idUsuario=Integer.parseInt(cookie.getValue());
            }
            
        }
        
        if (id.equals("")) {
            
            response.sendRedirect("FrontController?action=ViewMenu");
            
        } else {
            
            Produto produto = null;            
            try {
                
                //Pegar id do usuario
                Usuario usuario = UsuarioDAO.getInstance().getUsuario(idUsuario);
                
                Pedido pedido = PedidoDAO.getInstance().getOpenPedido(usuario);
                
                if(pedido!=null){
                    
                    pedido.removeProduto(Integer.parseInt(id));
                    PedidoDAO.getInstance().Edit(pedido);
                    
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(LoginAction.class.getName()).log(Level.SEVERE, null, ex);
            }
            

            response.sendRedirect("itemRemovido.jsp");
            
        }
    }
    
}
