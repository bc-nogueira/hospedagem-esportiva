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

@WebServlet("/novoUsuario")
public class NovoUsuarioServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        
        ArrayList<String> parametrosVazios = verificaParametros(req);
        if(!parametrosVazios.isEmpty()) {
            session.setAttribute("mensagem", "Os seguintes campos estão vazios:");
            session.setAttribute("parametrosVazios", parametrosVazios);
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/usuario/cadastro.jsp");
            dispatcher.forward(req, resp);
        }
        
        Usuario usuario = this.populaUsuario(req);
        
        try {
            new UsuarioDAO().cadastra(usuario);
        } catch(PersistenceException ex) {
            session.setAttribute("mensagem", "E-mail já está sendo utilizado.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/usuario/cadastro.jsp");
            dispatcher.forward(req, resp);
        }
        
        session.setAttribute("mensagem", "Cadastro realizado com sucesso.");
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
        if(req.getParameter("senha") == null || req.getParameter("senha").equals("")) {
            parametrosVazios.add("Senha");
        }
        return parametrosVazios;
    }
    
    private Usuario populaUsuario(HttpServletRequest req) {
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
        usuario.setEmail(req.getParameter("email"));
        usuario.setSenha(req.getParameter("senha"));
        return usuario;
    }
    
}
