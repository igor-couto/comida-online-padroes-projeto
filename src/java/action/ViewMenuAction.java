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
import model.Produto;
import model.Usuario;
import percistence.PedidoDAO;
import percistence.ProdutoDAO;
import percistence.UsuarioDAO;

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
            list+="<li><form action='FrontController?action=AddItem&id="+produto.getId()+"' method='post'>"+produto.getNome()+" - "+produto.getPreco()+"<input type='submit' value='+'/></form></li>";
        }
        request.setAttribute("list", list);
        String cart="";
        List<Produto> carrinho;
        try {
            
            carrinho = ProdutoDAO.getInstance().getProdutosPedido(PedidoDAO.getInstance().getOpenPedido(UsuarioDAO.getInstance().getUsuario(1)));
            for(Produto produto:carrinho){
                cart+="<form action='FrontController?action=RemoveItem&id="+produto.getId()+"' method='post'><li>"+produto.getNome()+" - "+produto.getPreco()+"<input type='submit' value='-'/></li>";
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ViewMenuAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("cart", cart);
        try {
            despachar.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ViewMenuAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
