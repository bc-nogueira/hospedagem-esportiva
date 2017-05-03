package model;

import java.util.Date;

public class Avaliacao {
    private Integer id;
    private Enum tipoAvaliacao;
    private Date dataAvaliacao; //ou Calendar?
    
    private Usuario avaliador;
    
    private Usuario avaliado;
    
}
