package br.ufg.inf.es.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;

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
    
    @JoinColumn(name="id_usuario")
    @ManyToOne(optional = false)
    private Usuario user;
    
    /** Campo livrosDaListaCompras*/
    @OneToMany(cascade= CascadeType.ALL)
    @JoinTable(
        name="LISTA_COMPRAS_ITEM_LISTA_COMPRAS",
        joinColumns=@JoinColumn(name="id_lista_compra"),
        inverseJoinColumns=@JoinColumn(name="id_item_lista_compra")
    )
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Collection<ItemListaCompras> livrosDaListaCompras = new ArrayList<ItemListaCompras>();

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
    public Collection<ItemListaCompras> getLivrosDaListaCompras() {
            return this.livrosDaListaCompras;
    }

    /**
     * Define o campo <code>livrosDaListaCompras</code>.
     *
     * @param livrosDaListaCompras 
     */
    public void setLivrosDaListaCompras(Collection<ItemListaCompras> livrosDaListaCompras) {
            this.livrosDaListaCompras = livrosDaListaCompras;
    }

}
