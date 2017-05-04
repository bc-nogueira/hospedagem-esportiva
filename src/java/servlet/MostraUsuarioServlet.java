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

@WebServlet("/mostraUsuario")
public class MostraUsuarioServlet extends HttpServlet {
    AvaliacaoService avaliacaoService = new AvaliacaoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Integer idUsuario = Integer.parseInt(req.getParameter("id"));
        
        Usuario usuarioMostrar = new UsuarioDAO().buscaPorId(idUsuario);
        
        HttpSession session = req.getSession();
        session.setAttribute("usuarioMostrar", usuarioMostrar);
        
        List<Avaliacao> recebidasAmigosUsuarioMostrar = 
                    new AvaliacaoDAO().buscaRecebidaPorUsuarioETipo(usuarioMostrar, TipoAvaliacao.AMIGO);
            List<Avaliacao> recebidasHospedeUsuarioMostrar = 
                    new AvaliacaoDAO().buscaRecebidaPorUsuarioETipo(usuarioMostrar, TipoAvaliacao.HOSPEDE);
            List<Avaliacao> recebidasAnfitriaoUsuarioMostrar = 
                    new AvaliacaoDAO().buscaRecebidaPorUsuarioETipo(usuarioMostrar, TipoAvaliacao.ANFITRIAO);
            List<Avaliacao> recebidasLevaEsporteUsuarioMostrar = 
                    new AvaliacaoDAO().buscaRecebidaPorUsuarioETipo(usuarioMostrar, TipoAvaliacao.LEVA_SAIDA_ESPORTE);
            List<Avaliacao> recebidasParticipaEsporteUsuarioMostrar = 
                    new AvaliacaoDAO().buscaRecebidaPorUsuarioETipo(usuarioMostrar, TipoAvaliacao.PARTICIPA_SAIDA_ESPORTE);
            
            session.setAttribute("recebidasAmigosUsuarioMostrar", recebidasAmigosUsuarioMostrar);
            session.setAttribute("notaMediaAmigosUsuarioMostrar", avaliacaoService.calculaNotaMedia(recebidasAmigosUsuarioMostrar));
            session.setAttribute("recebidasHospedeUsuarioMostrar", recebidasHospedeUsuarioMostrar);
            session.setAttribute("notaMediaHospedeUsuarioMostrar", avaliacaoService.calculaNotaMedia(recebidasHospedeUsuarioMostrar));
            session.setAttribute("recebidasAnfitriaoUsuarioMostrar", recebidasAnfitriaoUsuarioMostrar);
            session.setAttribute("notaMediaAnfitriaoUsuarioMostrar", avaliacaoService.calculaNotaMedia(recebidasAnfitriaoUsuarioMostrar));            
            session.setAttribute("recebidasLevaEsporteUsuarioMostrar", recebidasLevaEsporteUsuarioMostrar);
            session.setAttribute("notaMediaLevaEsporteUsuarioMostrar", avaliacaoService.calculaNotaMedia(recebidasLevaEsporteUsuarioMostrar));
            session.setAttribute("recebidasParticipaEsporteUsuarioMostrar", recebidasParticipaEsporteUsuarioMostrar);
            session.setAttribute("notaMediaParticipaEsporteUsuarioMostrar", avaliacaoService.calculaNotaMedia(recebidasParticipaEsporteUsuarioMostrar));
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/usuario/show.jsp");
        dispatcher.forward(req, resp);
    }
    
}
