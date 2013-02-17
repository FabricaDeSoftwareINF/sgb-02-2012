package br.ufg.inf.es.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Victor Carvalho
 */
public class CotacoesLivroTest {

    private CotacoesLivro cotacoesLivro;
    private String urlImagem = "url";
    private double valorMedio = 10;
    private Date dataCadastro = new Date();
    private Livro livro = new Livro();
    private Collection<Cotacao> cotacoes = Arrays.asList(new Cotacao());
    private int quantidade = 1;

    /**
     * setup
     */
    @Before
    public void setUp() {
        cotacoesLivro = new CotacoesLivro();
        cotacoesLivro.setValorMedio(valorMedio);
        cotacoesLivro.setDataCadastro(dataCadastro);
        cotacoesLivro.setLivro(livro);
        cotacoesLivro.setCotacoes(cotacoes);
        cotacoesLivro.setQuantidade(quantidade);
        cotacoesLivro.setUrlImagem(urlImagem);
    }

    /**
     * Test of getValorMedio method, of class CotacoesLivro.
     */
    @Test
    public void testGetValorMedio() {
        assertEquals(valorMedio, cotacoesLivro.getValorMedio(), 0.0);
    }

    /**
     * Test of getDataCadastro method, of class CotacoesLivro.
     */
    @Test
    public void testGetDataCadastro() {
        assertEquals(dataCadastro, cotacoesLivro.getDataCadastro());
    }

    /**
     * Test of getLivro method, of class CotacoesLivro.
     */
    @Test
    public void testGetLivro() {
        assertEquals(livro, cotacoesLivro.getLivro());
    }

    /**
     * Test of getCotacoes method, of class CotacoesLivro.
     */
    @Test
    public void testGetCotacoes() {
        assertEquals(cotacoes, cotacoesLivro.getCotacoes());
    }

    /**
     * Test of getQuantidade method, of class CotacoesLivro.
     */
    @Test
    public void testGetQuantidade() {
        assertEquals(quantidade, cotacoesLivro.getQuantidade());
    }

    /**
     * Test of getUrlImagem method, of class CotacoesLivro.
     */
    @Test
    public void testGetUrlImagem() {
        assertEquals(urlImagem, cotacoesLivro.getUrlImagem());
    }
}