package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
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
    
    public Usuario atualiza(Usuario usuarioAntigo, HttpServletRequest req) {
        EntityManager em = new JpaUtil().getEntityManager();
        em.getTransaction().begin();
        
        Usuario usuario = em.find(Usuario.class, usuarioAntigo.getId());
        
        usuario.setNome(req.getParameter("nome"));
        usuario.setCidadeMoradia(req.getParameter("cidadeMoradia"));
        usuario.setPaisMoradia(req.getParameter("paisMoradia"));
        usuario.setEsporteFavorito(req.getParameter("esporteFavorito"));
        
        if(req.getParameter("dispostoReceber") != null) {
            usuario.setDispostoReceber(Boolean.TRUE);
            usuario.setQuantReceber(Integer.parseInt(req.getParameter("quantReceber")));
        } else {
            usuario.setDispostoReceber(Boolean.FALSE);
        }
        
        usuario.setEmail(req.getParameter("email"));
        
//        em.merge(usuario);
        em.getTransaction().commit();
        em.close();
        
        return usuario;
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
    
    public List<Usuario> buscaPorCidadePaisQuant(String cidade, String pais, Integer quantidade) {
        EntityManager em = new JpaUtil().getEntityManager();
        em.getTransaction().begin();
        
        TypedQuery<Usuario> typedQuery = em.createNamedQuery("buscaPorCidadePaisQuant", Usuario.class);
        typedQuery.setParameter("pCidade", cidade);
        typedQuery.setParameter("pPais", pais);
        typedQuery.setParameter("pQuantidade", quantidade);
        
        List<Usuario> usuarios = typedQuery.getResultList();
        
        em.getTransaction().commit();
        em.close();
        
        return usuarios;
    }
            
}
