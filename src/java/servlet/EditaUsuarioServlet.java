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
        
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        usuario = this.populaUsuario(req, usuario.getEmail());
        
        try {
            new UsuarioDAO().atualiza(usuario);
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
        if(req.getParameter("localMoradia") == null || req.getParameter("localMoradia").equals("")) {
            parametrosVazios.add("Local de Moradia");
        }
        if(req.getParameter("esporteFavorito") == null || req.getParameter("esporteFavorito").equals("")) {
            parametrosVazios.add("Esporte Favorito");
        }
        if(req.getParameter("email") == null || req.getParameter("email").equals("")) {
            parametrosVazios.add("E-mail");
        }
        return parametrosVazios;
    }
    
    private Usuario populaUsuario(HttpServletRequest req, String emailOriginal) {
        Usuario usuario = new Usuario();
        usuario.setNome(req.getParameter("nome"));
        usuario.setLocalMoradia(req.getParameter("localMoradia"));
        usuario.setEsporteFavorito(req.getParameter("esporteFavorito"));
        
        if(req.getParameter("dispostoReceber") != null) {
            usuario.setDispostoReceber(Boolean.TRUE);
            usuario.setQuantReceber(Integer.parseInt(req.getParameter("quantReceber")));
        } else {
            usuario.setDispostoReceber(Boolean.FALSE);
        }
        
        if(!req.getParameter("email").equals(emailOriginal))
            usuario.setEmail(req.getParameter("email"));
        
        return usuario;
    }
}
