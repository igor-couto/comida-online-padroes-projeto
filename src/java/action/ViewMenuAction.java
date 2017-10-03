/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import controller.Action;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Produto;
import percistence.ProdutoDAO;

/**
 *
 * @author fhnri
 */
public class ViewMenuAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Produto> produtos = ProdutoDAO.getInstance().getProdutos();
        RequestDispatcher despachar = request.getRequestDispatcher("cardapio.jsp");
        String list="";
        for(Produto produto:produtos){
            list+="<form action='FrontController?action=AddItem&id="+produto.getId()+"' method='post'><li>"+produto.getNome()+" - "+produto.getPreco()+"<input type='submit' value='+'/></li>";
        }
        request.setAttribute("list", list);
        try {
            despachar.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ViewMenuAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
