package service;

import java.util.List;
import model.Avaliacao;

public class AvaliacaoService {
    public Double calculaNotaMedia(List<Avaliacao> avaliacoes) {
        Double notaTotal = 0.;
        Integer quantNotas = 0;
        for(Avaliacao avaliacao : avaliacoes) {
            notaTotal += avaliacao.getNota();
            quantNotas++;
        }
        return notaTotal/quantNotas;
    }
}
