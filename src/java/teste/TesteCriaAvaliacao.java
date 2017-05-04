package teste;

import java.util.Calendar;
import javax.persistence.EntityManager;
import model.Avaliacao;
import model.TipoAvaliacao;
import model.Usuario;
import util.JpaUtil;

public class TesteCriaAvaliacao {
    public static void main(String[] args) {
        Avaliacao avaliacao1 = new Avaliacao();
        avaliacao1.setDescricao("Bla bla bla.");
        avaliacao1.setDataAvaliacao(Calendar.getInstance());
        avaliacao1.setTipoAvaliacao(TipoAvaliacao.AMIGO);
        avaliacao1.setNota(3.5);
        
        Avaliacao avaliacao2 = new Avaliacao();
        avaliacao2.setDescricao("Bla bla bla 2.");
        avaliacao2.setDataAvaliacao(Calendar.getInstance());
        avaliacao2.setTipoAvaliacao(TipoAvaliacao.AMIGO);
        avaliacao2.setNota(4.5);
        
        Avaliacao avaliacao3 = new Avaliacao();
        avaliacao3.setDescricao("Bla bla bla 3.");
        avaliacao3.setDataAvaliacao(Calendar.getInstance());
        avaliacao3.setTipoAvaliacao(TipoAvaliacao.HOSPEDE);
        avaliacao3.setNota(4.5);
        
        Avaliacao avaliacao4 = new Avaliacao();
        avaliacao4.setDescricao("Bla bla bla 4.");
        avaliacao4.setDataAvaliacao(Calendar.getInstance());
        avaliacao4.setTipoAvaliacao(TipoAvaliacao.LEVA_SAIDA_ESPORTE);
        avaliacao4.setNota(3.5);
        
        EntityManager em = new JpaUtil().getEntityManager();
        em.getTransaction().begin();
        
        Usuario usuario1 = em.find(Usuario.class, 1);
        Usuario usuario2 = em.find(Usuario.class, 2);
        Usuario usuario3 = em.find(Usuario.class, 3);
        
        avaliacao1.setAvaliado(usuario1);
        avaliacao1.setAvaliador(usuario2);
        avaliacao2.setAvaliado(usuario1);
        avaliacao2.setAvaliador(usuario3);
        avaliacao3.setAvaliado(usuario1);
        avaliacao3.setAvaliador(usuario2);
        
        avaliacao4.setAvaliado(usuario2);
        avaliacao4.setAvaliador(usuario1);
        
        em.persist(avaliacao1);
        em.persist(avaliacao2);
        em.persist(avaliacao3);
        em.persist(avaliacao4);
        
        em.getTransaction().commit();
        em.close();
    }
}
