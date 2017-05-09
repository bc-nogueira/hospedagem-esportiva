package servlet;

import dao.AvaliacaoDAO;
import dao.UsuarioDAO;
import dao.ViagemDAO;
import java.io.IOException;
import java.util.Calendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Avaliacao;
import model.TipoAvaliacao;
import model.Usuario;
import model.Viagem;

@WebServlet("/avaliarUsuario")
public class AvaliaUsuarioServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        Integer idUsuarioAvaliado = Integer.parseInt(req.getParameter("idUsuario"));
        Double nota = Double.parseDouble(req.getParameter("nota"));
        String descricao = req.getParameter("descricao");
        
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(nota);
        avaliacao.setDescricao(descricao);
        avaliacao.setDataAvaliacao(Calendar.getInstance());
        avaliacao.setTipoAvaliacao(TipoAvaliacao.AMIGO);
        avaliacao.setAvaliado(new UsuarioDAO().buscaPorId(idUsuarioAvaliado));
        
        HttpSession session = req.getSession();
        
        avaliacao.setAvaliador((Usuario) session.getAttribute("usuarioLogado"));
        
        new AvaliacaoDAO().salvaAvaliacao(avaliacao);
        
        session.setAttribute("mensagem", "A avaliação foi salva com sucesso!");
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/usuario/index.jsp");
        dispatcher.forward(req, resp);
    }
}
