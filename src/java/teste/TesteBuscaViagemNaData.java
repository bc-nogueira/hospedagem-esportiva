package teste;

import dao.ViagemDAO;
import java.util.Calendar;
import java.util.List;
import model.Viagem;

public class TesteBuscaViagemNaData {
    public static void main(String[] args) {
        // Data pretendida dentro do intervalo de uma viagem existente
        Calendar dataInicio = Calendar.getInstance();
        Calendar dataFim = Calendar.getInstance();
        dataFim.add(Calendar.DAY_OF_MONTH, 5);
        
        List<Viagem> viagens = new ViagemDAO().buscaViagemPorDatas(dataInicio, dataFim);
        
        if(!viagens.isEmpty())
            System.out.println("(1/6)Data pretendida dentro do intervalo de uma viagem existente:"
                    + "Funcionou!!");
        
        // Data pretendida abrange o intervalo de uma viagem existente
        dataInicio = Calendar.getInstance();
        dataInicio.add(Calendar.MONTH, -1);
        dataFim = Calendar.getInstance();
        dataFim.add(Calendar.MONTH, 2);
        
        viagens = new ViagemDAO().buscaViagemPorDatas(dataInicio, dataFim);
        
        if(!viagens.isEmpty())
            System.out.println("(2/6)Data pretendida abrange o intervalo de uma viagem existente:"
                    + "Funcionou!!");
        
        // Data pretendida comeca antes e termina dentro
        dataInicio = Calendar.getInstance();
        dataInicio.add(Calendar.MONTH, -1);
        dataFim = Calendar.getInstance();
        dataFim.add(Calendar.DAY_OF_MONTH, 5);
        
        viagens = new ViagemDAO().buscaViagemPorDatas(dataInicio, dataFim);
        
        if(!viagens.isEmpty())
            System.out.println("(3/6)Data pretendida comeca antes e termina dentro:"
                    + "Funcionou!!");
        
        // Data pretendida comeca dentro e termina depois
        dataInicio = Calendar.getInstance();
        dataInicio.add(Calendar.DAY_OF_MONTH, 5);
        dataFim = Calendar.getInstance();
        dataFim.add(Calendar.MONTH, 3);
        
        viagens = new ViagemDAO().buscaViagemPorDatas(dataInicio, dataFim);
        
        if(!viagens.isEmpty())
            System.out.println("(4/6)Data pretendida comeca dentro e termina depois:"
                    + "Funcionou!!");
        
        // Data pretendida antes do intervalo de uma viagem existente
        dataInicio = Calendar.getInstance();
        dataInicio.add(Calendar.MONTH, -2);
        dataFim = Calendar.getInstance();
        dataFim.add(Calendar.MONTH, -1);
        
        viagens = new ViagemDAO().buscaViagemPorDatas(dataInicio, dataFim);
        
        if(viagens.isEmpty())
            System.out.println("(5/6)Data pretendida antes do intervalo de uma viagem existente:"
                    + "Funcionou!!");
        
        // Data pretendida depois do intervalo de uma viagem existente
        dataInicio = Calendar.getInstance();
        dataInicio.add(Calendar.MONTH, 3);
        dataFim = Calendar.getInstance();
        dataFim.add(Calendar.MONTH, 4);
        
        viagens = new ViagemDAO().buscaViagemPorDatas(dataInicio, dataFim);
        
        if(viagens.isEmpty())
            System.out.println("(6/6)Data pretendida depois do intervalo de uma viagem existente:"
                    + "Funcionou!!");
    }
}
