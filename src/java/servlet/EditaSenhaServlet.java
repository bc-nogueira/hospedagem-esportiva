package servlet;

import dao.UsuarioDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Usuario;

@WebServlet("/editaSenha")
public class EditaSenhaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        
        ArrayList<String> parametrosVazios = verificaParametros(req);
        if(!parametrosVazios.isEmpty()) {
            session.setAttribute("mensagem", "Os seguintes campos estão vazios:");
            session.setAttribute("parametrosVazios", parametrosVazios);
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/usuario/editSenha.jsp");
            dispatcher.forward(req, resp);
        }
        
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        
        if(!usuario.getSenha().equals(req.getParameter("senhaAntiga"))) {
            session.setAttribute("mensagem", "A senha antiga informada não está correta.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/usuario/editSenha.jsp");
            dispatcher.forward(req, resp);
        }
        
        usuario.setSenha(req.getParameter("senhaNova"));
        
        new UsuarioDAO().atualizaSenha(usuario);
        
        session.setAttribute("mensagem", "Atualização de senha realizada com sucesso.");
        session.setAttribute("usuarioLogado", usuario);
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/usuario/index.jsp");
        dispatcher.forward(req, resp);
    }
    
    private ArrayList<String> verificaParametros(HttpServletRequest req) {
        ArrayList<String> parametrosVazios = new ArrayList<>();
        if(req.getParameter("senhaAntiga") == null || req.getParameter("senhaAntiga").equals("")) {
            parametrosVazios.add("Senha antiga");
        }
        if(req.getParameter("senhaNova") == null || req.getParameter("senhaNova").equals("")) {
            parametrosVazios.add("Nova senha");
        }
        return parametrosVazios;
    }
    
}
