package servlet;

import dao.AvaliacaoDAO;
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

@WebServlet("/avaliarViagem")
public class AvaliaViagemServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        Integer idViagem = Integer.parseInt(req.getParameter("idViagem"));
        Double nota = Double.parseDouble(req.getParameter("nota"));
        String descricao = req.getParameter("descricao");
        
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(nota);
        avaliacao.setDescricao(descricao);
        avaliacao.setDataAvaliacao(Calendar.getInstance());
        
        HttpSession session = req.getSession();
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        
        avaliacao.setAvaliador(usuarioLogado);
        
        Viagem viagem = new ViagemDAO().buscaPorId(idViagem);
        avaliacao.setViagemAvaliada(viagem);
        
        if(usuarioLogado.getId().equals(viagem.getAnfitriao().getId())) {
            avaliacao.setTipoAvaliacao(TipoAvaliacao.HOSPEDE);
            avaliacao.setAvaliado(viagem.getHospede());
        } else {
            avaliacao.setTipoAvaliacao(TipoAvaliacao.ANFITRIAO);
            avaliacao.setAvaliado(viagem.getAnfitriao());
        }
        
        new AvaliacaoDAO().salvaAvaliacao(avaliacao);
        
        session.setAttribute("mensagem", "A avaliação foi salva com sucesso!");
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/usuario/index.jsp");
        dispatcher.forward(req, resp);
    }
}
