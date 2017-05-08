package teste;

import java.util.Calendar;
import javax.persistence.EntityManager;
import model.StatusViagem;
import model.Usuario;
import model.Viagem;
import util.JpaUtil;

public class TesteCriaViagem {
    public static void main(String[] args) {
        Viagem viagem1 = new Viagem();
        viagem1.setCidadeDestino("Maricá");
        viagem1.setPaisDestino("Brasil");
        viagem1.setDataInicio(Calendar.getInstance());

        Calendar fim = Calendar.getInstance(); 
        fim.add(Calendar.MONTH, 1);
        viagem1.setDataFim(fim);
        
        viagem1.setStatusViagem(StatusViagem.APROVADA);
        viagem1.setQuantHospedes(1);
        
        Viagem viagem2 = new Viagem();
        viagem2.setCidadeDestino("Maricá");
        viagem2.setPaisDestino("Brasil");
        viagem2.setDataInicio(Calendar.getInstance());

        fim = Calendar.getInstance(); 
        fim.add(Calendar.MONTH, 1);
        viagem2.setDataFim(fim);
        
        viagem2.setStatusViagem(StatusViagem.PENDENTE);
        viagem2.setQuantHospedes(1);
        
        EntityManager em = new JpaUtil().getEntityManager();
        em.getTransaction().begin();
        
        Usuario hospede = em.find(Usuario.class, 1);
        Usuario anfitriao = em.find(Usuario.class, 4);
        
        viagem1.setHospede(hospede);
        viagem1.setAnfitriao(anfitriao);
        viagem2.setHospede(hospede);
        viagem2.setAnfitriao(anfitriao);
        
        em.persist(viagem1);
        em.persist(viagem2);
        
        em.getTransaction().commit();
        em.close();
    }
}
