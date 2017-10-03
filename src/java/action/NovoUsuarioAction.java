package action;

import controller.Action;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NovoUsuarioAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");        
        String senha = request.getParameter("senha");
        
        if (email.equals("") || senha.equals("") || nome.equals("")) {
            RequestDispatcher despachar = request.getRequestDispatcher("cadastro_falha.jsp");
            request.setAttribute("erro", "Preencha todos os campos acima");
            try {
                despachar.forward(request, response);
            } catch (ServletException ex) {
                Logger.getLogger(NovoUsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            
        }
    }
    
}
