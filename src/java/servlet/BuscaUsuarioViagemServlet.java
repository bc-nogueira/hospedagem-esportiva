package servlet;

import com.google.gson.Gson;
import dao.UsuarioDAO;
import dao.ViagemDAO;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Usuario;
import model.Viagem;

@WebServlet("/buscaUsuarioViagem")
public class BuscaUsuarioViagemServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String cidadeDestino = req.getParameter("cidadeDestino");
        String paisDestino = req.getParameter("paisDestino");
        Integer quantidade = Integer.parseInt(req.getParameter("quantidade"));
        
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Calendar chegada = Calendar.getInstance();
        Calendar saida = Calendar.getInstance();

        try {
            chegada.setTime(formatoData.parse(req.getParameter("chegada")));
            saida.setTime(formatoData.parse(req.getParameter("saida")));
        } catch (ParseException ex) {
        }
        
        List<Usuario> usuarios = new UsuarioDAO().buscaPorCidadePaisQuant(cidadeDestino, paisDestino, quantidade);
        
        //Fazer pesquisa para retirar usuarios que já tenham hospedagem nesse periodo
        List<Viagem> viagens = new ViagemDAO().buscaViagemPorDatas(chegada, saida);
        
        for(Iterator<Usuario> iterator = usuarios.iterator(); iterator.hasNext();) {
            Usuario usuario = iterator.next();
            for(Viagem viagem : viagens) {
                if(usuario.getId().equals(viagem.getAnfitriao().getId())) {
                    iterator.remove();
                }
            }
        }
        
        HttpSession session = req.getSession();
        
        // Se não tiver resultado, nem precisa inicializar viagem.
        if(!usuarios.isEmpty()) {
            Viagem viagem = this.preparaViagem(cidadeDestino, paisDestino, chegada, saida, 
                    quantidade, (Usuario) session.getAttribute("usuarioLogado"));
            
            session.setAttribute("viagem", viagem);
        }
        
        String json = new Gson().toJson(usuarios);
        
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }
    
    private Viagem preparaViagem(String cidade, String pais, Calendar chegada, 
            Calendar saida, Integer quantidade, Usuario usuario) {
        Viagem viagem = new Viagem();
        viagem.setCidadeDestino(cidade);
        viagem.setPaisDestino(pais);
        viagem.setDataInicio(chegada);
        viagem.setDataFim(saida);
        viagem.setQuantHospedes(quantidade);
        viagem.setHospede(usuario);
        
        return viagem;
    }
}
