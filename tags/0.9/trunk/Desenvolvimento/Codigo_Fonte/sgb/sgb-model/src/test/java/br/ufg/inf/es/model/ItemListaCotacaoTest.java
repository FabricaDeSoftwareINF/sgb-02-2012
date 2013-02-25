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
public class ItemListaCotacaoTest {

    private ItemListaCotacao cotacoesLivro;
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
        cotacoesLivro = new ItemListaCotacao();
        cotacoesLivro.setValorMedio(valorMedio);
        cotacoesLivro.setLivro(livro);
        cotacoesLivro.setCotacoes(cotacoes);
        cotacoesLivro.setQuantidade(quantidade);
        cotacoesLivro.setUrlImagem(urlImagem);
    }

    /**
     * Test of getValorMedio method, of class ItemListaCotacao.
     */
    @Test
    public void testGetValorMedio() {
        assertEquals(valorMedio, cotacoesLivro.getValorMedio(), 0.0);
    }

    /**
     * Test of getLivro method, of class ItemListaCotacao.
     */
    @Test
    public void testGetLivro() {
        assertEquals(livro, cotacoesLivro.getLivro());
    }

    /**
     * Test of getCotacoes method, of class ItemListaCotacao.
     */
    @Test
    public void testGetCotacoes() {
        assertEquals(cotacoes, cotacoesLivro.getCotacoes());
    }

    /**
     * Test of getQuantidade method, of class ItemListaCotacao.
     */
    @Test
    public void testGetQuantidade() {
        assertEquals(quantidade, cotacoesLivro.getQuantidade());
    }

    /**
     * Test of getUrlImagem method, of class ItemListaCotacao.
     */
    @Test
    public void testGetUrlImagem() {
        assertEquals(urlImagem, cotacoesLivro.getUrlImagem());
    }

    /**
     * Test of hashCode method, of class ItemListaCotacao.
     */
    @Test
    public void testHashCode() {
        ItemListaCotacao instance = new ItemListaCotacao();
        assertTrue(instance.hashCode() != 0);
    }

    
    @Test
    public void testHashCodeNulo() {
        ItemListaCotacao instance = cotacoesLivro;
        instance.setLivro(null);
        assertTrue(instance.hashCode() != 0);
        instance.setLivro(livro);
        instance.setCotacoes(null);
        assertTrue(instance.hashCode() != 0);
        
    }
    /**
     * Test of equals method, of class ItemListaCotacao.
     */
    @Test
    public void testEquals() {
        ItemListaCotacao instance = cotacoesLivro;
        assertTrue(instance.equals(cotacoesLivro));
    }
    
    @Test
    public void testEqualsFalse() {
        ItemListaCotacao instance = new ItemListaCotacao();
        assertFalse(instance.equals(null));
        assertFalse(instance.equals(""));
        assertFalse(instance.equals(cotacoesLivro));
        instance.setUrlImagem(urlImagem);
        assertFalse(instance.equals(cotacoesLivro));
        instance.setValorMedio(valorMedio);
        assertFalse(instance.equals(cotacoesLivro));
        instance.setLivro(livro);
        assertFalse(instance.equals(cotacoesLivro));
        instance.setCotacoes(cotacoes);
        assertFalse(instance.equals(cotacoesLivro));
        instance.setQuantidade(quantidade);
        assertTrue(instance.equals(cotacoesLivro));
        instance.setQuantidade(898);
        assertFalse(instance.equals(cotacoesLivro));
        
    }
}