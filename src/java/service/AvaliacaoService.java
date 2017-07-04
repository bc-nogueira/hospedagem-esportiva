package service;

import dao.AvaliacaoDAO;
import java.util.List;
import model.Avaliacao;
import model.Usuario;
import model.Viagem;

public class AvaliacaoService {
    public Double calculaNotaMedia(List<Avaliacao> avaliacoes) {
        Double notaTotal = 0.;
        Integer quantNotas = 0;
        Boolean avaliouAlguma = Boolean.FALSE;
        for(Avaliacao avaliacao : avaliacoes) {
            notaTotal += avaliacao.getNota();
            quantNotas++;
        }
        
        return notaTotal/quantNotas;
    }
    
    public Double calculaNotaMediaViagens(List<Avaliacao> avaliacoes, Usuario avaliador) {
        Double notaTotal = 0.;
        Integer quantNotas = 0;
        Boolean avaliouAlguma = Boolean.FALSE;
        for(Avaliacao avaliacao : avaliacoes) {
            
            Viagem viagemAvaliada = avaliacao.getViagemAvaliada();
            List<Avaliacao> avaliacoesFeitas = 
                    new AvaliacaoDAO().buscaPorViagemAvaliadaEAvaliador(viagemAvaliada, avaliador);
            
            if(!avaliacoesFeitas.isEmpty()) {
                notaTotal += avaliacao.getNota();
                quantNotas++;
                avaliouAlguma = Boolean.TRUE;
            }
            
        }
        
        if(!avaliouAlguma) {
            return null;
        }
        
        return notaTotal/quantNotas;
    }
}
