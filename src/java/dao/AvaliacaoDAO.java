package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Avaliacao;
import model.TipoAvaliacao;
import model.Usuario;
import util.JpaUtil;

public class AvaliacaoDAO {
    
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
    
}
