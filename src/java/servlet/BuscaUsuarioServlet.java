package servlet;

import com.google.gson.Gson;
import dao.UsuarioDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Usuario;

@WebServlet("/buscaUsuario")
public class BuscaUsuarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String texto = req.getParameter("texto");
        
        List<Usuario> usuarios = new UsuarioDAO().buscaPorNomeEEmailLike(texto, texto);
        
        String json = new Gson().toJson(usuarios);
        
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }
    
}
