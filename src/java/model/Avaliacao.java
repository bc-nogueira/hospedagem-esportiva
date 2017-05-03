package model;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private Enum tipoAvaliacao;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataAvaliacao; //ou Date?
    private String descricao;
    
    @ManyToOne
    private Usuario avaliador;
    @ManyToOne
    private Usuario avaliado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Enum getTipoAvaliacao() {
        return tipoAvaliacao;
    }

    public void setTipoAvaliacao(Enum tipoAvaliacao) {
        this.tipoAvaliacao = tipoAvaliacao;
    }

    public Calendar getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(Calendar dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getAvaliador() {
        return avaliador;
    }

    public void setAvaliador(Usuario avaliador) {
        this.avaliador = avaliador;
    }

    public Usuario getAvaliado() {
        return avaliado;
    }

    public void setAvaliado(Usuario avaliado) {
        this.avaliado = avaliado;
    }
    
}
