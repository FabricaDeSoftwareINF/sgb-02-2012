/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao.cotacao;

import br.ufg.inf.es.model.Livraria;
import java.util.Map;

/**
 *
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
    public abstract Map<Livraria, OfertaLivro> buscarOfertas(String isbn);

    /**
     * Gera a url final de busca. Para isso adiciona-se o isbn na url padrão.
     *
     * @param isbn Identificação do livro.
     * @return String com a URL a ser usada em uma busca.
     */
    public abstract String gerarUrlDeBusca(String isbn);
}
