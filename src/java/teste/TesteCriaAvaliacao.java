package teste;

import java.util.Calendar;
import javax.persistence.EntityManager;
import model.Avaliacao;
import model.TipoAvaliacao;
import model.Usuario;
import util.JpaUtil;

public class TesteCriaAvaliacao {
    public static void main(String[] args) {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setDescricao("Bla bla bla.");
        avaliacao.setDataAvaliacao(Calendar.getInstance());
        avaliacao.setTipoAvaliacao(TipoAvaliacao.AMIGO);
        avaliacao.setNota(3.5);
        
        EntityManager em = new JpaUtil().getEntityManager();
        em.getTransaction().begin();
        
        Usuario avaliado = em.find(Usuario.class, 1);
        Usuario avaliador = em.find(Usuario.class, 2);
        
        avaliacao.setAvaliado(avaliado);
        avaliacao.setAvaliador(avaliador);
        
        em.persist(avaliacao);
        
        em.getTransaction().commit();
        em.close();
    }
}
