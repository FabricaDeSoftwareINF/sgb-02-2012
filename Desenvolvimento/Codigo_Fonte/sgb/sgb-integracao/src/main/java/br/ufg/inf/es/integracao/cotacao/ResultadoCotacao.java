package br.ufg.inf.es.integracao.cotacao;

import br.ufg.inf.es.model.Livraria;
import br.ufg.inf.es.model.Livro;

/**
 *
 * @author vinicius
 */
public class ResultadoCotacao {
    
    private Livro livro;
    private Livraria livraria;
    private OfertaLivro ofertaLivro;

    public ResultadoCotacao(Livraria livraria, OfertaLivro ofertaLivro) {
        this.livraria = livraria;
        this.ofertaLivro = ofertaLivro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Livro getLivro() {
        return livro;
    }
    
    public Livraria getLivraria() {
        return livraria;
    }

    public OfertaLivro getOfertaLivro() {
        return ofertaLivro;
    }
    
}
