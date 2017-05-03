package teste;

import javax.persistence.EntityManager;
import model.Usuario;
import util.JpaUtil;

public class TesteCriaUsuario {

    public static void main(String[] args) {
        Usuario usuario1 = new Usuario();
        usuario1.setNome("Talita");
        usuario1.setSobrenome("Rezende");
        usuario1.setLocalMoradia("Maricá");
        usuario1.setEsporteFavorito("Futebol");
        usuario1.setDispostoReceber(Boolean.TRUE);
        usuario1.setQuantReceber(2);
        usuario1.setEmail("talita@gmail.com");
        usuario1.setSenha("1234");
        
        Usuario usuario2 = new Usuario();
        usuario2.setNome("Breno");
        usuario2.setSobrenome("Nogueira");
        usuario2.setLocalMoradia("Niterói");
        usuario2.setEsporteFavorito("Futebol Americano");
        usuario2.setDispostoReceber(Boolean.FALSE);
        usuario2.setEmail("breno@gmail.com");
        usuario2.setSenha("1234");
        
        EntityManager em = new JpaUtil().getEntityManager();
        em.getTransaction().begin();
        
        em.persist(usuario1);
        em.persist(usuario2);
        
        em.getTransaction().commit();
        em.close();
    }
    
}
