package teste;

import javax.persistence.EntityManager;
import model.Usuario;
import util.JpaUtil;

public class TesteCriaUsuario {

    public static void main(String[] args) {
        Usuario usuario1 = new Usuario();
        usuario1.setNome("Breno Nogueira");
        usuario1.setSexo("Masculino");
        usuario1.setCidadeMoradia("Niterói");
        usuario1.setPaisMoradia("Brasil");
        usuario1.setEsporteFavorito("Futebol Americano");
        usuario1.setDispostoReceber(Boolean.FALSE);
        usuario1.setEmail("breno@gmail.com");
        usuario1.setSenha("1234");
        
        Usuario usuario2 = new Usuario();
        usuario2.setNome("Talita Rezende");
        usuario2.setSexo("Feminino");
        usuario2.setCidadeMoradia("Maricá");
        usuario2.setPaisMoradia("Brasil");
        usuario2.setEsporteFavorito("Futebol");
        usuario2.setDispostoReceber(Boolean.TRUE);
        usuario2.setQuantReceber(2);
        usuario2.setEmail("talita@gmail.com");
        usuario2.setSenha("1234");
        
        Usuario usuario3 = new Usuario();
        usuario3.setNome("Gabriel Guadalupe");
        usuario3.setSexo("Masculino");
        usuario3.setCidadeMoradia("Buenos Aires");
        usuario3.setPaisMoradia("Argentina");
        usuario3.setEsporteFavorito("Basquete");
        usuario3.setDispostoReceber(Boolean.FALSE);
        usuario3.setEmail("gabriel@gmail.com");
        usuario3.setSenha("1234");
        
        Usuario usuario4 = new Usuario();
        usuario4.setNome("Renan Vieira");
        usuario4.setSexo("Masculino");
        usuario4.setCidadeMoradia("Maricá");
        usuario4.setPaisMoradia("Brasil");
        usuario4.setEsporteFavorito("Futebol");
        usuario4.setDispostoReceber(Boolean.TRUE);
        usuario4.setQuantReceber(2);
        usuario4.setEmail("renan@gmail.com");
        usuario4.setSenha("1234");
        
        EntityManager em = new JpaUtil().getEntityManager();
        em.getTransaction().begin();
        
        em.persist(usuario1);
        em.persist(usuario2);
        em.persist(usuario3);
        em.persist(usuario4);
        
        em.getTransaction().commit();
        em.close();
    }
    
}
