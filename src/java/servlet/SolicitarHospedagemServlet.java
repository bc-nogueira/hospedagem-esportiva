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
        HttpSession session = req.getSession();
        Viagem viagem = (Viagem) session.getAttribute("viagem");
        
        if(viagem.getDataInicio().after(viagem.getDataFim())) {
            session.setAttribute("mensagemErroViagem",
                    "A data de chegada é posterior a data de saída!");

            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/viagem/solicitar.jsp");
            dispatcher.forward(req, resp);
        }
        
        Integer idUsuarioAnfitriao = Integer.parseInt(req.getParameter("id"));
        
        viagem.setAnfitriao(new UsuarioDAO().buscaPorId(idUsuarioAnfitriao));
        viagem.setStatusViagem(StatusViagem.PENDENTE);
        
        new ViagemDAO().cadastra(viagem);
        
        session.setAttribute("classeAlert", "success");
        session.setAttribute("mensagemSolicitacao",
                "A solicitação foi criada com sucesso!");
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("buscaSolicitacoes");
        dispatcher.forward(req, resp);
    }
}
