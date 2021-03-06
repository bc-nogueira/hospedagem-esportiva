package servlet;

import dao.UsuarioDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.persistence.PersistenceException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Usuario;

@WebServlet("/editaUsuario")
public class EditaUsuarioServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        
        ArrayList<String> parametrosVazios = verificaParametros(req);
        if(!parametrosVazios.isEmpty()) {
            session.setAttribute("mensagem", "Os seguintes campos estão vazios:");
            session.setAttribute("parametrosVazios", parametrosVazios);
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/usuario/edit.jsp");
            dispatcher.forward(req, resp);
        }
        
        Usuario usuarioAntigo = (Usuario) session.getAttribute("usuarioLogado");
        
        Usuario usuario = new Usuario();
        try {
            usuario = new UsuarioDAO().atualiza(usuarioAntigo, req);
        } catch(PersistenceException ex) {
            session.setAttribute("mensagem", "E-mail já está sendo utilizado.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/usuario/edit.jsp");
            dispatcher.forward(req, resp);
        }
        
        session.setAttribute("mensagem", "Atualização realizada com sucesso.");
        session.setAttribute("usuarioLogado", usuario);
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/usuario/index.jsp");
        dispatcher.forward(req, resp);
    }
    
    private ArrayList<String> verificaParametros(HttpServletRequest req) {
        ArrayList<String> parametrosVazios = new ArrayList<>();
        if(req.getParameter("nome") == null || req.getParameter("nome").equals("")) {
            parametrosVazios.add("Nome");
        }
        if(req.getParameter("sexo") == null || req.getParameter("sexo").equals("")) {
            parametrosVazios.add("Sexo");
        }
        if(req.getParameter("cidadeMoradia") == null || req.getParameter("cidadeMoradia").equals("")) {
            parametrosVazios.add("Cidade de Moradia");
        }
        if(req.getParameter("paisMoradia") == null || req.getParameter("paisMoradia").equals("")) {
            parametrosVazios.add("País de Moradia");
        }
        if(req.getParameter("esporteFavorito") == null || req.getParameter("esporteFavorito").equals("")) {
            parametrosVazios.add("Esporte Favorito");
        }
        if(req.getParameter("email") == null || req.getParameter("email").equals("")) {
            parametrosVazios.add("E-mail");
        }
        return parametrosVazios;
    }
    
}
