package model;

import dao.AvaliacaoDAO;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
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
                    "where a.avaliado = :pAvaliado and a.tipoAvaliacao = :pTipo"),
    @NamedQuery(name = "buscaPorViagemAvaliada",
            query = "select a from Avaliacao a " + 
                    "where a.viagemAvaliada = :pViagem"),
    @NamedQuery(name = "buscaPorAvaliadoEAvaliadorETipo",
            query = "select a from Avaliacao a " + 
                    "where a.avaliado = :pAvaliado and a.avaliador = :pAvaliador and a.tipoAvaliacao = :pTipo"),
    @NamedQuery(name = "buscaPorViagemAvaliadaEAvaliador",
            query = "select a from Avaliacao a " + 
                    "where a.viagemAvaliada = :pViagem and a.avaliador = :pAvaliador")
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
    private Calendar dataAvaliacao;
    private Double nota;
    @Column(nullable = false)
    private String descricao;
    
    @ManyToOne(optional = false)
    private Usuario avaliador;
    @ManyToOne(optional = false)
    private Usuario avaliado;
    
    @ManyToOne
    private Viagem viagemAvaliada;
    
//    private Esporte esporteAvaliado;

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

    public Viagem getViagemAvaliada() {
        return viagemAvaliada;
    }

    public void setViagemAvaliada(Viagem viagemAvaliada) {
        this.viagemAvaliada = viagemAvaliada;
    }
    
    public String horaFormatada() {
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss, dd/MM/yyyy");
        return formato.format(this.getDataAvaliacao().getTime());
    }
    
    public Boolean podeSerVista() {
        List<Avaliacao> avaliacoes = new AvaliacaoDAO().buscaPorViagemAvaliada(this.getViagemAvaliada());
        if(avaliacoes.size() == 2) 
            return Boolean.TRUE;
        return Boolean.FALSE;
    }
}
