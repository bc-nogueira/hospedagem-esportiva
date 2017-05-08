package servlet;

import dao.UsuarioDAO;
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
import model.Viagem;

@WebServlet("/solicitarHospedagem")
public class SolicitarHospedagemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        Integer idUsuarioAnfitriao = Integer.parseInt(req.getParameter("id"));
        
        HttpSession session = req.getSession();
        Viagem viagem = (Viagem) session.getAttribute("viagem");
        viagem.setAnfitriao(new UsuarioDAO().buscaPorId(idUsuarioAnfitriao));
        viagem.setStatusViagem(StatusViagem.PENDENTE);
        
        new ViagemDAO().cadastra(viagem);
        
        session.setAttribute("mensagemCriacaoSolicitacao",
                "A solicitação foi criada com sucesso!");
        
        //Buscar as solicitações antes
        RequestDispatcher dispatcher = req.getRequestDispatcher("buscaSolicitacoes");
        dispatcher.forward(req, resp);
    }
}
