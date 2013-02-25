package br.ufg.inf.es.model;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author vinicius
 */
@Entity
@Table(name = "ITEM_LISTA_COMPRA")
public class ItemListaCompras extends AbstractEntityModel {

    private static final int HASH = 5;
    private static final int SALTO = 11;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_livro")
    private Livro livro;
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
    private int quantidadeAComprar;

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public int getQuantidadeAComprar() {
        return quantidadeAComprar;
    }

    public void setQuantidadeAComprar(int quantidadeAComprar) {
        this.quantidadeAComprar = quantidadeAComprar;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (this.livro != null ? this.livro.hashCode() : 0);
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
        final ItemListaCompras other = (ItemListaCompras) obj;
        if ((this.livro == null || !this.livro.equals(other.livro))) {
            return false;
        }
        return true;
    }
}
