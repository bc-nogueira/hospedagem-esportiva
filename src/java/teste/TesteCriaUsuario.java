package teste;

import javax.persistence.EntityManager;
import model.Usuario;
import util.JpaUtil;

public class TesteCriaUsuario {

    public static void main(String[] args) {
        Usuario usuario = new Usuario();
        usuario.setNome("Talita");
        usuario.setSobrenome("Rezende");
        usuario.setLocalMoradia("Maricá");
        usuario.setEsporteFavorito("Futebol");
        usuario.setDispostoReceber(Boolean.TRUE);
        usuario.setQuantReceber(2);
        
        EntityManager em = new JpaUtil().getEntityManager();
        em.getTransaction().begin();
        
        em.persist(usuario);
        
        em.getTransaction().commit();
        em.close();
    }
    
}
