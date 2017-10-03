package action;

import controller.Action;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Usuario;
import percistence.UsuarioDAO;

public class LoginAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");        
        String senha = request.getParameter("senha");
        if (email.equals("") || senha.equals("")) {
            response.sendRedirect("login_falha.jsp");
        } else {
            
            Usuario usuario = null;
            
            try {
                usuario = UsuarioDAO.getInstance().Login(email,senha);
            } catch (SQLException ex) {
                Logger.getLogger(LoginAction.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(usuario == null) {
                response.sendRedirect("login_falha.jsp");
            }else {
                response.sendRedirect("FrontController?action=ViewMenu");
            }
        }
    }

}