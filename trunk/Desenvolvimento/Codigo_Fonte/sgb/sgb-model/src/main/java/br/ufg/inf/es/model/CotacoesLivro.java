package br.ufg.inf.es.model;

import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author vinicius
 */
@Entity
@Table(name = "COTACAO_LIVRO")
public class CotacoesLivro extends AbstractEntityModel {

    /**
     * Campo valor
     */
    private double valor;
    /**
     * Campo dataCadastro
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCadastro;
    /**
     * Campo livro
     */
    @OneToOne
    private Livro livro;
    /**
     * Campo livraria
     */
    @OneToMany
    @JoinColumn(name="id_cotacao")
    private Collection<Cotacao> cotacoes;
    
    /**
     * Campo quantidade
     */
    private int quantidade;

    /**
     * Obtém o valor do campo
     * <code>valor</code>
     *
     * @return {@link double}
     */
    public double getValor() {
        return this.valor;
    }

    /**
     * Define o campo
     * <code>valor</code>.
     *
     * @param valor
     */
    public void setValor(double valor) {

        this.valor = valor;
    }

    /**
     * Obtém o valor do campo
     * <code>dataCadastro</code>
     *
     * @return {@link Date}
     */
    public Date getDataCadastro() {

        return dataCadastro != null ? (Date) dataCadastro.clone() : dataCadastro;
    }

    /**
     * Define o campo
     * <code>dataCadastro</code>.
     *
     * @param dataCadastro
     */
    public void setDataCadastro(Date dataCadastro) {

        this.dataCadastro = dataCadastro != null ? (Date) dataCadastro.clone() : dataCadastro;
    }

    /**
     * Obtém o valor do campo
     * <code>livro</code>
     *
     * @return {@link Livro}
     */
    public Livro getLivro() {

        return this.livro;
    }

    /**
     * Define o campo
     * <code>livro</code>.
     *
     * @param livro
     */
    public void setLivro(Livro livro) {

        this.livro = livro;
    }

    /**
     * Obtém o valor do campo
     * <code>livraria</code>
     *
     * @return {@link Livraria}
     */
    public Collection<Cotacao> getCotacaoes() {
        return this.cotacoes;
    }

    /**
     * Define o campo
     * <code>livraria</code>.
     *
     * @param cotacoes
     */
    public void setCotacoes(Collection<Cotacao> cotacoes) {
        this.cotacoes = cotacoes;
    }

    /**
     * Obtém o valor do campo
     * <code>quantidade</code>
     *
     * @return {@link int}
     */
    public int getQuantidade() {

        return this.quantidade;
    }

    /**
     * Define o campo
     * <code>quantidade</code>.
     *
     * @param quantidade
     */
    public void setQuantidade(int quantidade) {

        this.quantidade = quantidade;
    }
}
