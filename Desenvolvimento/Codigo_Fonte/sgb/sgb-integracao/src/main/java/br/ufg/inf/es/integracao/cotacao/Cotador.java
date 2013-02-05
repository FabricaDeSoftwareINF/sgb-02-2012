/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao.cotacao;

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

    public boolean ehBuscadorInternacional() {
        return EhBuscadorInternacional;
    }

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
     * @param isbn identifição do livro
     * @return Json com o resultado da busca;
     */
    public String buscarLivro(String isbn) {
        return HttpUtil.FazerRequisicaoHttpGet(GerarUrlDeBusca(isbn));
    }
    
    
    
    /**
     * Converte o resultado da busca em json para um dicionário de dados. 
     * A chave do dicionário é a identificação da livraria. O valor associado
     * à uma chave é um  Map com as propriedades do livro como preço, link, imagem
     * , etc. AS chaves do Map são
     *      paisLivraria
            nomeLivro
            nomeLivro
            descricaoLivro
            linkLivroNaLivraria
            linkLivroNaLivraria
            linkImagemDoLivro 
            precoLivro
            moeda
                  
     * @param json String retornada na consulta do livro. Ess json será convertido
     * um dicionário de propriedades. 
     * @return  Um dicionário, sendo que as chaves são as identificaçãos das 
     * livrarias e os valores associados às chaves são as propriedades da cotação.
     */
    public abstract Map<String,Map<String, String>> ConverterJsonParaDicionario(String json);

    /**
     * Gera a url final de busca. Para isso adiciona-se o isbn na url padrão.
     *
     * @param isbn Identificação do livro.
     * @return String com a URL a ser usada em uma busca.
     */
    public abstract String GerarUrlDeBusca(String isbn);

    
    
}
