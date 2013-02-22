package br.ufg.inf.es.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author vinicius
 */
@Entity
@Table(name = "COTACOES_LIVRO")
public class CotacoesLivro extends AbstractEntityModel {

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
    @ManyToOne(cascade= CascadeType.REFRESH)
    private Livro livro;
    /**
     * Campo livraria
     */
    @OneToMany(cascade= CascadeType.ALL)
    @JoinTable(
            name="COTACOESLIVRO_COTACAO",
            joinColumns = @JoinColumn( name="id_cotacoeslivro"),
            inverseJoinColumns = @JoinColumn( name="id_cotacao")
    )
    private Collection<Cotacao> cotacoes;
    
    /**
     * Campo quantidade
     */
    private int quantidade;

    

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

    @Override
    public int hashCode() {
        int hash = HASH;
        hash = SALTO * hash + (this.urlImagem != null ? this.urlImagem.hashCode() : 0);
        hash = SALTO * hash + (int) (Double.doubleToLongBits(this.valorMedio) ^ (Double.doubleToLongBits(this.valorMedio) >>> TAMANHO_BITS));
        hash = SALTO * hash + (this.livro != null ? this.livro.hashCode() : 0);
        hash = SALTO * hash + (this.cotacoes != null ? this.cotacoes.hashCode() : 0);
        hash = SALTO * hash + this.quantidade;
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
        final CotacoesLivro other = (CotacoesLivro) obj;
        if ((this.urlImagem == null) ? (other.urlImagem != null) : !this.urlImagem.equals(other.urlImagem)) {
            return false;
        }
        if (Double.doubleToLongBits(this.valorMedio) != Double.doubleToLongBits(other.valorMedio)) {
            return false;
        }
        if (this.livro != other.livro && (this.livro == null || !this.livro.equals(other.livro))) {
            return false;
        }
        if (this.cotacoes != other.cotacoes && (this.cotacoes == null || !this.cotacoes.equals(other.cotacoes))) {
            return false;
        }
        if (this.quantidade != other.quantidade) {
            return false;
        }
        return true;
    }
    
}
