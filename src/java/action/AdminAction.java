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
import percistence.PedidoDAO;

/**
 *
 * @author fhnri
 */
public class AdminAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        RequestDispatcher despachar = request.getRequestDispatcher("administrativo.jsp");
        
        String list="";
        
        try {
            
            List<Pedido> pedidos = PedidoDAO.getInstance().getPedidosRecebidos();
            
            for(Pedido pedido:pedidos){
                
                String listProdutos="";
                
                for(Produto produto:pedido.getProdutos()){
                
                    listProdutos+="<li>"+produto.getNome()+" - "+produto.getPreco()+"</li>";
                
                }
                
                String nextState = ("<form action='FrontController'>"
                        + "<select name='action'>"
                        + "<option value='Aberto'>Aberto</option>"
                        + "<option value='Recebido'>Recebido</option>"
                        + "<option value='EmPreparacao'>Em preparação</option>"
                        + "<option value='SaiuEntrega'>Saiu para entrega</option>"
                        + "<option value='Cancelado'>Cancelado</option>"
                        + "</select>"
                        + "<input type='submit' value='Alterar'/>"
                        + "<input type='hidden' value='"+pedido.getId()+"' name='id'>"
                        + "</form>");
                
                list+="<li>Pedido número "+pedido.getId()+" - "+pedido.getDateString()+" ("+pedido.getStatus().getStatus()
                        +")<div>Cliente: "+pedido.getUsuario().getNome()+"</div>"
                        +"<ul>"+listProdutos+"</ul><label>Total: "+pedido.getTotal()+" - "+pedido.getFormaPagamento().getNome()+"</label>"
                        +"<div>Preço com desconto:"+pedido.getTotalFinal()+"</div>"
                        + nextState
                        //+ "<form action='FrontController?action=CancelaPedido&id="+pedido.getId()+"' method='post'><input type='submit' value='Cancelar pedido'/></form>"
                        + "</li>";
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("list", "<ul>"+list+"</ul>");
        
        try {
        
            despachar.forward(request, response);
        
        } catch (ServletException ex) {
        
            Logger.getLogger(ViewMenuAction.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        
    }
    
}
