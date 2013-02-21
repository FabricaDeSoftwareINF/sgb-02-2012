package br.ufg.inf.es.integracao.cotacao;

import br.ufg.inf.es.model.Livraria;
import br.ufg.inf.es.model.Livro;
import java.util.Collection;
import java.util.Map;

/**
 * Fonercer os servi&ccedil;os para a cota&ccedil&atilde;o
 * @author usuario
 */
public abstract class Cotador {

    private String url;
    private boolean ehBuscadorInternacional;

    /**
     * Verifica se o buscador realiza cotação internacional;
     *
     * @return True se o buscador realizar cotação internacional.
     */
    public boolean ehBuscadorInternacional() {
        return ehBuscadorInternacional;
    }

    /**
     * Seta se o buscador reaizará cotação internacional. 
     * @param ehBuscadorInternacional True caso realize cotação internacional
     */
    public void setEhBuscadorInternacional(boolean ehBuscadorInternacional) {
        this.ehBuscadorInternacional = ehBuscadorInternacional;
    }

    
    public String getURL() {
        return url;
    }

    public void setURL(String url) {
        this.url = url;
    }

    /**
     * Busca o livro por meio do isbn
     *
     * @param isbn identifição do livro, exemplo 9780470447628
     * @return Json com o resultado da busca;
     */
    public String buscarLivro(String isbn) {
        return HttpUtil.fazerRequisicaoHttpGet(gerarUrlDeBusca(isbn));
    }

    /**
     * Busca as ofertas de um determinado livro identificado pelo isbn.
     *
     * @param isbn Identificação do livro
     * @return Um dicionário cuja chaves são as livrarias que estão ofertando o
     * livro. Os valores das chaves são Ofertas que possuem os dados como Preço,
     * Image do Livro, etc.
     */
    public abstract Collection<ResultadoCotacao> buscarOfertas(String isbn);

    public Collection<ResultadoCotacao> buscarOfertas(Livro livro) {
        Collection<ResultadoCotacao> ofertas = buscarOfertas(livro.getIsbn13());
        for (ResultadoCotacao oferta : ofertas) {
            oferta.setLivro(livro);
        }
        return ofertas;
    }

    /**
     * Gera a url final de busca. Para isso adiciona-se o isbn na url padrão.
     *
     * @param isbn Identificação do livro.
     * @return String com a URL a ser usada em uma busca.
     */
    public abstract String gerarUrlDeBusca(String isbn);
}
