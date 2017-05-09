package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Avaliacao;
import model.TipoAvaliacao;
import model.Usuario;
import model.Viagem;
import util.JpaUtil;

public class AvaliacaoDAO {
    public void salvaAvaliacao(Avaliacao avaliacao) {
        EntityManager em = new JpaUtil().getEntityManager();
        em.getTransaction().begin();
        
        em.persist(avaliacao);
        em.getTransaction().commit();
        
        em.close();
    }
    
    public List<Avaliacao> buscaPorAvaliadoEAvaliadorETipo(Usuario avaliado, 
            Usuario avaliador, TipoAvaliacao tipo) {
        EntityManager em = new JpaUtil().getEntityManager();
        em.getTransaction().begin();
        
        TypedQuery<Avaliacao> typedQuery = em.createNamedQuery("buscaPorAvaliadoEAvaliadorETipo", Avaliacao.class);
        typedQuery.setParameter("pAvaliado", avaliado);
        typedQuery.setParameter("pAvaliador", avaliador);
        typedQuery.setParameter("pTipo", tipo);
        
        List<Avaliacao> avaliacoes = typedQuery.getResultList();
        
        em.getTransaction().commit();
        em.close();
        
        return avaliacoes;
    }
    
    public List<Avaliacao> buscaRecebidaPorUsuarioETipo(Usuario usuario, TipoAvaliacao tipo) {
        EntityManager em = new JpaUtil().getEntityManager();
        em.getTransaction().begin();
        
        TypedQuery<Avaliacao> typedQuery = em.createNamedQuery("buscaRecebidaPorUsuarioETipo", Avaliacao.class);
        typedQuery.setParameter("pAvaliado", usuario);
        typedQuery.setParameter("pTipo", tipo);
        
        List<Avaliacao> recebidas = typedQuery.getResultList();
        
        em.getTransaction().commit();
        em.close();
        
        return recebidas;
    }
    
    public List<Avaliacao> buscaPorViagemAvaliada(Viagem viagem) {
        EntityManager em = new JpaUtil().getEntityManager();
        em.getTransaction().begin();
        
        TypedQuery<Avaliacao> typedQuery = em.createNamedQuery("buscaPorViagemAvaliada", Avaliacao.class);
        typedQuery.setParameter("pViagem", viagem);
        
        List<Avaliacao> avaliacoes = typedQuery.getResultList();
        
        em.getTransaction().commit();
        em.close();
        
        return avaliacoes;
    }
    
}
