/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import controller.Action;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Promocao;
import model.Usuario;
import percistence.UsuarioDAO;

/**
 *
 * @author fhnri
 */
public class NovaPromocaoAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        RequestDispatcher despachar = request.getRequestDispatcher("notificados.jsp");
        
        String desconto = request.getParameter("desconto");
        
        Promocao novaPromocao = new Promocao();
        String list="";
        try {
            
            List<Usuario> usuarios=UsuarioDAO.getInstance().getUsuariosNotificacao(novaPromocao);
            
            novaPromocao.setDesconto(Integer.parseInt(desconto));
            
            for(Usuario usuario:usuarios){
                list+="<li>"+usuario.getNome()+"</li>";
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(NovaPromocaoAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("list", list);
        try {
            despachar.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(NovaPromocaoAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
