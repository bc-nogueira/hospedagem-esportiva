package servlet;

import dao.AvaliacaoDAO;
import dao.UsuarioDAO;
import java.io.IOException;
import java.util.List;
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
import service.AvaliacaoService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    AvaliacaoService avaliacaoService = new AvaliacaoService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        Usuario usuario = 
                new UsuarioDAO().buscaPorEmailESenha(req.getParameter("email"), req.getParameter("senha"));
        
        HttpSession session = req.getSession();
        
        String pagina;
        if(usuario != null) {
            List<Avaliacao> recebidasAmigos = 
                    new AvaliacaoDAO().buscaRecebidaPorUsuarioETipo(usuario, TipoAvaliacao.AMIGO);
            List<Avaliacao> recebidasHospede = 
                    new AvaliacaoDAO().buscaRecebidaPorUsuarioETipo(usuario, TipoAvaliacao.HOSPEDE);
            List<Avaliacao> recebidasAnfitriao = 
                    new AvaliacaoDAO().buscaRecebidaPorUsuarioETipo(usuario, TipoAvaliacao.ANFITRIAO);
            List<Avaliacao> recebidasLevaEsporte = 
                    new AvaliacaoDAO().buscaRecebidaPorUsuarioETipo(usuario, TipoAvaliacao.LEVA_SAIDA_ESPORTE);
            List<Avaliacao> recebidasParticipaEsporte = 
                    new AvaliacaoDAO().buscaRecebidaPorUsuarioETipo(usuario, TipoAvaliacao.PARTICIPA_SAIDA_ESPORTE);
            
            session.setAttribute("mensagem", "Login realizado com sucesso.");
            session.setAttribute("usuarioLogado", usuario);
            session.setAttribute("recebidasAmigos", recebidasAmigos);
            session.setAttribute("notaMediaAmigos", avaliacaoService.calculaNotaMedia(recebidasAmigos));
            session.setAttribute("recebidasHospede", recebidasHospede);
            session.setAttribute("notaMediaHospede", avaliacaoService.calculaNotaMedia(recebidasHospede));
            session.setAttribute("recebidasAnfitriao", recebidasAnfitriao);
            session.setAttribute("notaMediaAnfitriao", avaliacaoService.calculaNotaMedia(recebidasAnfitriao));            
            session.setAttribute("recebidasLevaEsporte", recebidasLevaEsporte);
            session.setAttribute("notaMediaLevaEsporte", avaliacaoService.calculaNotaMedia(recebidasLevaEsporte));
            session.setAttribute("recebidasParticipaEsporte", recebidasParticipaEsporte);
            session.setAttribute("notaMediaParticipaEsporte", avaliacaoService.calculaNotaMedia(recebidasParticipaEsporte));
            pagina = "jsp/usuario/index.jsp";
        } else {
            session.setAttribute("mensagem", "E-mail ou senha incorretos.");
            pagina = "jsp/usuario/login.jsp";
        }
        
        RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
        dispatcher.forward(req, resp);
        
    }
    
}
