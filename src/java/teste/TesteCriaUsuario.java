package teste;

import javax.persistence.EntityManager;
import model.Usuario;
import util.JpaUtil;

public class TesteCriaUsuario {

    public static void main(String[] args) {
        Usuario usuario1 = new Usuario();
        usuario1.setNome("Breno Nogueira");
        usuario1.setLocalMoradia("Niterói");
        usuario1.setEsporteFavorito("Futebol Americano");
        usuario1.setDispostoReceber(Boolean.FALSE);
        usuario1.setEmail("breno@gmail.com");
        usuario1.setSenha("1234");
        
        Usuario usuario2 = new Usuario();
        usuario2.setNome("Talita Rezende");
        usuario2.setLocalMoradia("Maricá");
        usuario2.setEsporteFavorito("Futebol");
        usuario2.setDispostoReceber(Boolean.TRUE);
        usuario2.setQuantReceber(2);
        usuario2.setEmail("talita@gmail.com");
        usuario2.setSenha("1234");
        
        Usuario usuario3 = new Usuario();
        usuario3.setNome("Gabriel Guadalupe");
        usuario3.setLocalMoradia("Niterói");
        usuario3.setEsporteFavorito("Basquete");
        usuario3.setDispostoReceber(Boolean.FALSE);
        usuario3.setEmail("gabriel@gmail.com");
        usuario3.setSenha("1234");
        
        EntityManager em = new JpaUtil().getEntityManager();
        em.getTransaction().begin();
        
        em.persist(usuario1);
        em.persist(usuario2);
        em.persist(usuario3);
        
        em.getTransaction().commit();
        em.close();
    }
    
}
