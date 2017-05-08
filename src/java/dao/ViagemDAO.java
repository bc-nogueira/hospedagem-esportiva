package dao;

import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import model.Usuario;
import model.Viagem;
import util.JpaUtil;

public class ViagemDAO {
    public void cadastra(Viagem viagem) throws PersistenceException {
        EntityManager em = new JpaUtil().getEntityManager();
        em.getTransaction().begin();
        
        em.persist(viagem);
        em.getTransaction().commit();
        
        em.close();
    }
    
    public List<Viagem> buscaViagemPorDatas(Calendar dataInicio, Calendar dataFim) {
        EntityManager em = new JpaUtil().getEntityManager();
        em.getTransaction().begin();
        
        TypedQuery<Viagem> typedQuery = em.createNamedQuery("buscaViagemPorDatas", Viagem.class);
        typedQuery.setParameter("pDataInicio", dataInicio);
        typedQuery.setParameter("pDataFim", dataFim);
        
        List<Viagem> viagens = typedQuery.getResultList();
        
        em.getTransaction().commit();
        em.close();
        
        return viagens;
    }
    
    public List<Viagem> buscaFeitas(Usuario hospede) {
        EntityManager em = new JpaUtil().getEntityManager();
        em.getTransaction().begin();
        
        TypedQuery<Viagem> typedQuery = em.createNamedQuery("buscaFeitas", Viagem.class);
        typedQuery.setParameter("pHospede", hospede);
        
        List<Viagem> viagens = typedQuery.getResultList();
        
        em.getTransaction().commit();
        em.close();
        
        return viagens;
    }
    
    public List<Viagem> buscaRecebidas(Usuario anfitriao) {
        EntityManager em = new JpaUtil().getEntityManager();
        em.getTransaction().begin();
        
        TypedQuery<Viagem> typedQuery = em.createNamedQuery("buscaRecebidas", Viagem.class);
        typedQuery.setParameter("pAnfitriao", anfitriao);
        
        List<Viagem> viagens = typedQuery.getResultList();
        
        em.getTransaction().commit();
        em.close();
        
        return viagens;
    }
    
    public void deletaViagem(Integer id) {
        EntityManager em = new JpaUtil().getEntityManager();
        em.getTransaction().begin();
        
        Viagem viagem = em.find(Viagem.class, id);
        
        em.remove(viagem);
        
        em.getTransaction().commit();
        em.close();
    }
}
