package br.ufg.inf.es.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author vinicius
 */
@Entity
@Table(name = "LIVRO_LISTA_COTACAO")
public class LivroListaCotacao extends AbstractEntityModel {
    
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_livro")
    private Livro livro;
    
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
    
}
