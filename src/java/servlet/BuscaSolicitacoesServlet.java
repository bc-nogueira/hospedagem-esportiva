package servlet;

import dao.ViagemDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Usuario;
import model.Viagem;

@WebServlet("/buscaSolicitacoes")
public class BuscaSolicitacoesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        
        List<Viagem> solicitacoesFeitas = new ViagemDAO().buscaFeitas(usuarioLogado);
        List<Viagem> solicitacoesRecebidas = new ViagemDAO().buscaRecebidas(usuarioLogado);
        
        session.setAttribute("solicitacoesFeitas", solicitacoesFeitas);
        session.setAttribute("solicitacoesRecebidas", solicitacoesRecebidas);
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/viagem/solicitacoes.jsp");
        dispatcher.forward(req, resp);
    }
}
