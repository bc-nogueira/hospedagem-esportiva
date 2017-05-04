package dao;

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
