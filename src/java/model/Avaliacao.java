package model;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
    @NamedQuery(name = "buscaRecebidaPorUsuarioETipo",
            query = "select a from Avaliacao a " + 
                    "where a.avaliado = :pAvaliado and a.tipoAvaliacao = :pTipo")
})
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAvaliacao tipoAvaliacao;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Calendar dataAvaliacao; //ou Date?
    private Double nota;
    @Column(nullable = false)
    private String descricao;
    
    @ManyToOne(optional = false)
    private Usuario avaliador;
    @ManyToOne(optional = false)
    private Usuario avaliado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoAvaliacao getTipoAvaliacao() {
        return tipoAvaliacao;
    }

    public void setTipoAvaliacao(TipoAvaliacao tipoAvaliacao) {
        this.tipoAvaliacao = tipoAvaliacao;
    }

    public Calendar getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(Calendar dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
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
