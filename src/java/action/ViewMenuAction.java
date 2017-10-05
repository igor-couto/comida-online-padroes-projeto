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
import model.Pedido;
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
            Usuario usuario = UsuarioDAO.getInstance().getUsuario(1);
            if(usuario!=null){
                
                Pedido pedido = PedidoDAO.getInstance().getOpenPedido(usuario);
                
                String mensagemDesconto = "";
                
                if(usuario.getDesconto()>0){
                    
                    mensagemDesconto="Parabens você ganhou um desconto de "+usuario.getDesconto()+"%";
                    
                }
                System.out.println(mensagemDesconto);
                request.setAttribute("mensagemDesconto", mensagemDesconto);
                
                if(pedido!=null){
                
                    carrinho = ProdutoDAO.getInstance().getProdutosPedido(pedido);
                    int i=0;
                    for(Produto produto:carrinho){

                        cart+="<li><form action='FrontController?action=RemoveItem&id="+i+"' method='post'>"+produto.getNome()+" - "+produto.getPreco()+"<input type='submit' value='-'/></form></li>";
                        i++;
                    }
                }
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
