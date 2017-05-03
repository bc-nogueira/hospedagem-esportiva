package servlet;

import dao.UsuarioDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Usuario;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        Usuario usuario = 
                new UsuarioDAO().buscaPorEmailSenha(req.getParameter("email"), req.getParameter("senha"));
        
        HttpSession session = req.getSession();
        
        String pagina;
        if(usuario != null) {
            session.setAttribute("mensagem", "Login realizado com sucesso.");
            session.setAttribute("usuarioLogado", usuario);
            pagina = "jsp/usuario/index.jsp";
        } else {
            session.setAttribute("mensagem", "E-mail ou senha incorretos.");
            pagina = "jsp/usuario/login.jsp";
        }
        
        RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
        dispatcher.forward(req, resp);
        
    }
    
}
