package servlet;

import dao.ViagemDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.StatusViagem;

@WebServlet("/confirmaSolicitacao")
public class ConfirmaSolicitacaoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        
        new ViagemDAO().atualizaStatusViagem(id, StatusViagem.CONFIRMADA);
        
        HttpSession session = req.getSession();
        session.setAttribute("classeAlert", "success");
        session.setAttribute("mensagemSolicitacao",
                "A solicitação foi atualizada com sucesso!");
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("buscaSolicitacoes");
        dispatcher.forward(req, resp);
    }
}
