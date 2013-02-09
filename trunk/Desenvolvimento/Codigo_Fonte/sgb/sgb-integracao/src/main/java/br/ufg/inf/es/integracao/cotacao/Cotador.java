/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao.cotacao;

import br.ufg.inf.es.model.Livraria;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author usuario
 */
public abstract class Cotador {

    private String URL;
    private boolean EhBuscadorInternacional;

    /**
     * Verifica se o buscador realiza cotação internacional;
     *
     * @return True se o buscador realizar cotação internacional.
     */
    public boolean ehBuscadorInternacional() {
        return EhBuscadorInternacional;
    }

    /**
     * Seta se o buscador reaizará cotação internacional. 
     * @param EhBuscadorInternacional True caso realize cotação internacional
     */
    public void setEhBuscadorInternacional(boolean EhBuscadorInternacional) {
        this.EhBuscadorInternacional = EhBuscadorInternacional;
    }

    
    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    /**
     * Busca o livro por meio do isbn
     *
     * @param isbn identifição do livro, exemplo 9780470447628
     * @return Json com o resultado da busca;
     */
    public String buscarLivro(String isbn) {
        return HttpUtil.FazerRequisicaoHttpGet(gerarUrlDeBusca(isbn));
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