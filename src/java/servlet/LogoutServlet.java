package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
//        req.getSession().removeAttribute("usuarioLogado");
        // Como tem muitos dados salvos na sessão, melhor destruir tudo ao sair.
        req.getSession().invalidate();
        
        HttpSession session = req.getSession();
        session.setAttribute("mensagemLogout", "Logout realizado com sucesso.");
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);
    }
    
}
