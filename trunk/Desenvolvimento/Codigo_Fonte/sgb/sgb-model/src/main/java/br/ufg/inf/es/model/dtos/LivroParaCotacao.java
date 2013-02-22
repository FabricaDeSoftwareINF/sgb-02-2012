package br.ufg.inf.es.model.dtos;

import br.ufg.inf.es.model.AbstractEntityModel;
import br.ufg.inf.es.model.Livro;

/**
 * Classe que representa a entidade Livro para a cotação.
 *
 * @author Victor Carvalho
 */
public class LivroParaCotacao extends AbstractEntityModel {

	private static final int HASH = 5;
	
	private static final int SALTO = 41;
	
    /**
     * Campo quantidadeVagas
     */
    private Integer quantidadeExigida;
    /**
     * Campo parametroMec
     */
    private Integer parametroMec;
    /**
     * Campo quantidadeLivrosDisponiveis
     */
    private Integer quantidadeLivrosDisponiveis;
    /**
     * Campo quantidadeLivrosFaltando
     */
    private Integer quantidadeAComprar;
    /**
     * Campo nomeLivro
     */
    private Livro livro; 

    /**
     * Construtor desta classe.
     */
    public LivroParaCotacao() {
    }

    /**
     * Construtor desta classe.
     *
     * @param quantidadeVagas
     * @param parametroMec
     * @param quantidadeLivrosDisponiveis
     * @param quantidadeAComprar
     * @param nomeLivro
     * @param isbn13
     */
    public LivroParaCotacao(Integer quantidadeVagas, Integer parametroMec,
            Integer quantidadeLivrosDisponiveis, Integer quantidadeAComprar,
            Livro livro) {
        this.quantidadeExigida = quantidadeVagas;
        this.parametroMec = parametroMec;
        this.quantidadeLivrosDisponiveis = quantidadeLivrosDisponiveis;
        this.quantidadeAComprar = quantidadeAComprar;
        this.livro = livro;
    }

    /**
     * Obtém o valor do campo
     * <code>quantidadeVagas</code>
     *
     * @return {@link Integer}
     */
    public Integer getQuantidadeExigida() {
        return this.quantidadeExigida;
    }

    /**
     * Obtém o valor do campo
     * <code>parametroMec</code>
     *
     * @return {@link Integer}
     */
    public Integer getParametroMec() {
        return this.parametroMec;
    }

    /**
     * Obtém o valor do campo
     * <code>quantidadeLivrosDisponiveis</code>
     *
     * @return {@link Integer}
     */
    public Integer getQuantidadeLivrosDisponiveis() {
        return this.quantidadeLivrosDisponiveis;
    }

    /**
     * Obtém o valor do campo
     * <code>quantidadeAComprar</code>
     *
     * @return {@link Integer}
     */
    public Integer getQuantidadeAComprar() {
        return this.quantidadeAComprar;
    }
    
    /**
     * Configura o valor do campo
     * <code>quantidadeAComprar</code>
     *
     * @return {@link Integer}
     */
    public void setQuantidadeAComprar(Integer qtd) {
        this.quantidadeAComprar = qtd;
    }
    
    /**
     * Obtém o valor do campo
     * <code>quantidadeLivrosFaltando</code>
     *
     * @return {@link Integer}
     */
    public Integer getQuantidadeLivrosFaltando() {
        return Math.max(0, this.quantidadeExigida - this.quantidadeLivrosDisponiveis);
    }

    /**
     * Obtém o valor do campo
     * <code>livro</code>
     *
     * @return {@link String}
     */
    public Livro getLivro() {
        return this.livro;
    }

    public void setQuantidadeExigida(Integer quantidadeExigida) {
        this.quantidadeExigida = quantidadeExigida;
    }

    public void setParametroMec(Integer parametroMec) {
        this.parametroMec = parametroMec;
    }

    public void setQuantidadeLivrosDisponiveis(Integer quantidadeLivrosDisponiveis) {
        this.quantidadeLivrosDisponiveis = quantidadeLivrosDisponiveis;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    @Override
    public int hashCode() {
        int hash = HASH;
        hash = SALTO * hash + (this.quantidadeExigida != null ? this.quantidadeExigida.hashCode() : 0);
        hash = SALTO * hash + (this.parametroMec != null ? this.parametroMec.hashCode() : 0);
        hash = SALTO * hash + (this.quantidadeLivrosDisponiveis != null ? this.quantidadeLivrosDisponiveis.hashCode() : 0);
        hash = SALTO * hash + (this.quantidadeAComprar != null ? this.quantidadeAComprar.hashCode() : 0);
        hash = SALTO * hash + (this.livro != null ? this.livro.hashCode() : 0);
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
        final LivroParaCotacao other = (LivroParaCotacao) obj;
        if (this.quantidadeExigida != other.quantidadeExigida && (this.quantidadeExigida == null || !this.quantidadeExigida.equals(other.quantidadeExigida))) {
            return false;
        }
        if (!this.parametroMec.equals(other.parametroMec) && (this.parametroMec == null || !this.parametroMec.equals(other.parametroMec))) {
            return false;
        }
        if (!this.quantidadeLivrosDisponiveis.equals(other.quantidadeLivrosDisponiveis) && (this.quantidadeLivrosDisponiveis == null || !this.quantidadeLivrosDisponiveis.equals(other.quantidadeLivrosDisponiveis))) {
            return false;
        }
        if (!this.quantidadeAComprar.equals(other.quantidadeAComprar) && (this.quantidadeAComprar == null || !this.quantidadeAComprar.equals(other.quantidadeAComprar))) {
            return false;
        }
        if (this.livro != other.livro && (this.livro == null || !this.livro.equals(other.livro))) {
            return false;
        }
        return true;
    }
    
}