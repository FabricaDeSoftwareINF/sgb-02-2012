package br.ufg.inf.es.model;

import java.util.Collection;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Jackeline Neves
 */
@Entity
@Table(name = "LISTA_COMPRAS")
public class ListaCompras extends AbstractEntityModel {

    @Column(name = "nome")
    private String nome;
    @Column(name = "data_criacao")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCriacao;
    @OneToOne(optional = false)
    private Usuario user;
    @ManyToMany(targetEntity = Livro.class)
    @JoinTable(name = "TB_LISTACOMPRAS_LIVRO", joinColumns =
    @JoinColumn(name = "id_listaCompras"), inverseJoinColumns =
    @JoinColumn(name = "id_livro"))
    private Collection<Livro> livrosDaListaCompras;

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Collection<Livro> getLivrosDaListaCompras() {
        return livrosDaListaCompras;
    }

    public void setLivrosDaListaCompras(Collection<Livro> livrosDaListaCompras) {
        this.livrosDaListaCompras = livrosDaListaCompras;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
