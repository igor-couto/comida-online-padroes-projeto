package action;

import controller.Action;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.PagamentoCartao;
import model.PagamentoDinheiro;
import model.Pedido;
import model.PedidoRecebido;
import model.Usuario;
import percistence.PedidoDAO;
import percistence.UsuarioDAO;

public class FechaPedidoAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Cookie[] cookies = request.getCookies();
        
            int idUsuario=0;

            for(Cookie cookie:cookies){

                if(cookie.getName().equals("usuario")){
                    idUsuario=Integer.parseInt(cookie.getValue());
                }

            }
            
            Usuario usuario = UsuarioDAO.getInstance().getUsuario(idUsuario);
            
            if(usuario!=null){
                
                Pedido pedido= PedidoDAO.getInstance().getOpenPedido(usuario);
                
                if(pedido!=null){
                    
                    String pagamento = request.getParameter("formaPagamento");
                    
                    if(pagamento.equals("dinheiro")){
                        
                        pedido.setFormaPagamento(new PagamentoDinheiro());
                        
                    }else{
                        
                        pedido.setFormaPagamento(new PagamentoCartao());
                        
                    }
                    
                    pedido.setStatus(new PedidoRecebido());
                    
                    PedidoDAO.getInstance().Edit(pedido);
                }
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FechaPedidoAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.sendRedirect("FrontController?action=ViewMenu");
        
    }
    
}
