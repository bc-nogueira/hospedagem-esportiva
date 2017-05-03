package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("hospedagemEsportiva");
    
    public EntityManager getEntityManager() {
        emf = Persistence.createEntityManagerFactory("hospedagemEsportiva");
        return emf.createEntityManager();
    }
}
