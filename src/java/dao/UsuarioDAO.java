package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import model.Usuario;
import util.JpaUtil;

public class UsuarioDAO {
    
    public void cadastra(Usuario usuario) throws PersistenceException {
        EntityManager em = new JpaUtil().getEntityManager();
        em.getTransaction().begin();
        
        em.persist(usuario);
        em.getTransaction().commit();
        
        em.close();
    }
    
    public void atualiza(Usuario usuario) {
        EntityManager em = new JpaUtil().getEntityManager();
        em.getTransaction().begin();
        
        em.merge(usuario);
        em.getTransaction().commit();
        
        em.close();
    }
    
    public Usuario buscaPorId(Integer id) {
        EntityManager em = new JpaUtil().getEntityManager();
        em.getTransaction().begin();
        
        Usuario usuario = em.find(Usuario.class, id);
        
        em.close();
        return usuario;
    }
    
    public Usuario buscaPorEmail(String email) {
        EntityManager em = new JpaUtil().getEntityManager();
        em.getTransaction().begin();
        
        TypedQuery<Usuario> typedQuery = em.createNamedQuery("buscaPorEmail", Usuario.class);
        typedQuery.setParameter("pEmail", email);
        
        Usuario usuario;
        
        if(typedQuery.getResultList().isEmpty()) {
            usuario = null;
        } else {
            usuario = typedQuery.getSingleResult();
        }
        
        em.getTransaction().commit();
        em.close();
        
        return usuario;
    }
    
    public List<Usuario> buscaPorNomeEEmailLike(String nome, String email) {
        EntityManager em = new JpaUtil().getEntityManager();
        em.getTransaction().begin();
        
        TypedQuery<Usuario> typedQuery = em.createNamedQuery("buscaPorNomeEEmailLike", Usuario.class);
        typedQuery.setParameter("pNome", "%"+nome+"%");
        typedQuery.setParameter("pEmail", "%"+email+"%");
        
        List<Usuario> usuario = typedQuery.getResultList();
        
        em.getTransaction().commit();
        em.close();
        
        return usuario;
    }
    
    public Usuario buscaPorEmailESenha(String email, String senha) {
        EntityManager em = new JpaUtil().getEntityManager();
        em.getTransaction().begin();
        
        TypedQuery<Usuario> typedQuery = em.createNamedQuery("buscaPorEmailESenha", Usuario.class);
        typedQuery.setParameter("pEmail", email);
        typedQuery.setParameter("pSenha", senha);
        
        Usuario usuario;
        
        if(typedQuery.getResultList().isEmpty()) {
            usuario = null;
        } else {
            usuario = typedQuery.getSingleResult();
        }
        
        em.getTransaction().commit();
        em.close();
        
        return usuario;
    }
    
}
