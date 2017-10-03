package action;

import controller.Action;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NovoUsuarioAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");        
        String senha = request.getParameter("senha");
        
        if (email.equals("") || senha.equals("") || nome.equals("")) {
            response.sendRedirect("cadastro_falha.jsp");
        } else {
            
        }
    }
    
}
