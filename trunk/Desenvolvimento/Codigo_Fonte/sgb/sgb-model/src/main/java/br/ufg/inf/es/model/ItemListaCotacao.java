package br.ufg.inf.es.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;

/**
 *
 * @author vinicius
 */
@Entity
@Table(name = "ITEM_LISTA_COTACAO")
public class ItemListaCotacao extends AbstractEntityModel {

    private static final int HASH = 7;
    private static final int SALTO = 19;
    private static final int TAMANHO_BITS = 32;
    /**
     * Campo urlImagem
     */
    private String urlImagem;
    /**
     * Campo valor
     */
    private double valorMedio;
    /**
     * Campo livro
     */
    @ManyToOne(optional=false)
    private Livro livro;
    /**
     * Campo livraria
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ITEM_LISTA_COTACAO_COTACAO",
            joinColumns =@JoinColumn( name = "id_item_lista_cotacao"),
            inverseJoinColumns =@JoinColumn( name = "id_cotacao"))
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Collection<Cotacao> cotacoes = new ArrayList<Cotacao>();
    /**
     * Campo quantidadeVagas
     */
    private Integer quantidadeExigida;
    /**
     * Campo quantidadeLivrosDisponiveis
     */
    private Integer quantidadeLivrosDisponiveis;
    /**
     * Campo quantidadeLivrosFaltando
     */
    private Integer quantidadeAComprar;

    /**
     * Obtém o valor do campo
     * <code>urlImagem</code>
     *
     * @return {@link double}
     */
    public String getUrlImagem() {
        return urlImagem;
    }

    /**
     * Define o campo
     * <code>urlImagem</code>.
     *
     * @param urlImagem
     */
    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    /**
     * Obtém o valor do campo
     * <code>valor</code>
     *
     * @return {@link double}
     */
    public double getValorMedio() {
        return this.valorMedio;
    }

    /**
     * Define o campo
     * <code>valor</code>.
     *
     * @param valor
     */
    public void setValorMedio(double valorMedio) {

        this.valorMedio = valorMedio;
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
    public Collection<Cotacao> getCotacoes() {
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

    public Integer getQuantidadeExigida() {
        return quantidadeExigida;
    }

    public void setQuantidadeExigida(Integer quantidadeExigida) {
        this.quantidadeExigida = quantidadeExigida;
    }

    public Integer getQuantidadeLivrosDisponiveis() {
        return quantidadeLivrosDisponiveis;
    }

    public void setQuantidadeLivrosDisponiveis(Integer quantidadeLivrosDisponiveis) {
        this.quantidadeLivrosDisponiveis = quantidadeLivrosDisponiveis;
    }

    public Integer getQuantidadeAComprar() {
        return quantidadeAComprar;
    }

    public void setQuantidadeAComprar(Integer quantidadeAComprar) {
        this.quantidadeAComprar = quantidadeAComprar;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (this.urlImagem != null ? this.urlImagem.hashCode() : 0);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.valorMedio) ^ (Double.doubleToLongBits(this.valorMedio) >>> 32));
        hash = 59 * hash + (this.livro != null ? this.livro.hashCode() : 0);
        hash = 59 * hash + (this.quantidadeExigida != null ? this.quantidadeExigida.hashCode() : 0);
        hash = 59 * hash + (this.quantidadeLivrosDisponiveis != null ? this.quantidadeLivrosDisponiveis.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemListaCotacao other = (ItemListaCotacao) obj;
        if ((this.urlImagem == null) ? (other.urlImagem != null) : !this.urlImagem.equals(other.urlImagem)) {
            return false;
        }
        if (Double.doubleToLongBits(this.valorMedio) != Double.doubleToLongBits(other.valorMedio)) {
            return false;
        }
        if (this.livro != other.livro && (this.livro == null || !this.livro.equals(other.livro))) {
            return false;
        }
        if (this.quantidadeExigida != other.quantidadeExigida && (this.quantidadeExigida == null || !this.quantidadeExigida.equals(other.quantidadeExigida))) {
            return false;
        }
        if (this.quantidadeLivrosDisponiveis != other.quantidadeLivrosDisponiveis && (this.quantidadeLivrosDisponiveis == null || !this.quantidadeLivrosDisponiveis.equals(other.quantidadeLivrosDisponiveis))) {
            return false;
        }
        return true;
    }

}
