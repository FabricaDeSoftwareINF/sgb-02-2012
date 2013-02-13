package br.ufg.inf.es.model;

import java.util.Collection;
import java.util.Date;
import javax.persistence.*;

/**
 * Entidade ListaCompras
 * @author Jackeline Neves
 */
@Entity
@Table(name = "LISTA_COMPRAS")
public class ListaCompras extends AbstractEntityModel {

    /** Campo nome*/
    @Column(name = "nome")
    private String nome;
    
    /** Campo dataCriacao*/
    @Column(name = "data_criacao")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCriacao;
    
    /** Campo user*/
    @OneToOne(optional = false)
    private Usuario user;
    
    /** Campo livrosDaListaCompras*/
    @ManyToMany(targetEntity = Livro.class)
    @JoinTable(name = "TB_LISTACOMPRAS_LIVRO", joinColumns =
    @JoinColumn(name = "id_listaCompras"), inverseJoinColumns =
    @JoinColumn(name = "id_livro"))
    private Collection<Livro> livrosDaListaCompras;

    /**
     * Obtém o valor do campo <code>nome</code>
     *
     * @return {@link String}
     */
    public String getNome() {

            return this.nome;
    }

    /**
     * Define o campo <code>nome</code>.
     *
     * @param nome 
     */
    public void setNome(String nome) {

            this.nome = nome;
    }

    /**
     * Obtém o valor do campo <code>dataCriacao</code>
     *
     * @return {@link Date}
     */
    public Date getDataCriacao() {

        return this.dataCriacao != null ? (Date) dataCriacao.clone() : dataCriacao;
    }

    /**
     * Define o campo <code>dataCriacao</code>.
     *
     * @param dataCriacao 
     */
    public void setDataCriacao(Date dataCriacao) {

            this.dataCriacao = this.dataCriacao != null ? (Date) dataCriacao.clone() : dataCriacao;
    }

    /**
     * Obtém o valor do campo <code>user</code>
     *
     * @return {@link Usuario}
     */
    public Usuario getUser() {

            return this.user;
    }

    /**
     * Define o campo <code>user</code>.
     *
     * @param user 
     */
    public void setUser(Usuario user) {

            this.user = user;
    }

    /**
     * Obtém o valor do campo <code>livrosDaListaCompras</code>
     *
     * @return {@link Collection<Livro>}
     */
    public Collection<Livro> getLivrosDaListaCompras() {

            return this.livrosDaListaCompras;
    }

    /**
     * Define o campo <code>livrosDaListaCompras</code>.
     *
     * @param livrosDaListaCompras 
     */
    public void setLivrosDaListaCompras(Collection<Livro> livrosDaListaCompras) {

            this.livrosDaListaCompras = livrosDaListaCompras;
    }

}
