package model;

import dao.AvaliacaoDAO;
import dao.UsuarioDAO;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "buscaPorEmail",
            query = "select u from Usuario u "
                    + "where u.email = :pEmail"),
    @NamedQuery(name = "buscaPorNomeEEmailLike",
            query = "select u from Usuario u "
                    + "where u.nome like :pNome or u.email like :pEmail"),
    @NamedQuery(name = "buscaPorEmailESenha",
            query = "select u from Usuario u "
                    + "where u.email = :pEmail and u.senha = :pSenha"),
    @NamedQuery(name = "buscaPorCidadePaisQuant",
            query = "select u from Usuario u "
                    + "where u.cidadeMoradia = :pCidade and u.paisMoradia = :pPais "
                    + "and u.quantReceber >= :pQuantidade and u.dispostoReceber = true")
})
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String sexo;
    @Column(nullable = false)
    private String cidadeMoradia;
    @Column(nullable = false)
    private String paisMoradia;
    @Column(nullable = false)
    private String esporteFavorito;
    private Boolean dispostoReceber;
    private Integer quantReceber;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String senha;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    public String getCidadeMoradia() {
        return cidadeMoradia;
    }

    public void setCidadeMoradia(String cidadeMoradia) {
        this.cidadeMoradia = cidadeMoradia;
    }

    public String getPaisMoradia() {
        return paisMoradia;
    }

    public void setPaisMoradia(String paisMoradia) {
        this.paisMoradia = paisMoradia;
    }

    public String getEsporteFavorito() {
        return esporteFavorito;
    }

    public void setEsporteFavorito(String esporteFavorito) {
        this.esporteFavorito = esporteFavorito;
    }

    public Boolean getDispostoReceber() {
        return dispostoReceber;
    }

    public void setDispostoReceber(Boolean dispostoReceber) {
        this.dispostoReceber = dispostoReceber;
    }

    public Integer getQuantReceber() {
        return quantReceber;
    }

    public void setQuantReceber(Integer quantReceber) {
        this.quantReceber = quantReceber;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    public Boolean podeAvaliar(Integer id) {
        Usuario usuarioLogado = new UsuarioDAO().buscaPorId(id);
        List<Avaliacao> avaliacoes = new AvaliacaoDAO()
                .buscaPorAvaliadoEAvaliadorETipo(this, usuarioLogado, TipoAvaliacao.AMIGO);
        if(avaliacoes.isEmpty())
            return Boolean.TRUE;
        return Boolean.FALSE;
    }
    
}
