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
    @NamedQuery(name = "buscaViagemPorDatas",
            query = "select v from Viagem v "
                    + "where v.dataInicio <= :pDataFim and v.dataFim >= :pDataInicio"),
    @NamedQuery(name = "buscaFeitas",
            query = "select v from Viagem v " + 
                    "where v.hospede = :pHospede"),
    @NamedQuery(name = "buscaRecebidas",
            query = "select v from Viagem v " + 
                    "where v.anfitriao = :pAnfitriao")
})
public class Viagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String cidadeDestino;
    @Column(nullable = false)
    private String paisDestino;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Calendar dataInicio;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Calendar dataFim;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusViagem statusViagem;
    @Column(nullable = false)
    private Integer quantHospedes;
    
    @ManyToOne(optional = false)
    private Usuario hospede;
    @ManyToOne(optional = false)
    private Usuario anfitriao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCidadeDestino() {
        return cidadeDestino;
    }

    public void setCidadeDestino(String cidadeDestino) {
        this.cidadeDestino = cidadeDestino;
    }

    public String getPaisDestino() {
        return paisDestino;
    }

    public void setPaisDestino(String paisDestino) {
        this.paisDestino = paisDestino;
    }

    public Calendar getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Calendar dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Calendar getDataFim() {
        return dataFim;
    }

    public void setDataFim(Calendar dataFim) {
        this.dataFim = dataFim;
    }

    public StatusViagem getStatusViagem() {
        return statusViagem;
    }

    public void setStatusViagem(StatusViagem statusViagem) {
        this.statusViagem = statusViagem;
    }

    public Integer getQuantHospedes() {
        return quantHospedes;
    }

    public void setQuantHospedes(Integer quantHospedes) {
        this.quantHospedes = quantHospedes;
    }

    public Usuario getHospede() {
        return hospede;
    }

    public void setHospede(Usuario hospede) {
        this.hospede = hospede;
    }

    public Usuario getAnfitriao() {
        return anfitriao;
    }

    public void setAnfitriao(Usuario anfitriao) {
        this.anfitriao = anfitriao;
    }
    
    public String dataInicioFormatada() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(this.getDataInicio().getTime());
    }
    
    public String dataFimFormatada() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(this.getDataFim().getTime());
    }
    
    public Boolean verificaSeJaPassou() {
        Calendar cal = Calendar.getInstance();
        if(this.getDataFim().before(cal))
            return Boolean.TRUE;
        return Boolean.FALSE;
    }
    
    public Boolean podeAvaliar(Integer idUsuarioLogado) {
        List<Avaliacao> avaliacoes = new AvaliacaoDAO().buscaPorViagemAvaliada(this);
        for(Avaliacao avaliacao : avaliacoes) {
            if(avaliacao.getAvaliador().getId().equals(idUsuarioLogado)) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }
}
