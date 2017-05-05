package model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
    @NamedQuery(name = "buscaPorEmail",
            query = "select u from Usuario u " + 
                    "where u.email = :pEmail"),
    @NamedQuery(name = "buscaPorNomeEEmailLike",
            query = "select u from Usuario u " + 
                    "where u.nome like :pNome or u.email like :pEmail"),
    @NamedQuery(name = "buscaPorEmailESenha",
            query = "select u from Usuario u " + 
                    "where u.email = :pEmail and u.senha = :pSenha")
})
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String localMoradia;
    @Column(nullable = false)
    private String esporteFavorito;
    private Boolean dispostoReceber;
    private Integer quantReceber;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String senha;
    
//    @OneToMany(mappedBy = "avaliador")
//    private List<Avaliacao> avaliacoesFeitas;
//    @OneToMany(mappedBy = "avaliado")
//    private List<Avaliacao> avaliacoesRecebidas;
    
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

    public String getLocalMoradia() {
        return localMoradia;
    }

    public void setLocalMoradia(String localMoradia) {
        this.localMoradia = localMoradia;
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

//    public List<Avaliacao> getAvaliacoesFeitas() {
//        return avaliacoesFeitas;
//    }
//
//    public void setAvaliacoesFeitas(List<Avaliacao> avaliacoesFeitas) {
//        this.avaliacoesFeitas = avaliacoesFeitas;
//    }
//
//    public List<Avaliacao> getAvaliacoesRecebidas() {
//        return avaliacoesRecebidas;
//    }
//
//    public void setAvaliacoesRecebidas(List<Avaliacao> avaliacoesRecebidas) {
//        this.avaliacoesRecebidas = avaliacoesRecebidas;
//    }
    
}
